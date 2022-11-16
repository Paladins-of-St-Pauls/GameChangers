package org.firstinspires.ftc.teamcode.paladins.jeffs;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.paladins.common.GamePadSteerDrive;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;

@Disabled
@TeleOp(name = "JeffsServoTest")
public class JeffsServoTest extends PaladinsOpMode {
    private JeffsBotConfiguration config;

    @Override
    protected void onInit() {
        config = JeffsBotConfiguration.newConfig(hardwareMap, telemetry);
    }

    @Override
    protected void activeLoop() throws InterruptedException {
        if(gamepad1.right_trigger > 0.2) {
            config.servo.setPosition(1);
        } else {
            config.servo.setPosition(0);
        }

        telemetry.addData("Servo Position", config.servo.getPosition());
    }
}