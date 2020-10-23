package org.firstinspires.ftc.teamcode.paladins.jeffs;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.common.TankDrive;
import org.firstinspires.ftc.teamcode.paladins.tasks.TankDriveTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;

import java.util.ArrayDeque;

@Disabled
@Autonomous(name = "JeffsAutonomousTimed")
public class JeffsAutonomousTimed extends PaladinsOpMode {
    private JeffsBotConfiguration config;
    private TankDrive drive;
    private ArrayDeque<Task> tasks = new ArrayDeque<>();

    @Override
    protected void onInit() {
        config = JeffsBotConfiguration.newConfig(hardwareMap, telemetry);
        config.leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        config.rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        drive = new TankDrive(this, gamepad1, config.leftMotor, config.rightMotor);
        tasks.add(new TankDriveTask(this, .2, drive, -0.2, -0.2));
        tasks.add(new TankDriveTask(this, 6, drive, -0.7, -0.7));
        tasks.add(new TankDriveTask(this, 2, drive, -0.7, 0));
        tasks.add(new TankDriveTask(this, 6.5, drive, -0.7, -0.7));
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