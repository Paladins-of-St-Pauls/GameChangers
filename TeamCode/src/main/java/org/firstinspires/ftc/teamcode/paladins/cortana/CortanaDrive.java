package org.firstinspires.ftc.teamcode.paladins.cortana;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsComponent;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;

/**
 * Created by Shaun on 2/07/2017.
 */

public class CortanaDrive extends PaladinsComponent {
    final private DcMotor backLeftMotor;
    final private DcMotor backRightMotor;
    final private DcMotor frontRightMotor;
    final private DcMotor frontLeftMotor;

    private double frontLeftPower;
    private double frontRightPower;
    private double backLeftPower;
    private double backRightPower;


    public CortanaDrive(PaladinsOpMode opMode, DcMotor backLeftMotor, DcMotor backRightMotor, DcMotor frontLeftMotor, DcMotor frontRightMotor) {
        super(opMode);

        this.backLeftMotor = backLeftMotor;
        this.backRightMotor = backRightMotor;
        this.frontLeftMotor = frontLeftMotor;
        this.frontRightMotor = frontRightMotor;

        frontLeftPower = 0;
        frontRightPower = 0;
        backLeftPower = 0;
        backRightPower = 0;
    }

    public void setPower(double frontLeft, double frontRight, double backLeft, double backRight) {
        frontRightPower = -frontRight;
        frontLeftPower = -frontLeft;
        backRightPower = -backRight;
        backLeftPower = -backLeft;
    }

    public void setToBrake() {
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setToFloat() {
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void setEncoder(boolean encoder) {
        if (encoder) {
            backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        } else {
            frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    /*
     * Update the motor power based on the gamepad state
     */
    @SuppressLint("DefaultLocale")
    public void update() {
        frontLeftMotor.setPower((frontLeftPower));
        frontRightMotor.setPower((frontRightPower));
        backLeftMotor.setPower((backLeftPower));
        backRightMotor.setPower((backRightPower));
    }

    public boolean isFinished() {
        return !(frontLeftMotor.isBusy() || frontRightMotor.isBusy() || backLeftMotor.isBusy() || backRightMotor.isBusy());
    }

    public boolean targetPositionReached() {
        return (!frontLeftMotor.isBusy() || frontRightMotor.isBusy() || backLeftMotor.isBusy() || backRightMotor.isBusy());
    }

    public void setTargetPosition(double backLeftDistance, double backRightDistance, double frontLeftDistance, double frontRightDistance) {

        int newFrontLeftTarget;
        int newFrontRightTarget;
        int newBackLeftTarget;
        int newBackRightTarget;
        newFrontLeftTarget = frontLeftMotor.getCurrentPosition() + (int) frontLeftDistance;
        newFrontRightTarget =  frontRightMotor.getCurrentPosition() + (int) frontRightDistance;
        newBackLeftTarget =  backLeftMotor.getCurrentPosition() + (int) backLeftDistance;
        newBackRightTarget =  backRightMotor.getCurrentPosition() + (int) backRightDistance;
        frontLeftMotor.setTargetPosition(newFrontLeftTarget);
        frontRightMotor.setTargetPosition(newFrontRightTarget);
        backLeftMotor.setTargetPosition(newBackLeftTarget);
        backRightMotor.setTargetPosition(newBackRightTarget);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
}
