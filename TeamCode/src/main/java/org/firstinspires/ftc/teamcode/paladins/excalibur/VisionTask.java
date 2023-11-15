package org.firstinspires.ftc.teamcode.paladins.excalibur;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.tasks.BaseTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.MessageTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;

import java.util.ArrayDeque;

public class VisionTask extends BaseTask implements Task {

    final private ArrayDeque<Task> tasks;
    final private Alliance alliance;

    private ExcaliburConfiguration config;
    private ExcaliburDrive drive;
    private ExcaliburUtils utils;


    public VisionTask(PaladinsOpMode opMode, ArrayDeque<Task> tasks, Alliance alliance, double time) {
        super(opMode, time);
        this.tasks = tasks;
        this.alliance = alliance;

    }

    void update() {

    }

    protected void onInit() {
        config = ExcaliburConfiguration.newConfig(hardwareMap, telemetry);
        drive = new ExcaliburDrive(opMode, config.backLeftMotor, config.backRightMotor, config.frontLeftMotor, config.frontRightMotor);
        utils = new ExcaliburUtils(opMode, config.Harvester, config.LeftLiftMotor, config.RightLiftMotor, config.BackLeftOutake, config.BackRightOutake, config.FrontLeftOutake, config.FrontRightOutake, config.PlaneShooter, config.RSensor, config.LSensor, config.indexMotor);
    }

