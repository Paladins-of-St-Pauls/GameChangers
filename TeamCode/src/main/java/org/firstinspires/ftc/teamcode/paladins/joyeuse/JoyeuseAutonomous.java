package org.firstinspires.ftc.teamcode.paladins.joyeuse;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.tasks.MessageTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;
import org.firstinspires.ftc.teamcode.paladins.tasks.TwoSensorTracerTask;

import java.util.ArrayDeque;

@Autonomous(name = "JoyeuseAutonomous")
public class JoyeuseAutonomous extends PaladinsOpMode {
    private JoyeuseConfiguration config;
    private JoyeuseDrive drive;
    private JoyeuseShoot shoot;
    private JoyeuseIntake intake;
    private ArrayDeque<Task> tasks = new ArrayDeque<>();
    private ArrayDeque<Task> tasks_none_rings = new ArrayDeque<>();
    private ArrayDeque<Task> tasks_one_ring = new ArrayDeque<>();
    private ArrayDeque<Task> tasks_four_rings = new ArrayDeque<>();

    @Override
    protected void onInit() {
        config = JoyeuseConfiguration.newConfig(hardwareMap, telemetry);

        drive = new JoyeuseDrive(this, config.leftMidMotor, config.leftBackMotor, config.rightMidMotor, config.rightBackMotor);
        shoot = new JoyeuseShoot(this, config.leftShooterMotor, config.rightShooterMotor);
        intake = new JoyeuseIntake(this, config.intakeMotor, config.bumpMotor, config.conveyorServo, config.indexerServo);
//
//        TASKS FOR NO RINGS
        tasks_none_rings.add(new MessageTask(this, 1.0, "NO RINGS DETECTED"));
        tasks_none_rings.add(new JoyeuseDriveTask(this, 1.2, drive, 0.5, 0.5));
        tasks_none_rings.add(new JoyeuseDriveTask(this, 1, drive, 0.5, -0.5));
        tasks_none_rings.add(new JoyeuseDriveTask(this, 0.1, drive, 0, 0));
//        Shoot three rings
//        Spin up the shooters
        tasks_none_rings.add(new JoyeuseSetShooterTask(this, 1.5, shoot, 0.8));
//        Shoot 3 rings
        tasks_none_rings.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.12));
        tasks_none_rings.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.25));
        tasks_none_rings.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.12));
        tasks_none_rings.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.25));
        tasks_none_rings.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.12));
        tasks_none_rings.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.25));
        tasks_none_rings.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.12));
        tasks_none_rings.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.25));
        tasks_none_rings.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.12));
        tasks_none_rings.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.25));
//        Kill shooters
        tasks_none_rings.add(new JoyeuseSetShooterTask(this, 0.1, shoot, 0));


//        Drive to white line
        tasks_none_rings.add(new TwoSensorTracerTask(this, 10, drive, 0.2, 0.2, config.leftColourSensor, config.rightColourSensor));
//        Spin up the shooters
        tasks_none_rings.add(new JoyeuseSetShooterTask(this, 1.5, shoot, 1.0));
//        Shoot 3 rings
        tasks_none_rings.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.25));
        tasks_none_rings.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.25));
        tasks_none_rings.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.25));

//        TASKS ONE RING
        tasks_one_ring.add(new MessageTask(this, 1.0, "ONE RING DETECTED"));
//        Drive to white line
        tasks_one_ring.add(new TwoSensorTracerTask(this, 10, drive, 0.2, 0.2, config.leftColourSensor, config.rightColourSensor));
//        Spin up the shooters
        tasks_one_ring.add(new JoyeuseSetShooterTask(this, 1.5, shoot, 1.0));
//        Shoot 3 rings
        tasks_one_ring.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.12));
        tasks_one_ring.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.25));
        tasks_one_ring.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.12));
        tasks_one_ring.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.25));
        tasks_one_ring.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.12));
        tasks_one_ring.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.25));
//        Turn on the spot
        tasks_one_ring.add(new JoyeuseDriveTask(this, 0.5, drive, 0.5, -0.5));
//        Drive forward
        tasks_one_ring.add(new JoyeuseDriveTask(this, 0.5, drive, 0.5, 0.5));
//        Turn on the spot
        tasks_one_ring.add(new JoyeuseDriveTask(this, 0.5, drive, -0.5, 0.5));
//        Drive forward
        tasks_one_ring.add(new JoyeuseDriveTask(this, 0.3, drive, 0.5, 0.5));
//        Release Wobble-goal
//        tasks_one_ring.add(new JoyeuseWGTask(this, 1, config.wgArm, config.wgHand));

//        TASKS FOUR RINGS
        tasks_four_rings.add(new MessageTask(this, 1.0, "FOUR RINGS DETECTED"));
//        Drive to white line
        tasks_four_rings.add(new TwoSensorTracerTask(this, 10, drive, 0.2, 0.2, config.leftColourSensor, config.rightColourSensor));
//        Spin up the shooters
        tasks_four_rings.add(new JoyeuseSetShooterTask(this, 1.5, shoot, 1.0));
//        Shoot 3 rings
        tasks_four_rings.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.12));
        tasks_four_rings.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.25));
        tasks_four_rings.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.12));
        tasks_four_rings.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.25));
        tasks_four_rings.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.12));
        tasks_four_rings.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.25));
//        Drive further
        tasks_four_rings.add(new JoyeuseDriveTask(this, 2.5, drive, 0.5, 0.5));
//        Release Wobble-goal
//        tasks_four_rings.add(new JoyeuseWGTask(this, 1, config.wgArm, config.wgHand));

//        TASKS FOR ANY RING CONFIGURATION
        tasks.add(new StackChoiceTask(this, 2.0, tasks, tasks_none_rings, tasks_one_ring, tasks_four_rings));

//        config.imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);

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