package org.firstinspires.ftc.teamcode.paladins.excalibur;

import static java.lang.Boolean.TRUE;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.mecanum.NormalisedMecanumDrive;


@TeleOp(name = "ExcaliburMechanumDrive")
public class ExcaliburOpMode extends PaladinsOpMode {
    private ExcaliburConfiguration config;
    private NormalisedMecanumDrive drive;
    private ExcaliburUtils utils;

    @Override
    protected void onInit() {
        config = ExcaliburConfiguration.newConfig(hardwareMap, telemetry);
        drive = new NormalisedMecanumDrive(this, config.frontLeftMotor, config.frontRightMotor, config.backLeftMotor, config.backRightMotor, TRUE);
        utils = new ExcaliburUtils(this, config.Harvester, config.RightLiftMotor, config.LeftLiftMotor, config.BackLeftOutake, config.BackRightOutake, config.FrontLeftOutake, config.FrontRightOutake, config.PlaneShooter);
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
        utils.liftSpeed = gamepad2.left_stick_y;

        if (gamepad2.right_bumper) {
            utils.frontOutakeSpeed = -1;
        }
        else {
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
        if (gamepad2.dpad_left) {
            utils.planeShooterPos = 1;
        }
        if (gamepad2.dpad_right) {
            utils.planeShooterPos = 0;
        }
        telemetry.addData("OutakeSpeed", utils.frontOutakeSpeed);
        telemetry.addData("HarvesterSpeed", utils.harvesterSpeed);
        telemetry.addData("LiftSpeed", utils.liftSpeed);



        drive.setSpeedXYR(-yy, xx, -gamepad1.left_stick_x / 2);

        drive.update();
        utils.update();
    }
}