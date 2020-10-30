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
    private JoyeuseShoot shoot;
    private JoyeuseSteerDrive steerDrive;

    @Override
    protected void onInit() {
        config = JoyeuseConfiguration.newConfig(hardwareMap, telemetry);

        drive = new JoyeuseDrive(this, config.leftMidMotor, config.leftBackMotor, config.rightMidMotor, config.rightBackMotor);
        shoot = new JoyeuseShoot(this, gamepad1, config.leftShooterMotor, config.rightShooterMotor);
        steerDrive = new JoyeuseSteerDrive(this, gamepad1, drive);
    }

    @Override
    protected void activeLoop() throws InterruptedException {
        steerDrive.update();
        shoot.update();

        if(gamepad1.left_trigger > 0) {
            config.wgServo.setPower(gamepad1.left_trigger);
        } else if (gamepad1.right_trigger > 0) {
            config.wgServo.setPower(-gamepad1.right_trigger);
        } else {
            config.wgServo.setPower(0);
        }
    }

}