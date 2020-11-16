package org.firstinspires.ftc.teamcode.paladins.joyeuse;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;

import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseConfiguration;
import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseDrive;
import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseShoot;

import java.util.ArrayDeque;

@TeleOp(name = "SteerDrive")
public class SteerDriveOpMode extends PaladinsOpMode {
    private JoyeuseConfiguration config;
    private JoyeuseDrive drive;
    private JoyeuseHarvest harvest;
    private JoyeuseShoot shoot;
    private JoyeuseSteerDrive steerDrive;

    private int shootMode;

    @Override
    protected void onInit() {
        config = JoyeuseConfiguration.newConfig(hardwareMap, telemetry);

        drive = new JoyeuseDrive(this, config.leftMidMotor, config.leftBackMotor, config.rightMidMotor, config.rightBackMotor);
        harvest = new JoyeuseHarvest(this, config.intakeMotor, config.bumpMotor);
//        shoot = new JoyeuseShoot(this, config.leftShooterMotor, config.rightShooterMotor, config.shooterServo);
        steerDrive = new JoyeuseSteerDrive(this, gamepad1, drive);

        shootMode = 0;
    }

    @Override
    protected void activeLoop() throws InterruptedException {
        steerDrive.update();

            harvest.setIntakePower(gamepad1.right_trigger);
            harvest.setBumpPower(gamepad1.right_trigger);
//
//        if (gamepad1.left_trigger > 0) {
//            shoot.setServoPower(gamepad1.left_trigger);
//        } else if (gamepad1.right_trigger > 0) {
//            shoot.setServoPower(-gamepad1.right_trigger);
//        } else {
//            shoot.setServoPower(0);
//        }
//
//        if (gamepad1.y) {
//            shootMode = 1;
//        } else if (gamepad1.x) {
//            shootMode = 2;
//        } else if (gamepad1.a) {
//            shootMode = 3;
//        } else if (gamepad1.b) {
//            shootMode = 0;
//        }
//
//        if (shootMode == 1) {
//            shoot.setPower(0.75);
//        } else if (shootMode == 2) {
//            shoot.setPower(0.2);
//        } else if (shootMode == 3) {
//            shoot.setPower(-0.2);
//        } else {
//            shoot.setPower(0);
//        }

    }

}