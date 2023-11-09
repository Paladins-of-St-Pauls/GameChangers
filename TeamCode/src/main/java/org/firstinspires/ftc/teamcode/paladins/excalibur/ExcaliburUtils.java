package org.firstinspires.ftc.teamcode.paladins.excalibur;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsComponent;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;

/**
 * Created by Shaun on 2/07/2017.
 */

public class ExcaliburUtils extends PaladinsComponent {

    final private DcMotor Harvester;
    final private DcMotor LeftLiftMotor;
    final private DcMotor RightLiftMotor;

    final private Servo BackLeftOutake;
    final private Servo BackRightOutake;
    final private CRServo FrontLeftOutake;
    final private CRServo FrontRightOutake;

    final private Servo PlaneShooter;


    public double harvesterSpeed;
    public double backOutakePos;
    public double frontOutakeSpeed;
    public double liftSpeed;

    public double planeShooterPos;

    public ExcaliburUtils(PaladinsOpMode opMode, DcMotor Harvester, DcMotor LeftLiftMotor, DcMotor RightLiftMotor, Servo BackLeftOutake, Servo BackRightOutake, CRServo FrontLeftOutake, CRServo FrontRightOutake, Servo PlaneShooter) {
        super(opMode);

        this.Harvester = Harvester;
        this.LeftLiftMotor = LeftLiftMotor;
        this.RightLiftMotor = RightLiftMotor;
        this.BackLeftOutake = BackLeftOutake;
        this.BackRightOutake = BackRightOutake;
        this.FrontLeftOutake = FrontLeftOutake;
        this.FrontRightOutake = FrontRightOutake;
        this.PlaneShooter = PlaneShooter;
    }

    public void setPower(double harvesterSpeed, double liftSpeed, double backOutakeSpeed, double frontOutakeSpeed) {
        harvesterSpeed = harvesterSpeed;
        liftSpeed = liftSpeed;
        frontOutakeSpeed = frontOutakeSpeed;
        backOutakeSpeed = backOutakeSpeed;
    }

    @SuppressLint("DefaultLocale")
    public void update() {
        Harvester.setPower(harvesterSpeed);
        LeftLiftMotor.setPower(liftSpeed);
        RightLiftMotor.setPower(-liftSpeed);

        BackLeftOutake.setPosition(backOutakePos);
        BackRightOutake.setPosition(backOutakePos);
        PlaneShooter.setPosition(planeShooterPos);

        FrontLeftOutake.setPower(frontOutakeSpeed);
        FrontRightOutake.setPower(-frontOutakeSpeed);

    }
}
