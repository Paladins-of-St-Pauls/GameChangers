package org.firstinspires.ftc.teamcode.paladins.durandal;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsComponent;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.tensorflow.lite.support.image.TensorImage;

/**
 * Created by Shaun on 2/07/2017.
 */

public class DurandalLift extends PaladinsComponent {
    private static int[] lift_positions = {0, 100, 300, 500, 600};

    final private DcMotor liftMotor;
    final private TouchSensor liftSwitch;


    private double liftPower;

    private int liftIndex = 0;



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

    public boolean isFinished() {
        return !(liftMotor.isBusy());
    }

}
