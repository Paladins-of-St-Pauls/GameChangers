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
    private int greenlimit = 4000 ;

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

        opMode.telemetry.addData("stopp",String.format("red: %d   gren %d   bloo %d", rightSensor.red(),rightSensor.green(),rightSensor.blue()));

        if (isFinished()) {
            opMode.telemetry.addData("stopp","isFinished");
            drive.setPower(0,0);
            drive.update();
            return;

        }

        //if (true) return;

        if (rightSensor.green() > greenlimit && leftSensor.green() > greenlimit) {
            this.isFinished=true;
            opMode.telemetry.addData("stopp","both Green");
            drive.setPower(0.5,0.5);
            drive.update();
            return;

        }
        if (leftSensor.green() > greenlimit) {
            opMode.telemetry.addData("stopp","leftSensor Green");
            drive.setPower(0.2,-0.3);
            drive.update();
            return;

        }
        if (rightSensor.green() > greenlimit) {
            opMode.telemetry.addData("stopp","rightSensor Green");
            drive.setPower(-0.3,0.2);
            drive.update();
            return;


        }
        opMode.telemetry.addData("stopp","keep goingb");


        drive.setPower(-leftSpeed, -rightSpeed);
        drive.update();
    }

}
