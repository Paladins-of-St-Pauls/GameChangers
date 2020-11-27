package org.firstinspires.ftc.teamcode.paladins.joyeuse;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;

@TeleOp(name = "JoyeuseTeleOp")
public class TeleOpOpMode extends PaladinsOpMode {
    private JoyeuseConfiguration config;
    private JoyeuseDrive drive;
    private JoyeuseIntake intake;
    private JoyeuseShoot shoot;
    private JoyeuseGauntlet gauntlet;
    private JoyeuseTriggerSteerDrive steerDrive;

    private boolean steerDriveMode;

    private boolean shooterOn;

    private boolean intakeOn;
    private boolean bumpOn;

    private boolean arm;
    private boolean hand;

    private boolean gripperClosed;

    @Override
    protected void onInit() {
        config = JoyeuseConfiguration.newConfig(hardwareMap, telemetry);

        drive = new JoyeuseDrive(this, config.leftMidMotor, config.leftBackMotor, config.rightMidMotor, config.rightBackMotor);
        intake = new JoyeuseIntake(this, config.intakeMotor, config.bumpMotor);
        shoot = new JoyeuseShoot(this, config.leftShooterMotor, config.rightShooterMotor);
        gauntlet = new JoyeuseGauntlet(this, config.wgArm, config.wgHand, config.wgGripper);
        steerDrive = new JoyeuseTriggerSteerDrive(this, gamepad1, drive);

        shooterOn = false;

        intakeOn = false;
        bumpOn = false;

        gripperClosed = false;
    }

    @Override
    protected void activeLoop() throws InterruptedException {

//        DRIVER (GAMEPAD) 1 CONTROLS
        steerDrive.update();

//        if (gamepad1.dpad_up && !was1DpadUp) {
//            shooterOn ^= true;
//        }

//        if (shooterOn) {
//            shoot.setPower(1.0);
//        } else {
//            shoot.setPower(0);
//        }
//
//        if (gamepad1.dpad_left && !was1DpadLeft) {
//            intakeOn ^= true;
//        }
//
//        if (gamepad1.dpad_right && !was1DpadRight) {
//            bumpOn ^= true;
//        }
//
//        if (intakeOn) {
//            intake.setIntakePower(1.0);
//        } else {
//            intake.setIntakePower(0);
//        }
//
//        if (bumpOn) {
//            intake.setBumpPower(1.0);
//        } else {
//            intake.setBumpPower(0);
//        }

//        DRIVER (GAMEPAD) 2 CONTROLS

        boolean was2X = false;

        if(gamepad2.x && !was2X) {
            arm ^= true;
        }

        if(arm) {
            gauntlet.setArmPos(0.5);
        } else {
            gauntlet.setArmPos(1.0);
        }

        was2X = gamepad2.x;

//        gauntlet.setArmPos(gamepad2.left_stick_y);


//        config.wgArm.setPosition(gamepad2.left_trigger);
        telemetry.addData("Position", config.wgArm.getPosition());


//        boolean was2DpadLeft = false;
//        boolean was2DpadRight = false;

//        if (gamepad2.dpad_left && !was2DpadLeft) z
//            gripperClosed = true;
//        }
//
//        if (gamepad2.dpad_right && !was1DpadRight) {
//            gripperClosed = false;
//        }
//
//        if (gripperClosed) {
//            gauntlet.setGripperPos(1.0);
//        } else {
//            gauntlet.setGripperPos(0);
//        }
//
//        was2DpadLeft = gamepad2.dpad_left;
//        was2DpadRight = gamepad2.dpad_right;


    }
}