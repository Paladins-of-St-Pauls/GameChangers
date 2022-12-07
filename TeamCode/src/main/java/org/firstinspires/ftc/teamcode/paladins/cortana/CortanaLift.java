package org.firstinspires.ftc.teamcode.paladins.cortana;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsComponent;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.tensorflow.lite.support.image.TensorImage;

/**
 * Created by Shaun on 2/07/2017.
 */

public class CortanaLift extends PaladinsComponent {
    public static int[] lift_positions = {0, 2000, 4000, 5600 };

    final DcMotor liftMotor;
    final private TouchSensor liftSensor;
    final private Servo liftClamp;


    private double liftPower;

    private int liftIndex = 0;



    public CortanaLift(PaladinsOpMode opMode, DcMotor liftMotor, Servo liftClamp, TouchSensor liftSensor) {
        super(opMode);

        this.liftSensor = liftSensor;
        this.liftClamp = liftClamp;
        this.liftMotor = liftMotor;

        liftClamp.setPosition(0);
        liftClamp.scaleRange(0, 1);

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

    public void liftClampOpen() {
        liftClamp.setPosition(0);
    }
    public void liftClampClose() {
        liftClamp.setPosition(0.7);
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
        getOpMode().telemetry.addLine(String.format("Lift Position: %d",liftMotor.getCurrentPosition()));

        if(liftIndex == 0 && liftSensor.isPressed()) {
            liftReset();
        }
        else {
            liftMotor.setPower((liftPower));
            liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

    }

    public boolean isFinished() {
        return !(liftMotor.isBusy());
    }

}
