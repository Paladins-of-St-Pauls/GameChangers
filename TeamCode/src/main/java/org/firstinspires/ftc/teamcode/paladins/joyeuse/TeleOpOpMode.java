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
    private JoyeuseSteerDrive steerDrive;
    private JoyeuseTankDrive tankDrive;

    private boolean steerDriveMode;

    private boolean shooterOn;

    private boolean intakeOn;
    private boolean bumpOn;

    private boolean gripperClosed;

    @Override
    protected void onInit() {
        config = JoyeuseConfiguration.newConfig(hardwareMap, telemetry);

        drive = new JoyeuseDrive(this, config.leftMidMotor, config.leftBackMotor, config.rightMidMotor, config.rightBackMotor);
        intake = new JoyeuseIntake(this, config.intakeMotor, config.bumpMotor);
        shoot = new JoyeuseShoot(this, config.leftShooterMotor, config.rightShooterMotor);
//        gauntlet = new JoyeuseGauntlet(this, config.wgArm, config.wgHand, config.wgGripper);
        steerDrive = new JoyeuseSteerDrive(this, gamepad1, drive);

        steerDriveMode = false;

        shooterOn = false;

        intakeOn = false;
        bumpOn = false;

        gripperClosed = false;
    }

    @Override
    protected void activeLoop() throws InterruptedException {

//        DRIVER (GAMEPAD) 1 CONTROLS

        boolean was1A = false;
        boolean was1DpadUp = false;
        boolean was1DpadLeft = false;
        boolean was1DpadRight = false;

        if (gamepad1.a && !was1A) {
            steerDriveMode ^= true;
        }

        if (steerDriveMode) {
            telemetry.addLine("You are currently operating in STEER DRIVE | Driver, press A to change.");
            steerDrive.update();
        } else {
            telemetry.addLine("You are currently operating in TANK DRIVE | Driver, press A to change.");
            tankDrive.update();
        }

        if (gamepad1.dpad_up && !was1DpadUp) {
            shooterOn ^= true;
        }

        if (shooterOn) {
            shoot.setPower(1.0);
        } else {
            shoot.setPower(0);
        }

        if (gamepad1.dpad_left && !was1DpadLeft) {
            intakeOn ^= true;
        }

        if (gamepad1.dpad_right && !was1DpadRight) {
            bumpOn ^= true;
        }

        if (intakeOn) {
            intake.setIntakePower(1.0);
        } else {
            intake.setIntakePower(0);
        }

        if (bumpOn) {
            intake.setBumpPower(1.0);
        } else {
            intake.setBumpPower(0);
        }

        was1A = gamepad1.a;
        was1DpadUp = gamepad1.dpad_up;
        was1DpadLeft = gamepad1.dpad_left;
        was1DpadRight = gamepad1.dpad_right;

//        DRIVER (GAMEPAD) 2 CONTROLS

        boolean was2DpadLeft = false;
        boolean was2DpadRight = false;

//        if (gamepad2.dpad_left && !was2DpadLeft) {
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

        was2DpadLeft = gamepad2.dpad_left;
        was2DpadRight = gamepad2.dpad_right;


    }
}