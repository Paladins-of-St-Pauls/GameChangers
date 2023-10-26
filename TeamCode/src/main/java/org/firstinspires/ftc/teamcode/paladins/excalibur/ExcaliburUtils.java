package org.firstinspires.ftc.teamcode.paladins.excalibur;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsComponent;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;

/**
 * Created by Shaun on 2/07/2017.
 */

public class ExcaliburUtils extends PaladinsComponent {

    final private CRServo RSpinner;
    final private CRServo LSpinner;
    final private DcMotor Harvester;
    public double spinnerSpeed;
    public double harvesterSpeed;

    public ExcaliburUtils(PaladinsOpMode opMode, CRServo LSpinner, CRServo RSpinner, DcMotor Harvester) {
        super(opMode);

        this.LSpinner = LSpinner;
        this.RSpinner = RSpinner;
        this.Harvester  = Harvester;

        spinnerSpeed = 0;
    }

    public void setPower(double spinnerSpeed, double harvesterSpeed) {
        spinnerSpeed = spinnerSpeed;
        harvesterSpeed = harvesterSpeed;
    }
    @SuppressLint("DefaultLocale")
    public void update() {
        RSpinner.setPower(spinnerSpeed);
        LSpinner.setPower(-spinnerSpeed);
        Harvester.setPower(harvesterSpeed);
    }

}
