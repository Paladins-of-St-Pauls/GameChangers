package org.firstinspires.ftc.teamcode.paladins.durandal;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseConfiguration;
import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseDrive;
import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseGauntlet;
import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseIntake;
import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseShoot;
import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseTankDrive;

@TeleOp(name = "DurandalTankDrive")
public class DurandalTankDriveOpMode extends PaladinsOpMode {
    private DurandalConfiguration config;
    private DurandalDrive drive;
    private DurandalTankDrive tankDrive;

    @Override
    protected void onInit() {
        config = DurandalConfiguration.newConfig(hardwareMap, telemetry);

        drive = new DurandalDrive(this, config.leftMotor, config.rightMotor);
        tankDrive = new DurandalTankDrive(this, gamepad1, drive);


    }

    @Override
    protected void activeLoop() throws InterruptedException {
        tankDrive.update();


    }
}