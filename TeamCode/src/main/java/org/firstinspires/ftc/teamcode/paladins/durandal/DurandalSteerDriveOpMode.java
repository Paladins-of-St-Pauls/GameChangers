package org.firstinspires.ftc.teamcode.paladins.durandal;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;

@TeleOp(name = "DurandalSteerDrive")
public class DurandalSteerDriveOpMode extends PaladinsOpMode {
    private DurandalConfiguration config;
    private DurandalDrive drive;
    private DurandalSteerDrive steerDrive;
    private DurandalCarousel carousel;

    @Override
    protected void onInit() {
        config = DurandalConfiguration.newConfig(hardwareMap, telemetry);

        drive = new DurandalDrive(this, config.leftMotor, config.rightMotor);
        steerDrive = new DurandalSteerDrive(this, gamepad1, drive);
//        carousel = new DurandalCarousel(this, config.carouselMotor);

    }

    @Override
    protected void activeLoop() throws InterruptedException {
        steerDrive.update();


    }
}