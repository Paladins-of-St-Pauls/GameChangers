package org.firstinspires.ftc.teamcode.paladins.joyeuse;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsComponent;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;

/**
 * Created by Caleb on 30/10/2020.
 */

public class JoyeuseGauntlet extends PaladinsComponent {
    final private Servo wgArm;
    final private Servo wgHand;
    final private Servo wgHook;

    final private PaladinsOpMode opMode;



    public JoyeuseGauntlet(PaladinsOpMode opMode, Servo wgArm, Servo wgHand, Servo wgHook) {
        super(opMode);

        this.opMode = opMode;

        this.wgArm = wgArm;
        this.wgHand = wgHand;
        this.wgHook = wgHook;
    }

    /*
     * Update the motor power based on the gamepad state
     */
    @SuppressLint("DefaultLocale")
    public void setArmPos(double pos) {
        wgArm.setPosition(pos);
    }

    public void setHandPos(double pos) {
        wgHand.setPosition(pos);
    }

    public void setHookPos(double pos) {
        wgHook.setPosition(pos);
    }
}