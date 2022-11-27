package org.firstinspires.ftc.teamcode.paladins.cortana;

import static java.lang.Boolean.TRUE;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.mecanum.NormalisedMecanumDrive;

@TeleOp(name = "CortanaMechanumDrive")
public class CortanaOpMode extends PaladinsOpMode {
    private CortanaConfiguration config;
    private NormalisedMecanumDrive drive;
    private CortanaLift lift;

    @Override
    protected void onInit() {
        config = CortanaConfiguration.newConfig(hardwareMap, telemetry);

        drive = new NormalisedMecanumDrive(this, config.frontLeftMotor, config.frontRightMotor, config.backLeftMotor, config.backRightMotor, TRUE);
        lift = new CortanaLift(this, config.liftMotor, config.liftClamp, config.liftSensor);
    }

    @Override
    protected void activeLoop() throws InterruptedException {


        float xx = 0;
        float yy = 0;



        if (Math.abs(gamepad1.right_stick_x) > Math.abs(gamepad1.right_trigger)) {
            xx = gamepad1.right_stick_x;
        } else {
            if (Math.abs(gamepad1.right_trigger) > 0) {
                yy = -gamepad1.right_trigger;
            } else {
                yy = gamepad1.left_trigger;
            }
        }

        if (!gamepad1.b) {
            xx = xx/2;
            yy = yy/2;
        }
        if (gamepad1.dpad_up) {
            lift.liftUp();

        } else if (gamepad1.dpad_down && !config.liftSensor.isPressed()) {
            lift.liftDown();
        } else{
            lift.liftBrake();
        }

        if (gamepad1.dpad_left) {
            lift.liftClampOpen();
        }
        if (gamepad1.dpad_right){
            lift.liftClampClose();
        }
            drive.setSpeedXYR(-yy, xx, -gamepad1.left_stick_x);
            drive.update();
            lift.update();

//        DRIVER (GAMEPAD) 1 CONTROLS
//        drive.update();


    }
}