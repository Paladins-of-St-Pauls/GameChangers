package org.firstinspires.ftc.teamcode.paladins.cortana;

import static java.lang.Boolean.TRUE;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.mecanum.NormalisedMecanumDrive;

@TeleOp(name = "CortanaMechanumDrive")
public class CortanaOpMode extends PaladinsOpMode {
    private CortanaConfiguration config;
    private NormalisedMecanumDrive drive;
    private CortanaLift lift;
    private boolean up_pressed = false;
    private boolean down_pressed = false;
    private double liftSpeed;


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

        liftSpeed = gamepad2.left_stick_y;
        if(lift.liftSensor.isPressed() && gamepad2.left_stick_y <= 0) {
            liftSpeed = 0;
            lift.liftReset();
        }

//        lift.setPower(0.5);
//        if (up_pressed && !gamepad2.dpad_up) {
//            lift.liftUp();
//        } else if (down_pressed && !gamepad2.dpad_down) {
//            lift.liftDown();
//        }

//        if (gamepad2.x) {
//            lift.highGoal();
//        }
//        if (gamepad2.y) {
//            lift.midGoal();
//        }
//        if (gamepad2.b) {
//            lift.lowGoal();
//        }
//        if (gamepad2.a) {
//            lift.bottomOut();
//        }

//        up_pressed = gamepad2.dpad_up;
//        down_pressed = gamepad2.dpad_down;

        if (gamepad1.dpad_left) {
            lift.liftClampOpen();
        }
        if (gamepad1.dpad_right){
            lift.liftClampClose();
        }
            drive.setSpeedXYR(-yy, xx, -gamepad1.left_stick_x/2);
            drive.update();
            lift.setPower(liftSpeed);
            lift.update();



    }
}