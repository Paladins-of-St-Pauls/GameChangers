package org.firstinspires.ftc.teamcode.paladins.failure;

import static java.lang.Boolean.TRUE;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.mecanum.NormalisedMecanumDrive;

@TeleOp(name = "FailureDrive")
public class FailureOpMode extends PaladinsOpMode {
    private FailureConfiguration config;
    private NormalisedMecanumDrive drive;
    private FailureUtils utils;
    private double liftSpeed;
    private double extensionSpeed;


    @Override
    protected void onInit() {
        config = FailureConfiguration.newConfig(hardwareMap, telemetry);

        drive = new NormalisedMecanumDrive(this, config.frontLeftMotor, config.frontRightMotor, config.backLeftMotor, config.backRightMotor, TRUE);
        utils = new FailureUtils(this, config.rightLiftMotor, config.leftLiftMotor, config.extensionMotor, config.grabber, config.internalGrabber, config.internalGrabber2);

    }

    @Override
    protected void activeLoop() throws InterruptedException {

        float xx = 0;
        float yy = 0;

        if (Math.abs(gamepad1.right_stick_x) > Math.abs(gamepad1.right_trigger)) {
            xx = gamepad1.right_stick_x;
        } else {
            if (Math.abs(gamepad1.right_trigger) > 0) {
                yy = -gamepad1.right_trigger/2;
            } else {
                yy = gamepad1.left_trigger/2;
            }
        }

        if (gamepad1.b) {
            xx = xx/3;
            yy = yy/3;
        }
        if (gamepad2.a) {
            xx = xx*2;
            yy = yy*2;
        }

        utils.setLiftPower(gamepad2.left_stick_y);
        utils.setExtensionPower(gamepad2.right_stick_y);

        if (gamepad2.dpad_down) {
            utils.grab(1);
        }

        drive.setSpeedXYR(-yy, xx, -gamepad1.left_stick_x/2);
        drive.update();

    }
}