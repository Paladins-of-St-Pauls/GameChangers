package org.firstinspires.ftc.teamcode.paladins.joyeuse;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;

import java.util.ArrayDeque;

@Autonomous(name = "JoyeuseSimpleAutonomousRedInner")
public class JoyeuseSimpleAutonomousRedInner extends PaladinsOpMode {
    private JoyeuseConfiguration config;
    private JoyeuseDrive drive;
    private JoyeuseShoot shoot;
    private JoyeuseIntake intake;
    private ArrayDeque<Task> tasks = new ArrayDeque<>();

    private int ServoHoldTime = 1;

    @Override
    protected void onInit() {
        config = JoyeuseConfiguration.newConfig(hardwareMap, telemetry);

        drive = new JoyeuseDrive(this, config.leftMidMotor, config.leftBackMotor, config.rightMidMotor, config.rightBackMotor);
        shoot = new JoyeuseShoot(this, config.leftShooterMotor, config.rightShooterMotor);
        intake = new JoyeuseIntake(this, config.intakeMotor, config.bumpMotor, config.conveyorServo, config.indexerServo);

        tasks.add(new JoyeuseWGTask(this, 1, config.wgArm, config.wgHook, false));

//        Variable Delay
//        tasks.add(new JoyeuseDriveTask(this, 5, drive, 0,0));

//        Drive forward
        tasks.add(new JoyeuseDriveTask(this, 1.8, drive, -0.5, -0.5));
        tasks.add(new JoyeuseDriveTask(this, 0.1, drive, 0, 0));
//        Shoot three rings
//        Spin up the shooters
        tasks.add(new JoyeuseSetShooterTask(this, 3, shoot, 1));
//        Shoot the rings
        tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.05));
        tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.25));
        tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.05));
        tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.25));
        tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.05));
        tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.25));
        tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.05));
        tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.25));
        tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.05));
        tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.25));
//        Kill shooters
        tasks.add(new JoyeuseSetShooterTask(this, 0.1, shoot, 0));

//        Drive to white line
//        tasks.add(new TwoSensorTracerTask(this, 4, drive, -0.2, -0.2, config.leftColourSensor, config.rightColourSensor));

        tasks.add(new JoyeuseDriveTask(this, 1, drive, -0.5, -0.5));
        tasks.add(new JoyeuseDriveTask(this, 0.8, drive, -0.5, 0.5));
        tasks.add(new JoyeuseDriveTask(this, 0.1, drive, 0, 0));

        tasks.add(new JoyeuseWGTask(this, 1, config.wgArm, config.wgHook, true));
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