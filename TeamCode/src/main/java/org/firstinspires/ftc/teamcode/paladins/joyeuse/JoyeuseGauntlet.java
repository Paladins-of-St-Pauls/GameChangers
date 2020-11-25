package org.firstinspires.ftc.teamcode.paladins.joyeuse;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsComponent;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;

/**
 * Created by Caleb on 30/10/2020.
 */

public class JoyeuseGauntlet extends PaladinsComponent {
    final private CRServo wgArm;
    final private CRServo wgHand;
    final private CRServo wgGripper;

    final private PaladinsOpMode opMode;



    public JoyeuseGauntlet(PaladinsOpMode opMode, CRServo wgArm, CRServo wgHand, CRServo wgGripper) {
        super(opMode);

        this.opMode = opMode;

        this.wgArm = wgArm;
        this.wgHand = wgHand;
        this.wgGripper = wgGripper;
    }

    /*
     * Update the motor power based on the gamepad state
     */
    @SuppressLint("DefaultLocale")
    public void setArmPower(double power) {
        wgArm.setPower(power);
    }

    public void setHandPower(double power) {
        wgHand.setPower(power);
    }

    public void setGripperPower(double power) {
        wgGripper.setPower(power);
    }
}