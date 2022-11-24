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
    final private DcMotor liftMotor;
    final private Servo liftClamp;


//    final private Gamepad gamepad;
//    final private Telemetry.Item leftPowerItem;
//    final private Telemetry.Item rightPowerItem;
//    boolean encoderMode = false;

    private double liftPower;





//    private double leftCm;
//    private double rightCm;
//    private double countsPerCm;

    public CortanaLift(PaladinsOpMode opMode, DcMotor liftMotor, Servo liftClamp) {
        super(opMode);

        this.liftMotor = liftMotor;
        this.liftClamp = liftClamp;

        // Init Lift Motor
        liftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftPower = 0;

        // Init Lift Clamp
        liftClamp.setPosition(0);
        liftClamp.scaleRange(0, 1);
    }


    public void liftUp() {
        liftPower = 1;
    }

    public void liftDown() {
        liftPower = -1;
    }
    public void liftBrake() {
        liftPower = 0;
    }
    public void liftClampOpen() {
        liftClamp.setPosition(0);
    }
    public void liftClampClose() {
        liftClamp.setPosition(0.7);
    }
    public void setPower(double power) {
        liftPower = power;

    }

    /*
     * Update the motor power based on the gamepad state
     */
    @SuppressLint("DefaultLocale")
    public void update() {
        getOpMode().telemetry.addLine(String.format("lift pow: %f",liftMotor.getPower()));
        liftMotor.setPower((liftPower));
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
        return !(liftMotor.isBusy());
    }

}
