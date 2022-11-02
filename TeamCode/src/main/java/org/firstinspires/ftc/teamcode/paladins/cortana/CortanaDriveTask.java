package org.firstinspires.ftc.teamcode.paladins.cortana;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.durandal.DurandalDrive;
import org.firstinspires.ftc.teamcode.paladins.tasks.BaseTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;

public class CortanaDriveTask extends BaseTask implements Task {

    private final DurandalDrive drive;
    private final double leftSpeed;
    private final double rightSpeed;

    public CortanaDriveTask(PaladinsOpMode opMode, double time, DurandalDrive drive, double leftSpeed, double rightSpeed) {
        super(opMode, time);
        this.drive = drive;
        this.leftSpeed = leftSpeed;
        this.rightSpeed = rightSpeed;
        drive.setEncoder(false);
    }

    @Override
    public void init() {
        super.init();
        drive.setEncoder(false);
    }

    @Override
    public void run() {
        drive.setPower(leftSpeed, rightSpeed);
        drive.update();
        if (isFinished()) {
            drive.setPower(0, 0);
            drive.update();
            return;
        }

    }

}
