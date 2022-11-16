package org.firstinspires.ftc.teamcode.paladins.jeffs;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.paladins.common.GamePadSteerDrive;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;

@Disabled
@TeleOp(name = "JeffsTeleOpSteerDrive")
public class JeffsTeleOpSteerDrive extends PaladinsOpMode {
    private JeffsBotConfiguration config;
    private GamePadSteerDrive drive;

    @Override
    protected void onInit() {
        config = JeffsBotConfiguration.newConfig(hardwareMap, telemetry);

        drive = new GamePadSteerDrive(this, gamepad1, config.leftMotor, config.rightMotor);
    }

    @Override
    protected void activeLoop() throws InterruptedException {
        drive.update();
    }
}
