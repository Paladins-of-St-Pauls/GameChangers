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
        utils = new ExcaliburUtils(this, config.LSpinner, config.RSpinner);
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
            xx = xx*2;
            yy = yy*2;
        }


        if (gamepad1.x) {
            utils.spinnerSpeed = 1;
        } else {
            utils.spinnerSpeed = 0;
        }

            drive.setSpeedXYR(-yy, xx, -gamepad1.left_stick_x/2);
            drive.update();
            utils.update();
    }
}