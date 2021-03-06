package org.firstinspires.ftc.teamcode.paladins.joyeuse;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsComponent;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;

/**
 * Created by Caleb on 30/10/2020.
 */

public class JoyeuseShoot extends PaladinsComponent {
    private static float[] power_curve =
            {0.00f, 0.2f, 0.25f, 0.3f, 0.5f, 0.7f, 0.8f, 1.0f};
    private static float[] steer_curve =
            {0.00f, 0.2f, 0.25f, 0.3f, 0.35f, 0.4f, 0.5f, 1.0f};

    final private DcMotor leftShooterMotor;
    final private DcMotor rightShooterMotor;

    final private PaladinsOpMode opMode;



    public JoyeuseShoot(PaladinsOpMode opMode, DcMotor leftShooterMotor, DcMotor rightShooterMotor) {
        super(opMode);

        this.opMode = opMode;

        this.leftShooterMotor = leftShooterMotor;
        this.rightShooterMotor = rightShooterMotor;

//        leftShooterMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightShooterMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        leftShooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightShooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftShooterMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightShooterMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /*
     * Update the motor power based on the gamepad state
     */
    @SuppressLint("DefaultLocale")
    public void setPower(double power) {
            leftShooterMotor.setPower(power);
            rightShooterMotor.setPower(power);
    }

//    public void update() {
//        opMode.telemetry.addData("Left Current Pos.", leftShooterMotor.getCurrentPosition());
//        opMode.telemetry.addData("Right Current Pos.", rightShooterMotor.getCurrentPosition());
//    }

    /**
     * The  DC motors are scaled to make it easier to control them at slower speeds
     * The clip method guarantees the value never exceeds the range 0-1.
     */


    public boolean isFinished() {
        return !(leftShooterMotor.isBusy() || rightShooterMotor.isBusy());
    }
}
