package org.firstinspires.ftc.teamcode.paladins.joyeuse;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;

@TeleOp(name = "SteerDrive")
public class SteerDriveOpMode extends PaladinsOpMode {
    private JoyeuseConfiguration config;
    private JoyeuseDrive drive;
    private JoyeuseIntake intake;
    private JoyeuseShoot shoot;
    private JoyeuseGauntlet gauntlet;
    private JoyeuseSteerDrive steerDrive;

    private int shootMode;

    private boolean intakeOn;
    private boolean bumpOn;
    private boolean intakeReverse;

    @Override
    protected void onInit() {
        config = JoyeuseConfiguration.newConfig(hardwareMap, telemetry);

        drive = new JoyeuseDrive(this, config.leftMidMotor, config.leftBackMotor, config.rightMidMotor, config.rightBackMotor);
        intake = new JoyeuseIntake(this, config.intakeMotor, config.bumpMotor);
        shoot = new JoyeuseShoot(this, config.leftShooterMotor, config.rightShooterMotor);
//        gauntlet = new JoyeuseGauntlet(this, config.wgArm, config.wgHand, config.wgGripper);
        steerDrive = new JoyeuseSteerDrive(this, gamepad1, drive);

        shootMode = 0;

        intakeOn = false;
        bumpOn = false;
        intakeReverse = false;
    }

    @Override
    protected void activeLoop() throws InterruptedException {
        steerDrive.update();

        intake.setIntakePower(gamepad2.right_trigger);
        intake.setBumpPower(gamepad2.right_trigger);

//        gauntlet.setArmPower(gamepad2.left_stick_y);
//        gauntlet.setHandPower(gamepad2.left_stick_x);
//        gauntlet.setGripperPower(gamepad2.right_stick_x);

        if (gamepad2.y) {
            shootMode = 1;
        } else if (gamepad2.x) {
            shootMode = 2;
        } else if (gamepad2.a) {
            shootMode = 3;
        } else if (gamepad2.b) {
            shootMode = 0;
        }

        if (shootMode == 1) {
            shoot.setPower(0.75);
        } else if (shootMode == 2) {
            shoot.setPower(0.2);
        } else if (shootMode == 3) {
            shoot.setPower(-0.2);
        } else {
            shoot.setPower(0);
        }

        if (gamepad2.dpad_up) {
            intakeReverse = false;
        } else if (gamepad2.dpad_down) {
            intakeReverse = true;
        }
        if (gamepad2.dpad_left) {
            if (intakeOn) {
                intakeOn = false;
            } else {
                intakeOn = true;
            }
        }
        if (gamepad2.dpad_right) {
            if (bumpOn) {
                bumpOn = false;
            } else {
                bumpOn = true;
            }
        }

        if (intakeOn) {
            intake.setIntakePower(1.0);
        }
        if (intakeOn && intakeReverse) {
            intake.setIntakePower(-1.0);
        }
        if (intakeOn == false) {
            intake.setIntakePower(0.0);
        }

        if (bumpOn) {
            intake.setBumpPower(1.0);
        }
        if (bumpOn && intakeReverse) {
            intake.setBumpPower(-  1.0);
        }
        if(bumpOn == false) {
            intake.setBumpPower(0.0);
        }

//        if(gamepad2.right_trigger > 0.2) {
//            intake.setIndexerPos(1);
//        } else {
//            intake.setIndexerPos(0);
//        }
    }
}