package org.firstinspires.ftc.teamcode.paladins.joyeuse;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;

@TeleOp(name = "TankDrive")
public class TankDriveOpMode extends PaladinsOpMode {
    private JoyeuseConfiguration config;
    private JoyeuseDrive drive;
    private JoyeuseTankDrive tankDrive;

    @Override
    protected void onInit() {
        config = JoyeuseConfiguration.newConfig(hardwareMap, telemetry);

        drive = new JoyeuseDrive(this, config.leftMidMotor, config.leftBackMotor, config.rightMidMotor, config.rightBackMotor);
        tankDrive = new JoyeuseTankDrive(this, gamepad1, drive);
    }

    @Override
    protected void activeLoop() throws InterruptedException {
        tankDrive.update();
    }

}