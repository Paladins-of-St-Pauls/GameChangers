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

    final private DcMotor Harvester;
    final private DcMotor LeftLiftMotor;
    final private DcMotor RightLiftMotor;
    public double harvesterSpeed;
    public double liftSpeed;

    public ExcaliburUtils(PaladinsOpMode opMode, DcMotor Harvester, DcMotor LeftLiftMotor, DcMotor RightLiftMotor) {
        super(opMode);

        this.Harvester  = Harvester;
        this.LeftLiftMotor = LeftLiftMotor;
        this.RightLiftMotor = RightLiftMotor;
    }

    public void setPower(double harvesterSpeed, double liftSpeed) {
        harvesterSpeed = harvesterSpeed;
        liftSpeed = liftSpeed;
    }
    @SuppressLint("DefaultLocale")
    public void update() {
        Harvester.setPower(harvesterSpeed);
        LeftLiftMotor.setPower(liftSpeed);
        RightLiftMotor.setPower(-liftSpeed);
    }
}
