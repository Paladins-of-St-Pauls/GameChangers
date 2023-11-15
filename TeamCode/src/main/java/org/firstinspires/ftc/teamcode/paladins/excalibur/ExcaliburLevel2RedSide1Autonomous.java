package org.firstinspires.ftc.teamcode.paladins.excalibur;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;
import org.firstinspires.ftc.teamcode.paladins.tasks.WaitTask;

import java.util.ArrayDeque;

@Autonomous(name = "ExcaliburLevel2RedSide1Autonomous")
public class ExcaliburLevel2RedSide1Autonomous extends PaladinsOpMode {
    private ExcaliburConfiguration config;
    private ExcaliburDrive drive;
    private ExcaliburUtils utils;
    private ArrayDeque<Task> tasks = new ArrayDeque<>();

    public int element_zone = 1;
    private int ServoHoldTime = 1;

    private ExcaliburVision excaliburVision;


    @Override
    protected void onInit() {
        config = ExcaliburConfiguration.newConfig(hardwareMap, telemetry);
        drive = new ExcaliburDrive(this, config.backLeftMotor, config.backRightMotor, config.frontLeftMotor, config.frontRightMotor);
        utils = new ExcaliburUtils(this, config.Harvester, config.LeftLiftMotor, config.RightLiftMotor, config.BackLeftOutake, config.BackRightOutake, config.FrontLeftOutake, config.FrontRightOutake, config.PlaneShooter, config.RSensor, config.LSensor, config.indexMotor);

        tasks.add(new WaitTask(this, 3));
        tasks.add(new ExcaliburDriveTask(this, 0.3, drive, 1,1,1,1));
        tasks.add(new ExcaliburUtilsTask(this, 1, utils, -1, 0,0,0));
        tasks.add(new ExcaliburDriveTask(this, 0.3, drive, -1,-1,-1,-1));
        tasks.add(new ExcaliburDriveTask(this, 1, drive, 0.5, -0.5, -0.5, 0.5));


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
            drive.setPower(0, 0, 0, 0);
            drive.update();
        }
    }
}