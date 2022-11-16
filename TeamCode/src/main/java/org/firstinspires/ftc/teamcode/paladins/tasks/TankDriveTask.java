package org.firstinspires.ftc.teamcode.paladins.tasks;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.common.TankDrive;

public class TankDriveTask extends BaseTask implements Task {

    private final TankDrive drive;
    private final double leftSpeed;
    private final double rightSpeed;

    public TankDriveTask(PaladinsOpMode opMode, double time, TankDrive drive, double leftSpeed, double rightSpeed) {
        super(opMode, time);
        this.drive = drive;
        this.leftSpeed = leftSpeed;
        this.rightSpeed = rightSpeed;
    }

    @Override
    public void init() {
        super.init();
        drive.setEncoderMode(false);
    }

    @Override
    public void run() {
        if (isFinished()) {
            drive.setPower(0, 0);
            drive.update();
            return;
        }
        drive.setPower(leftSpeed, rightSpeed);
        drive.update();
    }

}
