package org.firstinspires.ftc.teamcode.paladins.excalibur;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.tasks.BaseTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;

public class ExcaliburDriveTaskV2 extends BaseTask implements Task {

    private final ExcaliburDrive drive;

    private final double direction;
    private final double speed;
    private final double rotation;


    public ExcaliburDriveTaskV2(PaladinsOpMode opMode, double time, ExcaliburDrive drive, double direction, double rotation, double speed) {
        super(opMode, time);
        this.drive = drive;
        this.direction = direction;
        this.speed = speed;
        this.rotation = rotation;
    }

    @Override
    public void init() {
        super.init();
        drive.setEncoder(false);
    }

    @Override
    public void run() {

        double x = direction*speed;
        double y = -direction*speed;
        double turn = rotation;
        double theta = Math.atan2(y,x);
        double power = Math.hypot(x,y);
        double sin = Math.sin(theta - Math.PI/4);
        double cos = Math.cos(theta - Math.PI/4);
        double max = Math.max(Math.abs(sin), Math.abs(cos));
        double frontLeft = power * cos/max + turn;
        double frontRight = power * sin/max - turn;
        double backLeft = power * sin/max + turn;
        double backRight = power * cos/max - turn;
        if ((power + Math.abs(turn)) > 1) {
            frontLeft /= power + Math.abs(turn);
            frontRight /= power + Math.abs(turn);
            backLeft /= power + Math.abs(turn);
            backRight /= power + Math.abs(turn);
        }
        drive.setPower(frontLeft, frontRight, backLeft, backRight);
        drive.update();
        if (isFinished()) {
            drive.setPower(0, 0, 0, 0);
            drive.update();
            return;
        }

    }

}
