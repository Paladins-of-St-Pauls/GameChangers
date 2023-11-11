package org.firstinspires.ftc.teamcode.paladins.excalibur;

import static java.lang.Boolean.TRUE;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.mecanum.NormalisedMecanumDrive;


@TeleOp(name = "ExcaliburMechanumDrive")
public class ExcaliburOpMode extends PaladinsOpMode {
    private ExcaliburConfiguration config;
    private NormalisedMecanumDrive drive;
    private ExcaliburUtils utils;

    public float liftAvg;

    @Override
    protected void onInit() {
        config = ExcaliburConfiguration.newConfig(hardwareMap, telemetry);
        drive = new NormalisedMecanumDrive(this, config.frontLeftMotor, config.frontRightMotor, config.backLeftMotor, config.backRightMotor, TRUE);
        utils = new ExcaliburUtils(this, config.Harvester, config.RightLiftMotor, config.LeftLiftMotor, config.BackLeftOutake, config.BackRightOutake, config.FrontLeftOutake, config.FrontRightOutake, config.PlaneShooter, config.RSensor, config.LSensor);

        utils.RightLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        utils.LeftLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        utils.LeftLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        utils.RightLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        utils.LeftLiftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        utils.RightLiftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    protected void activeLoop() throws InterruptedException {

        float xx = 0;
        float yy = 0;

        if (Math.abs(gamepad1.right_stick_x) > Math.abs(gamepad1.right_trigger)) {
            xx = gamepad1.right_stick_x;
        } else {
            if (Math.abs(gamepad1.right_trigger) > 0) {
                yy = -gamepad1.right_trigger / 2;
            } else {
                yy = gamepad1.left_trigger / 2;
            }
        }

        if (gamepad1.b) {
            xx = xx * 2;
            yy = yy * 2;
        }


        if (gamepad2.dpad_down) {
            utils.harvesterSpeed = -1;
        } else {
            utils.harvesterSpeed = 0;
        }
        if (gamepad2.dpad_up) {
            utils.harvesterSpeed = 1;
        }
        utils.liftSpeed = gamepad2.left_stick_y / 2;

        if (gamepad2.right_bumper) {
            utils.frontOutakeSpeed = -1;
        } else {
            utils.frontOutakeSpeed = 0;
        }
        if (gamepad2.left_bumper) {
            utils.frontOutakeSpeed = 1;
        }

        if (gamepad2.y) {
            utils.backOutakePos = 1;
        }
        if (gamepad2.x) {
            utils.backOutakePos = 0;
        }
        if (gamepad2.a) {
            utils.planeShooterPos = 1;
        }
        if (gamepad2.b) {
            utils.planeShooterPos = 0;
        }

        liftAvg = (Math.abs(utils.LeftLiftMotor.getCurrentPosition()) + Math.abs(utils.RightLiftMotor.getCurrentPosition())) / 2;


        if (liftAvg > 1875) {
            utils.backOutakePos = 0;
        } else if (liftAvg < 1875) {
            utils.backOutakePos = 1;
        }


        gamepad1.rumble(utils.LSensor.blue(), utils.RSensor.blue(), 5);
        gamepad1.rumble(utils.LSensor.red(), utils.RSensor.red(), 5);

        telemetry.addData("OutakeSpeed", utils.frontOutakeSpeed);
        telemetry.addData("HarvesterSpeed", utils.harvesterSpeed);
        telemetry.addData("LiftSpeed", utils.liftSpeed);
        telemetry.addData("LSensorB", utils.LSensor.blue());
        telemetry.addData("RSensorB", utils.RSensor.blue());
        telemetry.addData("LSensorR", utils.LSensor.red());
        telemetry.addData("RSensorR", utils.RSensor.red());
        telemetry.addData("Lift L", utils.LeftLiftMotor.getCurrentPosition());
        telemetry.addData("Lift R", utils.RightLiftMotor.getCurrentPosition());
        telemetry.addData("liftAvg", liftAvg);
        telemetry.addData("BLOutakePos", utils.BackLeftOutake.getPosition());
        telemetry.addData("RLOutakePos", utils.BackRightOutake.getPosition());


        drive.setSpeedXYR(-yy, xx, -gamepad1.left_stick_x / 2);

        drive.update();
        utils.update();
    }
}