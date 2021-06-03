package org.firstinspires.ftc.teamcode.paladins.mecanum;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;

@TeleOp(name = "SkyDrive")
public class SkyDriveOpMode extends PaladinsOpMode {
    private SkystoneConfiguration config;
    private NormalisedMecanumDrive mecanumDrive = null;
    @Override
    protected void onInit() {
        config = SkystoneConfiguration.newConfig(hardwareMap, telemetry);

        try {
            mecanumDrive = new NormalisedMecanumDrive(this,
                    config.frontLeftMotor, config.frontRightMotor,
                    config.backLeftMotor, config.backRightMotor,
                    false);
        } catch (Exception e) {
            telemetry.addLine("Failed to initialise mecanum drive");
        }
    }

    @Override
    protected void activeLoop() throws InterruptedException {

        if (mecanumDrive != null) {
            if(gamepad1.right_trigger > 0) {
                config.frontLeftMotor.setPower(-gamepad1.right_trigger);
                config.frontRightMotor.setPower(-gamepad1.right_trigger);
                config.backLeftMotor.setPower(gamepad1.right_trigger);
                config.backRightMotor.setPower(gamepad1.right_trigger);
            } else if(gamepad1.left_trigger > 0) {
                config.frontLeftMotor.setPower(gamepad1.left_trigger);
                config.frontRightMotor.setPower(gamepad1.left_trigger);
                config.backLeftMotor.setPower(-gamepad1.left_trigger);
                config.backRightMotor.setPower(-gamepad1.left_trigger);
            } else {
                mecanumDrive.setSpeedXYR(-gamepad1.left_stick_y, -gamepad1.left_stick_x, gamepad1.right_stick_x);
                mecanumDrive.update();
            }

        }
    }
}
