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

    public boolean isFinished() {
        return !(liftMotor.isBusy());
    }

}
