package org.firstinspires.ftc.teamcode.paladins.durandal;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.paladins.common.ButtonControl;
import org.firstinspires.ftc.teamcode.paladins.common.GamePadMomentaryMotor;
import org.firstinspires.ftc.teamcode.paladins.common.JoystickMomentaryServo;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;


@TeleOp(name = "DurandalSteerDrive")
public class DurandalSteerDriveOpMode extends PaladinsOpMode {
    private DurandalConfiguration config;
    private DurandalDrive drive;
    private DurandalSteerDrive steerDrive;
    private DurandalLift lift;
    private DurandalHarvester harvester;
    private GamePadMomentaryMotor spinner;
    private JoystickMomentaryServo rightHarvester;
    private JoystickMomentaryServo leftHarvester;

    @Override
    protected void onInit() {
        config = DurandalConfiguration.newConfig(hardwareMap, telemetry);

        drive = new DurandalDrive(this, config.leftMotor, config.rightMotor);
        steerDrive = new DurandalSteerDrive(this, gamepad1, drive);
        spinner = new GamePadMomentaryMotor(this, gamepad2, config.spinnerMotor, ButtonControl.Y, 0.5f);
        lift = new DurandalLift(this, config.liftMotor, config.liftSwitch);
        harvester = new DurandalHarvester(this, config.leftHarvester, config.rightHarvester);
    }

    @Override
    protected void activeLoop() throws InterruptedException {
        steerDrive.update();
        spinner.update();

        if (gamepad2.a) {
            lift.setPower((gamepad2.right_trigger-gamepad2.left_trigger));
        } else {
            lift.setPower((gamepad2.right_trigger-gamepad2.left_trigger)/2);
        }

        lift.update();

        if(gamepad2.left_bumper) {
            harvester.setPower(-1.0);
        } else if (gamepad2.right_bumper) {
            harvester.setPower(0.5);
        } else {
            harvester.setPower(0);
        }

        harvester.update();
        if(config.liftSwitch.isPressed()) {
            telemetry.addLine("Switch Down");

        } else {
            telemetry.addLine("Switch Up");
        }


    }
}