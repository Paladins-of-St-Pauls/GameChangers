package org.firstinspires.ftc.teamcode.paladins.tasks;

import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.common.TankDrive;

public class LineTracerer extends BaseTask implements Task {

    private final TankDrive drive;
    private final double leftSpeed;
    private final double rightSpeed;
    private final ColorSensor leftSensor;
    private final ColorSensor rightSensor;

    public LineTracerer(PaladinsOpMode opMode, double time, TankDrive drive, double leftSpeed, double rightSpeed, ColorSensor colorSensor) {
        super(opMode, time);
        this.drive = drive;
        this.leftSpeed = leftSpeed;
        this.rightSpeed = rightSpeed;
        this.leftSensor = colorSensor;
        this.rightSensor = colorSensor;
    }

    @Override
    public void init() {
        super.init();
        drive.setEncoderMode(false);
    }

    @Override
    public void run() {
        if (isFinished()) {
            drive.setPower(0,0);
            drive.update();
            return;
        }
        if (leftSensor.green() > 210) {
            this.isFinished=false;
            drive.setPower(0.5,0.1);
            drive.update();
            return;
        }
        if (leftSensor.green() < 110) {
            this.isFinished=false;
            drive.setPower(0.1,0.5);
            drive.update();
            return;
        }
        drive.setPower(leftSpeed, rightSpeed);
        drive.update();
    }

}
