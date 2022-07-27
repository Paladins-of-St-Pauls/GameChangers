package org.firstinspires.ftc.teamcode.paladins.durandal;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseDrive;
import org.firstinspires.ftc.teamcode.paladins.tasks.BaseTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;

public class DurandalDriveEncoderTask extends BaseTask implements Task {

    private final DurandalDrive drive;
    private final double leftDistance;
    private final double rightDistance;
    private final double leftPower;
    private final double rightPower;

    public DurandalDriveEncoderTask(PaladinsOpMode opMode, double time, DurandalDrive drive, double leftMM, double rightMM, double leftPower, double rightPower) {
        super(opMode, time);
        this.drive = drive;
//        This is for the GoBilda motor with 537.7 ticks/rev.
        double ticks_per_rev = 537.7;
        double wheel_diameter = 120;
        double magic_factor = (2000.0/1475.0)*(100.0/99.0)*(2400.0/2350.0);

        this.leftDistance = (-leftMM*ticks_per_rev/(wheel_diameter*Math.PI))*magic_factor;
        this.rightDistance = (-rightMM*ticks_per_rev/(wheel_diameter*Math.PI))*magic_factor;
        this.leftPower = leftPower;
        this.rightPower = rightPower;
    }

    @Override
    public void init() {
        super.init();
        drive.setEncoder(true);
        drive.setTargetPosition(leftDistance, rightDistance);
        drive.setPower(leftPower, rightPower);
        drive.setToBrake();
    }

    @Override
    public boolean isFinished() {
        return super.isFinished() || drive.targetPositionReached();
    }

    @Override
    public void run() {
        if (isFinished()) {
            drive.setPower(0, 0);
            drive.update();
            drive.setEncoder(false);
            return;
        }
        drive.update();
    }

}
