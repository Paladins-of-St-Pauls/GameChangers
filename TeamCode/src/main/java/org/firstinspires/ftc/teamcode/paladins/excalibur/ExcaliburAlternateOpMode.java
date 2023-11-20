package org.firstinspires.ftc.teamcode.paladins.excalibur;

import static java.lang.Boolean.TRUE;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.mecanum.NormalisedMecanumDrive;
import org.opencv.core.Mat;


@TeleOp(name = "ExcaliburMechanumDriveV2")
public class ExcaliburAlternateOpMode extends PaladinsOpMode {
    private ExcaliburConfiguration config;
    private ExcaliburDrive drive;
    private ExcaliburUtils utils;

    public float liftAvg;

    @Override
    protected void onInit() {
        config = ExcaliburConfiguration.newConfig(hardwareMap, telemetry);
        drive = new ExcaliburDrive(this, config.backLeftMotor, config.backRightMotor, config.frontLeftMotor, config.frontRightMotor);
        utils = new ExcaliburUtils(this, config.Harvester, config.RightLiftMotor, config.LeftLiftMotor, config.BackLeftOutake, config.BackRightOutake, config.FrontLeftOutake, config.FrontRightOutake, config.PlaneShooter, config.RSensor, config.LSensor, config.indexMotor);

        config.frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        config.frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        config.backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        config.backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        utils.RightLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        utils.LeftLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        utils.LeftLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        utils.RightLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        utils.LeftLiftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        utils.RightLiftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    protected void activeLoop() throws InterruptedException {


        //drive

        float x = gamepad1.left_stick_x;
        float y = -gamepad1.left_stick_y;
        float turn = gamepad1.right_stick_x;
        double theta = Math.atan2(y,x);
        double power = Math.hypot(x,y);
        double sin = Math.sin(theta - Math.PI/4);
        double cos = Math.cos(theta - Math.PI/4);
        double max = Math.max(Math.abs(sin), Math.abs(cos));
        double frontLeft = power * cos/max + turn;
        double frontRight = power * sin/max - turn;
        double backLeft = power * sin/max + turn;
        double backRight = power * cos/max - turn;
        if ((power + Math.abs(turn)) > 1) {
            frontLeft /= power + Math.abs(turn);
            frontRight /= power + Math.abs(turn);
            backLeft /= power + Math.abs(turn);
            backRight /= power + Math.abs(turn);
        }
        drive.setPower(frontLeft, frontRight, backLeft, backRight);


        // utilities
        if (gamepad2.dpad_down) {
            utils.harvesterSpeed = -1;
        } else {
            utils.harvesterSpeed = 0;
        }
        if (gamepad2.dpad_up) {
            utils.harvesterSpeed = 1;
        }
        utils.liftSpeed = gamepad2.left_stick_y;
        if (gamepad2.right_bumper) {
            utils.frontOutakeSpeed = -1;
        } else {
            utils.frontOutakeSpeed = 0;
        }
        if (gamepad2.left_bumper) {
            utils.frontOutakeSpeed = 1;
        }
        if (gamepad2.a) {
            utils.planeShooterPos = 1;
        }
        if (gamepad2.b) {
            utils.planeShooterPos = 0;
        }
        liftAvg = (Math.abs(utils.LeftLiftMotor.getCurrentPosition()) + Math.abs(utils.RightLiftMotor.getCurrentPosition())) / 2;
        if (liftAvg > 1750) {
            utils.backOutakePos = 0;
        } else if (liftAvg < 1750) {
            utils.backOutakePos = 1;
        }
        if (gamepad2.y) {
            utils.indexPos = 100;
        } else if (gamepad2.x) {
            utils.indexPos = 0;
        }

        telemetry.addData("OutakeSpeed", utils.frontOutakeSpeed);
        telemetry.addData("HarvesterSpeed", utils.harvesterSpeed);
        telemetry.addData("LiftSpeed", utils.liftSpeed);
        telemetry.addData("liftAvg", liftAvg);

        drive.update();
        utils.update();
    }
}