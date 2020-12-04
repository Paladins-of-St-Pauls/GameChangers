package org.firstinspires.ftc.teamcode.paladins.joyeuse;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.tasks.MessageTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;
import org.firstinspires.ftc.teamcode.paladins.tasks.TwoSensorTracerTask;

import java.util.ArrayDeque;

@Autonomous(name = "JoyeuseAutonomous")
public class JoyeuseSimpleAutonomous extends PaladinsOpMode {
    private JoyeuseConfiguration config;
    private JoyeuseDrive drive;
    private JoyeuseShoot shoot;
    private JoyeuseIntake intake;
    private ArrayDeque<Task> tasks = new ArrayDeque<>();

    @Override
    protected void onInit() {
        config = JoyeuseConfiguration.newConfig(hardwareMap, telemetry);

        drive = new JoyeuseDrive(this, config.leftMidMotor, config.leftBackMotor, config.rightMidMotor, config.rightBackMotor);
        shoot = new JoyeuseShoot(this, config.leftShooterMotor, config.rightShooterMotor);
        intake = new JoyeuseIntake(this, config.intakeMotor, config.bumpMotor, config.conveyorServo, config.indexerServo);

//        Drive forward
        tasks.add(new JoyeuseDriveTask(this, 1.2, drive, -0.5, -0.5));
        tasks.add(new JoyeuseDriveTask(this, 0.1, drive, 0, 0));
//        Shoot three rings
//        Spin up the shooters
        tasks.add(new JoyeuseSetShooterTask(this, 1.5, shoot, 0.8));
//        Shoot the rings
        tasks.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.12));
        tasks.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.25));
        tasks.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.12));
        tasks.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.25));
        tasks.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.12));
        tasks.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.25));
        tasks.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.12));
        tasks.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.25));
        tasks.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.12));
        tasks.add(new JoyeuseIndexerShootTask(this, 0.5, intake, 0.25));
//        Kill shooters
        tasks.add(new JoyeuseSetShooterTask(this, 0.1, shoot, 0));

//        Drive to white line
        tasks.add(new TwoSensorTracerTask(this, 10, drive, -0.2, -0.2, config.leftColourSensor, config.rightColourSensor));
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