package org.firstinspires.ftc.teamcode.paladins.fierbois;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseDrive;
import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseTankDrive;
@Disabled
@TeleOp(name = "FierboisTankDrive")
public class FierboisTankDrive extends PaladinsOpMode {
    private FierboisConfiguration config;
    private JoyeuseDrive drive;
    private JoyeuseTankDrive tankDrive;


    @Override
    protected void onInit() {
        config = FierboisConfiguration.newConfig(hardwareMap, telemetry);

        drive = new JoyeuseDrive(this, config.frontLeftMotor, config.backLeftMotor, config.frontRightMotor, config.backRightMotor);
        tankDrive = new JoyeuseTankDrive(this, gamepad1, drive);

    }

    @Override
    protected void activeLoop() throws InterruptedException {
        tankDrive.update();

    }
}