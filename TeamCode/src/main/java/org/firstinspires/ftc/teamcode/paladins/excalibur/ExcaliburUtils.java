package org.firstinspires.ftc.teamcode.paladins.excalibur;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsComponent;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;

/**
 * Created by Shaun on 2/07/2017.
 */

public class ExcaliburUtils extends PaladinsComponent {

    final private CRServo RSpinner;
    final private CRServo LSpinner;
    public double spinnerSpeed;

    public ExcaliburUtils(PaladinsOpMode opMode, CRServo LSpinner, CRServo RSpinner) {
        super(opMode);

        this.LSpinner = LSpinner;
        this.RSpinner = RSpinner;

        spinnerSpeed = 0;
    }

    public void setPower(double frontLeft, double frontRight, double backLeft, double backRight) {
        spinnerSpeed = spinnerSpeed;
    }
    @SuppressLint("DefaultLocale")
    public void update() {
        RSpinner.setPower(spinnerSpeed);
        LSpinner.setPower(-spinnerSpeed);
    }

}
