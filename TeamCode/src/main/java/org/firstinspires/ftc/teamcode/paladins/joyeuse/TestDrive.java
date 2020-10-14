package org.firstinspires.ftc.teamcode.paladins.joyeuse;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;

import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseConfiguration;
import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseDrive;

import java.util.ArrayDeque;

@TeleOp(name = "TestDrive")
public class TestDrive extends PaladinsOpMode {
    private JoyeuseConfiguration config;
    private JoyeuseDrive drive;

    @Override
    protected void onInit() {
        config = JoyeuseConfiguration.newConfig(hardwareMap, telemetry);

        drive = new JoyeuseDrive(this, config.leftMidMotor, config.leftBackMotor, config.rightMidMotor, config.rightBackMotor);
    }

    @Override
    protected void activeLoop() throws InterruptedException {
        drive.setPower(gamepad1.left_stick_y, gamepad1.right_stick_y);
        drive.update();
    }

}