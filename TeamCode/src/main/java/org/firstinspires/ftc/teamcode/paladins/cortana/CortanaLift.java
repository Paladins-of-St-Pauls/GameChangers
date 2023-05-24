package org.firstinspires.ftc.teamcode.paladins.cortana;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsComponent;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;

/**
 * Created by Shaun on 2/07/2017.
 */

public class CortanaLift extends PaladinsComponent {
    public static int[] lift_positions = {0, 150, 250, 350, 450, 550, 600, 2150, 4100, 5750 };
    final private PaladinsOpMode opMode;
    final public DcMotor liftMotor;
    final public TouchSensor liftSensor;
    final public Servo liftClamp;


    public double liftPower;

    private int liftIndex = 0;



    public CortanaLift(PaladinsOpMode opMode, DcMotor liftMotor, Servo liftClamp, TouchSensor liftSensor) {
        super(opMode);
        this.opMode = opMode;
        this.liftSensor = liftSensor;
        this.liftClamp = liftClamp;
        this.liftMotor = liftMotor;
        liftClamp.setPosition(0.2);
        liftClamp.scaleRange(0, 1);
        liftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setTargetPosition(0);
        liftPower = 0;
    }

    public void liftUp() {
        liftIndex ++;
        if(liftIndex > lift_positions.length -1) {
            liftIndex = lift_positions.length -1;
        }
        liftMotor.setTargetPosition(lift_positions[liftIndex]);
    }

    public void liftDown() {
        liftIndex --;
        if(liftIndex < 0) {
            liftIndex = 0;
        }
        liftMotor.setTargetPosition(lift_positions[liftIndex]);
    }

    public void highGoal() {
        liftMotor.setTargetPosition(lift_positions[9]);
        liftIndex = 9;
    }
    public void midGoal() {
        liftMotor.setTargetPosition(lift_positions[8]);
        liftIndex = 8;
    }
    public void lowGoal() {
        liftMotor.setTargetPosition(lift_positions[7]);
        liftIndex = 7;
    }

    public void bottomOut() {
        liftMotor.setTargetPosition(lift_positions[0]);
        liftIndex = 0;
    }
    public void liftClampOpen() {
        liftClamp.setPosition(0.2);
    }
    public void liftClampClose() {
        liftClamp.setPosition(0.7);
    }

//    public void liftReset() {
//        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        liftMotor.setPower(0);
//    }

    public void setPower(double power) {
        liftPower = power;

    }

    /*
     * Update the motor power based on the gamepad state
     */
    @SuppressLint("DefaultLocale")
    public void update() {
        getOpMode().telemetry.addLine(String.format("Lift Position: %d",liftMotor.getCurrentPosition()));
//
//        if(liftSensor.isPressed()) {
//            liftMotor
//        }
//        else {
            liftMotor.setPower((liftPower));
//            liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

//    }

    public boolean isFinished() {
        return !(liftMotor.isBusy());
    }

}
