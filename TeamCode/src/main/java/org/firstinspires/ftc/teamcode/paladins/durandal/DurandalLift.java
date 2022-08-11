package org.firstinspires.ftc.teamcode.paladins.durandal;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsComponent;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;

/**
 * Created by Shaun on 2/07/2017.
 */

public class DurandalLift extends PaladinsComponent {
    private static float[] power_curve =
            {0.00f, 0.2f, 0.25f, 0.3f, 0.5f, 0.7f, 0.8f, 1.0f};
    private static float[] steer_curve =
            {0.00f, 0.2f, 0.25f, 0.3f, 0.35f, 0.4f, 0.5f, 1.0f};
    private static int[] lift_positions = {0, 100, 300, 500, 700};

    final private DcMotor liftMotor;
    final private TouchSensor liftSwitch;


//    final private Gamepad gamepad;
//    final private Telemetry.Item leftPowerItem;
//    final private Telemetry.Item rightPowerItem;
//    boolean encoderMode = false;

    private double liftPower;

    private int liftIndex = 0;




//    private double leftCm;
//    private double rightCm;
//    private double countsPerCm;

    public DurandalLift(PaladinsOpMode opMode, DcMotor liftMotor, TouchSensor liftSwitch) {
        super(opMode);

        this.liftSwitch = liftSwitch;

        this.liftMotor = liftMotor;

        liftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        liftMotor.setTargetPosition(0);



        liftPower = 0;


//        leftPowerItem = getOpMode().telemetry.addData("Left power", "%.2f", 0.0f);
//        leftPowerItem.setRetained(true);
//        rightPowerItem = getOpMode().telemetry.addData("Right power", "%.2f", 0.0f);
//        rightPowerItem.setRetained(true);
    }

//    public JoyeuseDrive(PaladinsOpMode opMode, Gamepad gamepad, DcMotor leftMidMotor, DcMotor rightMidMotor) {
//        this(opMode, leftMidMotor, leftBackMotor, rightMidMotor, rightBackMotor);
//    }

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

    private void liftReset() {
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    public void setPower(double power) {
        liftPower = power;

    }

    /*
     * Update the motor power based on the gamepad state
     */
    @SuppressLint("DefaultLocale")
    public void update() {
        getOpMode().telemetry.addLine(String.format("pos: %d",liftMotor.getCurrentPosition()));

        if(liftIndex == 0 && liftSwitch.isPressed()) {
            liftReset();
        }
        else {
            liftMotor.setPower((liftPower));
            liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

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
