package org.firstinspires.ftc.teamcode.paladins.tasks;

import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.common.TankDrive;
import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseDrive;

public class TwoSensorTracerTask extends BaseTask implements Task {

    private final JoyeuseDrive drive;
    private final double leftSpeed;
    private final double rightSpeed;
    private final ColorSensor leftSensor;
    private final ColorSensor rightSensor;

    public TwoSensorTracerTask(PaladinsOpMode opMode, double time, JoyeuseDrive drive, double leftSpeed, double rightSpeed, ColorSensor leftSensor, ColorSensor rightSensor) {
        super(opMode, time);
        this.drive = drive;
        this.leftSpeed = leftSpeed;
        this.rightSpeed = rightSpeed;
        this.leftSensor = leftSensor;
        this.rightSensor = rightSensor;


    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void run() {
        if (isFinished()) {
            drive.setPower(0,0);
            drive.update();
            return;

        }
        if (rightSensor.green() > 180 && leftSensor.green() > 180) {
            this.isFinished=true;
            opMode.telemetry.addData("stopp","stop");
            drive.setPower(-0.5,-0.5);
            drive.update();
            return;

        }
        if (leftSensor.green() > 220) {
            drive.setPower(-0.4,0.5);
            drive.update();
            return;

        }
        if (rightSensor.green() > 220) {
            drive.setPower(0.5,-0.4);
            drive.update();
            return;


        }


        drive.setPower(leftSpeed, rightSpeed);
        drive.update();
    }

}
