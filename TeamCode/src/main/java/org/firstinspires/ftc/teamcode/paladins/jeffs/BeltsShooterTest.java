package org.firstinspires.ftc.teamcode.paladins.jeffs;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.common.TankDrive;

@TeleOp(name = "BeltsShooterTest")
public class BeltsShooterTest extends PaladinsOpMode {
    private JeffsBotConfiguration config;
    private TankDrive drive;

    @Override
    protected void onInit() {
        config = JeffsBotConfiguration.newConfig(hardwareMap, telemetry);

        drive = new TankDrive(this, gamepad1, config.leftMotor, config.rightMotor);
    }

    @Override
    protected void activeLoop() throws InterruptedException {
        drive.setPower(-gamepad1.left_stick_y, -gamepad1.right_stick_y);
        drive.update();

        if(gamepad1.left_trigger > 0) {
        config.servo.setPower(gamepad1.left_trigger);
        } else if (gamepad1.right_trigger > 0) {
            config.servo.setPower(-gamepad1.right_trigger);
        } else {
            config.servo.setPower(0);
        }
    }
}