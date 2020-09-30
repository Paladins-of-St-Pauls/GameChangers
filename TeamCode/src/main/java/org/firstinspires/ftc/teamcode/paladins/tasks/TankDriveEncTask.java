package org.firstinspires.ftc.teamcode.paladins.tasks;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.common.TankDrive;

public class TankDriveEncTask extends BaseTask implements Task {

    private final TankDrive drive;
    private final double leftSpeed;
    private final double rightSpeed;

    private final double leftCm;
    private final double rightCm;

    public TankDriveEncTask(PaladinsOpMode opMode, double time, TankDrive drive, double leftSpeed, double rightSpeed, double leftCm, double rightCm) {
        super(opMode, time);
        this.drive = drive;
        this.leftSpeed = leftSpeed;
        this.rightSpeed = rightSpeed;

        this.leftCm = leftCm;
        this.rightCm = rightCm;
    }

    @Override
    public void init() {
        super.init();
        drive.setPosition(leftCm, rightCm);
        drive.setEncoderMode(true);
    }

    @Override
    public boolean isFinished() {
        return super.isFinished() || drive.isFinished();
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
