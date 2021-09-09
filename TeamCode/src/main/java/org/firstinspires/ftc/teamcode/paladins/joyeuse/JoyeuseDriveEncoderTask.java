package org.firstinspires.ftc.teamcode.paladins.joyeuse;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.tasks.BaseTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;

public class JoyeuseDriveEncoderTask extends BaseTask implements Task {

    private final JoyeuseDrive drive;
    private final double leftDistance;
    private final double rightDistance;

    public JoyeuseDriveEncoderTask(PaladinsOpMode opMode, double time, JoyeuseDrive drive, double leftDistance, double rightDistance) {
        super(opMode, time);
        this.drive = drive;
        this.leftDistance = leftDistance;
        this.rightDistance = rightDistance;
    }

    @Override
    public void init() {
        super.init();
        drive.setEncoder(true);
        drive.setTargetPosition(leftDistance, rightDistance);
        drive.setPower(0.25, 0.25);
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
