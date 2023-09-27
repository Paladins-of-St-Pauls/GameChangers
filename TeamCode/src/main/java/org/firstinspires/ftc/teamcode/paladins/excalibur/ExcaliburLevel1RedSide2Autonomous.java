package org.firstinspires.ftc.teamcode.paladins.excalibur;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;
import org.firstinspires.ftc.teamcode.paladins.tasks.WaitTask;

import java.util.ArrayDeque;

@Autonomous(name = "ExcaliburLevel1RedSide2Autonomous")
public class ExcaliburLevel1RedSide2Autonomous extends PaladinsOpMode {
    private ExcaliburConfiguration config;
    private ExcaliburDrive drive;
    private ArrayDeque<Task> tasks = new ArrayDeque<>();

    private int ServoHoldTime = 1;

    @Override
    protected void onInit() {
    config = ExcaliburConfiguration.newConfig(hardwareMap, telemetry);
    drive = new ExcaliburDrive(this, config.backLeftMotor, config.backRightMotor, config.frontLeftMotor, config.frontRightMotor);
// Begin Auto
    tasks.add(new ExcaliburDriveTask(this, 0.2, drive, 0.25, 0.25,0.25,0.25));
    tasks.add(new WaitTask(this, 0.5));
    tasks.add(new ExcaliburDriveTask(this, 2, drive, 0.5, -0.5,-0.5,0.5));
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
            drive.setPower(0,0,0,0);
            drive.update();
        }
    }
}