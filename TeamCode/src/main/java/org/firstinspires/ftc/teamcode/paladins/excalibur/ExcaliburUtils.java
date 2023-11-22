package org.firstinspires.ftc.teamcode.paladins.excalibur;

import android.annotation.SuppressLint;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsComponent;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;

/**
 * Created by Shaun on 2/07/2017.
 */

public class ExcaliburUtils extends PaladinsComponent {

    final private DcMotor Harvester;
    final DcMotor LeftLiftMotor;
    final DcMotor RightLiftMotor;


    final private CRServo FrontOutake;

    final private Servo PlaneShooter;

    final public RevColorSensorV3 RSensor;
    final public RevColorSensorV3 LSensor;

    final DcMotor indexMotor;


    public double harvesterSpeed;
    public double backOutakePos;
    public double frontOutakeSpeed;
    public double liftSpeed;

    public double indexSpeed;

    public double planeShooterPos;

    public ExcaliburUtils(PaladinsOpMode opMode, DcMotor Harvester, DcMotor LeftLiftMotor, DcMotor RightLiftMotor, CRServo FrontOutake, Servo PlaneShooter, RevColorSensorV3 RSensor, RevColorSensorV3 LSensor, DcMotor indexMotor) {
        super(opMode);

        this.Harvester = Harvester;
        this.LeftLiftMotor = LeftLiftMotor;
        this.RightLiftMotor = RightLiftMotor;
        this.FrontOutake = FrontOutake;
        this.PlaneShooter = PlaneShooter;
        this.RSensor = RSensor;
        this.LSensor = LSensor;
        this.indexMotor = indexMotor;
    }

    public void setPower(double harvesterSpeed, double liftSpeed, double backOutakeSpeed, double frontOutakeSpeed, double indexSpeed) {
        harvesterSpeed = harvesterSpeed;
        liftSpeed = liftSpeed;

        frontOutakeSpeed = frontOutakeSpeed;
        backOutakeSpeed = backOutakeSpeed;
        indexSpeed = indexSpeed;

        indexMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @SuppressLint("DefaultLocale")
    public void update() {
        Harvester.setPower(harvesterSpeed);
        LeftLiftMotor.setPower(liftSpeed/3);
        RightLiftMotor.setPower(-(liftSpeed/3));

        indexMotor.setPower(indexSpeed);

        PlaneShooter.setPosition(planeShooterPos);

        FrontOutake.setPower(frontOutakeSpeed);

    }
}
