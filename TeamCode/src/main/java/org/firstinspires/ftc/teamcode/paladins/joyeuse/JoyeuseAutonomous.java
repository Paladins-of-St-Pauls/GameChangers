package org.firstinspires.ftc.teamcode.paladins.joyeuse;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.common.TankDrive;
import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseConfiguration;
import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseDrive;
import org.firstinspires.ftc.teamcode.paladins.tasks.MessageTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.TankDriveEncTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;
import org.firstinspires.ftc.teamcode.paladins.tasks.TwoSensorTracerTask;
import org.opencv.core.Point;

import java.util.ArrayDeque;

@Autonomous(name = "JoyeuseAutonomous")
public class JoyeuseAutonomous extends PaladinsOpMode {
    private JoyeuseConfiguration config;
    private JoyeuseDrive drive;
    private ArrayDeque<Task> tasks = new ArrayDeque<>();
    private ArrayDeque<Task> tasks_none_rings = new ArrayDeque<>();
    private ArrayDeque<Task> tasks_one_ring = new ArrayDeque<>();
    private ArrayDeque<Task> tasks_four_rings = new ArrayDeque<>();

    @Override
    protected void onInit() {
        config = JoyeuseConfiguration.newConfig(hardwareMap, telemetry);

        drive = new JoyeuseDrive(this, config.leftMidMotor, config.leftBackMotor, config.rightMidMotor, config.rightBackMotor);

//        TASKS FOR NO RINGS
        tasks_none_rings.add(new MessageTask(this, 1.0, "NO RINGS DETECTED"));
//        Drive to white line
        tasks_none_rings.add(new TwoSensorTracerTask(this, 10, drive, 0.25, 0.25, config.leftColourSensor, config.rightColourSensor));
//        Release Wobble-goal

//        TASKS ONE RING
        tasks_one_ring.add(new MessageTask(this, 1.0, "ONE RING DETECTED"));
//        Drive to white line
        tasks_none_rings.add(new TwoSensorTracerTask(this, 10, drive, 0.25, 0.25, config.leftColourSensor, config.rightColourSensor));
//        Turn on the spot
        tasks_none_rings.add(new JoyeuseDriveTask(this, 1, drive, 0.5, 0));
//        Drive forward
        tasks_none_rings.add(new JoyeuseDriveTask(this, 1, drive, 0.5, 0.5));
//        Turn on the spot
        tasks_none_rings.add(new JoyeuseDriveTask(this, 1, drive, 0, 0.5));
//        Drive forward
        tasks_none_rings.add(new JoyeuseDriveTask(this, 1, drive, 0.5, 0.5));
//        Release Wobble-goal

//        TASKS FOUR RINGS
        tasks_four_rings.add(new MessageTask(this, 1.0, "FOUR RINGS DETECTED"));
//        Drive to white line
        tasks_none_rings.add(new TwoSensorTracerTask(this, 10, drive, 0.25, 0.25, config.leftColourSensor, config.rightColourSensor));
//        Drive further
        tasks_four_rings.add(new JoyeuseDriveTask(this, 3, drive, 0.25, 0.25));
//        Release Wobble-goal

//        TASKS FOR ANY RING CONFIGURATION
        tasks.add(new StackChoiceTask(this, 2.0, tasks, tasks_none_rings, tasks_one_ring, tasks_four_rings));
    }

    @Override
    protected void activeLoop() throws InterruptedException {
        Task currentTask = tasks.peekFirst();
        if (currentTask == null) {
            return;
        }
        currentTask.run();
        if (currentTask.isFinished()) {
            tasks.removeFirst();

        }
        if (tasks.isEmpty()) {
            drive.setPower(0, 0);
            drive.update();
        }
    }
}