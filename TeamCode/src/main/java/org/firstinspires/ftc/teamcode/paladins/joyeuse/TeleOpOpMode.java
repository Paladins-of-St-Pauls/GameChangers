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

    private boolean arm;
    private boolean hand;
    private boolean hook;

    @Override
    protected void onInit() {
        config = JoyeuseConfiguration.newConfig(hardwareMap, telemetry);

        drive = new JoyeuseDrive(this, config.leftMidMotor, config.leftBackMotor, config.rightMidMotor, config.rightBackMotor);
        intake = new JoyeuseIntake(this, config.intakeMotor, config.bumpMotor, config.conveyorServo, config.indexerServo);
        shoot = new JoyeuseShoot(this, config.leftShooterMotor, config.rightShooterMotor);
        gauntlet = new JoyeuseGauntlet(this, config.wgArm, config.wgHand, config.wgHook);
        steerDrive = new JoyeuseTriggerSteerDrive(this, gamepad1, drive);
    }

    @Override
    protected void activeLoop() throws InterruptedException {

//        DRIVER (GAMEPAD) 1 CONTROLS
        steerDrive.update();

        if(gamepad1.a) {
            intake.setIndexerPos(0.12);
        } else {
            intake.setIndexerPos(0.25);
        }

//        DRIVER (GAMEPAD) 2 CONTROLS


        if(gamepad2.left_trigger > 0) {
            intake.setIntakePower(gamepad2.left_trigger);
            intake.setBumpPower(gamepad2.left_trigger);
            intake.setConveyorPower(gamepad2.left_trigger);
        } else {
            intake.setIntakePower(-gamepad2.right_trigger);
            intake.setBumpPower(-gamepad2.right_trigger);
            intake.setConveyorPower(-gamepad2.right_trigger);
        }

        if(gamepad2.left_bumper) {
            shoot.setPower(-1.0);
        } else if(gamepad2.right_bumper) {
            shoot.setPower(1.0);
        } else {
            shoot.setPower(0);
        }

        if(gamepad2.dpad_up) {
            gauntlet.setArmPos(0);
        }

        if(gamepad2.dpad_down) {
            gauntlet.setArmPos(0.5);
        }

        if(gamepad2.dpad_left) {
            gauntlet.setHandPos(0);
        }

        if(gamepad2.dpad_right) {
            gauntlet.setHandPos(1.0);
        }

        if(gamepad2.x) {
            gauntlet.setHookPos(0);
        }

        if(gamepad2.b) {
            gauntlet.setHookPos(1.0);

        }

        telemetry.addData("Arm Position", config.wgArm.getPosition());
        telemetry.addData("Hand Position", config.wgHand.getPosition());
        telemetry.addData("Hook Position", config.wgHook.getPosition());

    }
}