    @Override
    public void run() {
        if (isFinished()) {
            return;
        }

        int zone = SplitAveragePipeline.get_element_zone();

        if (alliance == Alliance.REDLEFT) {
            if (zone == 1) {
                tasks.add(new MessageTask(opMode, 1, "Red Zone 1 Left"));
                //forward 1 tile
                tasks.add(new ExcaliburDriveTask(opMode, 1, drive, 1, 1, 1, 1));
                //Outake purple pixel
                tasks.add(new ExcaliburUtilsTask(opMode, 1,utils,-1,0,0,0));
                //straffe right 3 tiles   // strafe right 5 tiles
                //tasks.add(new ExcaliburDriveTask(opMode, 5, drive, 1, 1, 1, 1));
                //backward 1 tile
                //straffe right 1 tile
                //tasks.add(new ExcaliburDriveTask(opMode, 0.5, drive, 1, 1, 1, 1));

            } else if (zone == 2) {
                tasks.add(new MessageTask(opMode, 1, "Red Zone 2 Left"));
                //forward 1 tile
                tasks.add(new ExcaliburDriveTask(opMode, 1, drive, 1, 1, 1, 1));
                // strafe right 5 tiles
                tasks.add(new ExcaliburDriveTask(opMode, 5, drive, 1, 1, 1, 1));
                //forward 0.5 tiles
                tasks.add(new ExcaliburDriveTask(opMode, 0.5, drive, 1, 1, 1, 1));
            } else if (zone == 3) {
                tasks.add(new MessageTask(opMode, 1, "Red Zone 3 Left"));
                //forward 1 tile
                tasks.add(new ExcaliburDriveTask(opMode, 1, drive, 1, 1, 1, 1));
                // strafe right 5 tiles
                tasks.add(new ExcaliburDriveTask(opMode, 5, drive, 1, 1, 1, 1));
                //forward 0.5 tiles
                tasks.add(new ExcaliburDriveTask(opMode, 0.5, drive, 1, 1, 1, 1));
            }
        }
        if (alliance == Alliance.BLUELEFT) {
            if (zone == 1) {
                tasks.add(new MessageTask(opMode, 1, "Blue Zone 1 Left"));
                //drive up to spike point
                tasks.add(new ExcaliburDriveTask(opMode, 0.3, drive, -1,1,1,-1));
                tasks.add(new ExcaliburDriveTask(opMode, 0.3, drive, 1,1,1,1));
                //eject pixel
                tasks.add(new ExcaliburUtilsTask(opMode, 1, utils, -1, 0,0,0));
            } else if (zone == 2) {
                tasks.add(new MessageTask(opMode, 1, "Blue Zone 2 Left"));
                tasks.add(new ExcaliburDriveTask(opMode, 0.3, drive, 1,1,1,1));
                //eject pixel
                tasks.add(new ExcaliburUtilsTask(opMode, 1, utils, -1, 0,0,0));
            } else if (zone == 3) {
                tasks.add(new MessageTask(opMode, 1, "Blue Zone 3 Left"));
                //forward 1 tile
                tasks.add(new ExcaliburDriveTask(opMode, 0.3, drive, 1,-1,-1,1));
                tasks.add(new ExcaliburDriveTask(opMode, 0.3, drive, 1,1,1,1));
                //eject pixel
                tasks.add(new ExcaliburUtilsTask(opMode, 1, utils, -1, 0,0,0));
            }
        }
        if (alliance == Alliance.REDRIGHT) {
            if (zone == 1) {
                tasks.add(new MessageTask(opMode, 1, "Red Zone 1 Right"));
                //forward 1 tile
                tasks.add(new ExcaliburDriveTask(opMode, 1, drive, 1, 1, 1, 1));
                // strafe right 2.5 tiles
                tasks.add(new ExcaliburDriveTask(opMode, 2.5, drive, 1, 1, 1, 1));
                //forward 0.5 tiles
                tasks.add(new ExcaliburDriveTask(opMode, 0.5, drive, 1, 1, 1, 1));
            } else if (zone == 2) {
                tasks.add(new MessageTask(opMode, 1, "Red Zone 2 Right"));
                //forward 1 tile
                tasks.add(new ExcaliburDriveTask(opMode, 1, drive, 1, 1, 1, 1));
                // strafe right 2.5 tiles
                tasks.add(new ExcaliburDriveTask(opMode, 2.5, drive, 1, 1, 1, 1));
                //forward 0.5 tiles
                tasks.add(new ExcaliburDriveTask(opMode, 0.5, drive, 1, 1, 1, 1));
            } else if (zone == 3) {
                tasks.add(new MessageTask(opMode, 1, "Red Zone 3 Right"));
                //forward 1 tile
                tasks.add(new ExcaliburDriveTask(opMode, 1, drive, 1, 1, 1, 1));
                // strafe right 2.5 tiles
                tasks.add(new ExcaliburDriveTask(opMode, 2.5, drive, 1, 1, 1, 1));
                //forward 0.5 tiles
                tasks.add(new ExcaliburDriveTask(opMode, 0.5, drive, 1, 1, 1, 1));
            }
        }
        if (alliance == Alliance.BLUERIGHT) {
            if (zone == 1) {
                tasks.add(new MessageTask(opMode, 1, "Blue Zone 1 Right"));
                //forward 1 tile
                tasks.add(new ExcaliburDriveTask(opMode, 1, drive, 1, 1, 1, 1));
                // strafe left 2.5 tiles
                tasks.add(new ExcaliburDriveTask(opMode, 2.5, drive, 1, 1, 1, 1));
                //forward 0.5 tiles
                tasks.add(new ExcaliburDriveTask(opMode, 0.5, drive, 1, 1, 1, 1));
            } else if (zone == 2) {
                tasks.add(new MessageTask(opMode, 1, "Blue Zone 2 Right"));
                tasks.add(new ExcaliburDriveTask(opMode, 1, drive, 1, 1, 1, 1));
                // strafe left 2.5 tiles
                tasks.add(new ExcaliburDriveTask(opMode, 2.5, drive, 1, 1, 1, 1));
                //forward 0.5 tiles
                tasks.add(new ExcaliburDriveTask(opMode, 0.5, drive, 1, 1, 1, 1));
            } else if (zone == 3) {
                tasks.add(new MessageTask(opMode, 1, "Blue Zone 3 Right"));
                tasks.add(new ExcaliburDriveTask(opMode, 1, drive, 1, 1, 1, 1));
                // strafe left 2.5 tiles
                tasks.add(new ExcaliburDriveTask(opMode, 2.5, drive, 1, 1, 1, 1));
                //forward 0.5 tiles
                tasks.add(new ExcaliburDriveTask(opMode, 0.5, drive, 1, 1, 1, 1));
            }
        }
        this.isFinished = true;

    }

}
