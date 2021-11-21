package org.firstinspires.ftc.teamcode.paladins.durandal;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsComponent;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;

/**
 * Created by Shaun on 2/07/2017.
 */

public class DurandalDrive extends PaladinsComponent {
    private static float[] power_curve =
            {0.00f, 0.2f, 0.25f, 0.3f, 0.5f, 0.7f, 0.8f, 1.0f};
    private static float[] steer_curve =
            {0.00f, 0.2f, 0.25f, 0.3f, 0.35f, 0.4f, 0.5f, 1.0f};

    final private DcMotor leftMotor;
    final private DcMotor rightMotor;


//    final private Gamepad gamepad;
//    final private Telemetry.Item leftPowerItem;
//    final private Telemetry.Item rightPowerItem;
//    boolean encoderMode = false;

    private double leftPower;
    private double rightPower;



//    private double leftCm;
//    private double rightCm;
//    private double countsPerCm;

    public DurandalDrive(PaladinsOpMode opMode, DcMotor leftMotor, DcMotor rightMotor) {
        super(opMode);

        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;


//        leftMidMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
////        leftBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightMidMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
////        rightBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
////
//        leftMidMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
////        leftBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightMidMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
////        rightBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftPower = 0;
        rightPower = 0;


//        leftPowerItem = getOpMode().telemetry.addData("Left power", "%.2f", 0.0f);
//        leftPowerItem.setRetained(true);
//        rightPowerItem = getOpMode().telemetry.addData("Right power", "%.2f", 0.0f);
//        rightPowerItem.setRetained(true);
    }

//    public JoyeuseDrive(PaladinsOpMode opMode, Gamepad gamepad, DcMotor leftMidMotor, DcMotor rightMidMotor) {
//        this(opMode, leftMidMotor, leftBackMotor, rightMidMotor, rightBackMotor);
//    }



    public void setPower(double left, double right) {
        leftPower = -left;
        rightPower = -right;

    }

    public void setToBrake() {
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setToFloat() {
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void setEncoder(boolean encoder) {
        if (encoder) {
            leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


//            leftBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//            rightBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        } else {
            leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


//            leftBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//            rightBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }
    }

    /*
     * Update the motor power based on the gamepad state
     */
    @SuppressLint("DefaultLocale")
    public void update() {
        leftMotor.setPower((leftPower));
        rightMotor.setPower((rightPower));




        getOpMode().telemetry.addLine(String.format("%d, %d", leftMotor.getCurrentPosition(), rightMotor.getCurrentPosition()));
    }

    /**
     * The  DC motors are scaled to make it easier to control them at slower speeds
     * The clip method guarantees the value never exceeds the range 0-1.
     */


//    private double scaleTriggerPower(double power) {
//
//        // Ensure the values are legal.
//        double clipped_power = Range.clip(power, -1, 1);
//
//        // Remember if this is positive or negative
//        double sign = Math.signum(clipped_power);
//
//        // Work only with positive numbers for simplicity
//        double abs_power = Math.abs(clipped_power);
//
//        // Map the power value [0..1.0] to a power curve index
//        int index = (int) (abs_power * (power_curve.length - 1));
//
//        double scaled_power = sign * power_curve[index];
//
//        return scaled_power;
//    }
//
//    private float scaleSteerPower(float p_power) {
//
//        // Ensure the values are legal.
//        float clipped_power = Range.clip(p_power, -1, 1);
//
//        // Remember if this is positive or negative
//        float sign = Math.signum(clipped_power);
//
//        // Work only with positive numbers for simplicity
//        float abs_power = Math.abs(clipped_power);
//
//        // Map the power value [0..1.0] to a power curve index
//        int index = (int) (abs_power * (steer_curve.length - 1));
//
//        float scaled_power = sign * steer_curve[index];
//
//        return scaled_power;
//
//    }

    public boolean isFinished() {
        return !(leftMotor.isBusy() || rightMotor.isBusy());
    }

    public boolean targetPositionReached() {
        return !(leftMotor.isBusy() || rightMotor.isBusy());
    }

    public void setTargetPosition(double leftDistance, double rightDistance) {

        int newLeftTarget;
        int newRightTarget;
        newLeftTarget = leftMotor.getCurrentPosition() + (int) leftDistance;
        newRightTarget =  rightMotor.getCurrentPosition() + (int) rightDistance;
        leftMotor.setTargetPosition(newLeftTarget);
        rightMotor.setTargetPosition(newRightTarget);

        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
}
