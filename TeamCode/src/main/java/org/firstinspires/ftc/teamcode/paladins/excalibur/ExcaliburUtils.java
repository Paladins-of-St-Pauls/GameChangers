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
    final private DcMotor LeftLiftMotor;
    final private DcMotor RightLiftMotor;
    public double spinnerSpeed;
    public double harvesterSpeed;
    public double liftSpeed;

    public ExcaliburUtils(PaladinsOpMode opMode, CRServo LSpinner, CRServo RSpinner, DcMotor Harvester, DcMotor LeftLiftMotor, DcMotor RightLiftMotor) {
        super(opMode);

        this.LSpinner = LSpinner;
        this.RSpinner = RSpinner;
        this.Harvester  = Harvester;
        this.LeftLiftMotor = LeftLiftMotor;
        this.RightLiftMotor = RightLiftMotor;


        spinnerSpeed = 0;

    }

    public void setPower(double spinnerSpeed, double harvesterSpeed, double liftSpeed) {
        spinnerSpeed = spinnerSpeed;
        harvesterSpeed = harvesterSpeed;
        liftSpeed = liftSpeed;
    }
    @SuppressLint("DefaultLocale")
    public void update() {
        RSpinner.setPower(spinnerSpeed);
        LSpinner.setPower(-spinnerSpeed);
        Harvester.setPower(harvesterSpeed);
        LeftLiftMotor.setPower(liftSpeed);
        RightLiftMotor.setPower(-liftSpeed);
    }
}
