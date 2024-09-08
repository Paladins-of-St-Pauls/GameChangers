package org.firstinspires.ftc.teamcode.paladins.excalibur;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.cortana.CortanaDrive;
import org.firstinspires.ftc.teamcode.paladins.tasks.BaseTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;

public class ExcaliburDriveTask extends BaseTask implements Task {

    private final ExcaliburDrive drive;
    private final double frontLeftSpeed;
    private final double frontRightSpeed;
    private final double backLeftSpeed;
    private final double backRightSpeed;


    public ExcaliburDriveTask(PaladinsOpMode opMode, double time, ExcaliburDrive drive, double frontLeftSpeed, double frontRightSpeed, double backLeftSpeed, double backRightSpeed) {
        super(opMode, time);
        this.drive = drive;
        this.frontLeftSpeed = -frontLeftSpeed;
        this.frontRightSpeed = -frontRightSpeed;
        this.backLeftSpeed = -backLeftSpeed;
        this.backRightSpeed = -backRightSpeed;
    }

    @Override
    public void init() {
        super.init();
        drive.setEncoder(false);
    }

    @Override
    public void run() {
        drive.setPower(frontLeftSpeed, frontRightSpeed, backLeftSpeed, backRightSpeed);
        drive.update();
        if (isFinished()) {
            drive.setPower(0, 0, 0, 0);
            drive.update();
            return;
        }

    }

}