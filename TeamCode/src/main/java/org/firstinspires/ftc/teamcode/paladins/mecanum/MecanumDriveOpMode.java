package org.firstinspires.ftc.teamcode.paladins.mecanum;

import static java.lang.Boolean.TRUE;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseConfiguration;
import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseDrive;
import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseGauntlet;
import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseIntake;
import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseShoot;
import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseTriggerSteerDrive;

@TeleOp(name = "MecanumTeleOp")
public class MecanumDriveOpMode extends PaladinsOpMode {
    private MecanumConfiguration config;
    private NormalisedMecanumDrive drive;

    private boolean arm;
    private boolean hand;
    private boolean hook;

    private String message = "Hi - This is a message";

    @Override
    protected void onInit() {
        config = MecanumConfiguration.newConfig(hardwareMap, telemetry);

        drive = new NormalisedMecanumDrive(this, config.leftMidMotor, config.rightMidMotor, config.leftBackMotor, config.rightBackMotor, TRUE);
    }

    @Override
    protected void activeLoop() throws InterruptedException {

        float xx = 0;
        float yy = 0;

        if (Math.abs(gamepad1.left_stick_x) > Math.abs(gamepad1.left_stick_y)) {
            xx = gamepad1.left_stick_x / 2;
        } else {
            yy = gamepad1.left_stick_y /2;
        }

            drive.setSpeedXYR(yy, xx, gamepad1.right_stick_x);
            drive.update();

//        DRIVER (GAMEPAD) 1 CONTROLS
//        drive.update();


    }
}