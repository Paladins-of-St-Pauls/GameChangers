package org.firstinspires.ftc.teamcode.paladins.joyeuse;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.common.TankDrive;
import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseConfiguration;
import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseDrive;
import org.firstinspires.ftc.teamcode.paladins.tasks.TankDriveEncTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;

import java.util.ArrayDeque;

@Autonomous(name = "JoyeuseAutoTest")
public class JoyeuseAutoTest extends PaladinsOpMode {
    private JoyeuseConfiguration config;
    private JoyeuseDrive drive;
    private ArrayDeque<Task> tasks = new ArrayDeque<>();

    @Override
    protected void onInit() {
        config = JoyeuseConfiguration.newConfig(hardwareMap, telemetry);

        drive = new JoyeuseDrive(this, config.leftMidMotor, config.leftBackMotor, config.rightMidMotor, config.rightBackMotor);
        tasks.add(new JoyeuseDriveTask(this, 1, drive, 0.5, 0.5));
        tasks.add(new JoyeuseDriveTask(this, 1, drive, -0.5, 0.5));
        tasks.add(new JoyeuseDriveTask(this, 1, drive, 0.5, -0.5));
        tasks.add(new JoyeuseDriveTask(this, 1, drive, 1, 1));
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