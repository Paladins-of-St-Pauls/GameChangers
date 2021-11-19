package org.firstinspires.ftc.teamcode.paladins.durandal;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.paladins.common.ButtonControl;
import org.firstinspires.ftc.teamcode.paladins.common.GamePadMomentaryMotor;
import org.firstinspires.ftc.teamcode.paladins.common.JoystickControl;
import org.firstinspires.ftc.teamcode.paladins.common.JoystickMomentaryServo;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;


@TeleOp(name = "DurandalTankDrive")
public class  DurandalTankDriveOpMode extends PaladinsOpMode {
    private DurandalConfiguration config;
    private DurandalDrive drive;
    private DurandalTankDrive tankDrive;
    private GamePadMomentaryMotor leftSpinner;
    private GamePadMomentaryMotor rightSpinner;
    private JoystickMomentaryServo rightHarvester;
    private JoystickMomentaryServo leftHarvester;

    @Override
    protected void onInit() {
        config = DurandalConfiguration.newConfig(hardwareMap, telemetry);

        drive = new DurandalDrive(this, config.leftMotor, config.rightMotor);
        tankDrive = new DurandalTankDrive(this, gamepad1, drive);
        leftSpinner = new GamePadMomentaryMotor(this, gamepad1, config.leftSpinnerMotor, ButtonControl.LEFT_BUMPER, 0.5f);
        rightSpinner = new GamePadMomentaryMotor(this, gamepad1, config.rightSpinnerMotor, ButtonControl.RIGHT_BUMPER, 0.5f);
        rightHarvester = new JoystickMomentaryServo(this, gamepad1, config.rightHarvester, ButtonControl.A,-0.25f, ButtonControl.X,0.25f);
        leftHarvester = new JoystickMomentaryServo(this, gamepad1, config.leftHarvester, ButtonControl.B,0.25f, ButtonControl.Y, -0.25f);

    }

    @Override
    protected void activeLoop() throws InterruptedException {
        tankDrive.update();
        leftSpinner.update();
        rightSpinner.update();
        leftHarvester.update();
        rightHarvester.update();


    }
}