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
    private DurandalLift lift;
    private DurandalHarvester harvester;
    private GamePadMomentaryMotor spinner;
    private JoystickMomentaryServo rightHarvester;
    private JoystickMomentaryServo leftHarvester;

    private boolean up_pressed = false;
    private boolean down_pressed = false;


    @Override
    protected void onInit() {
        config = DurandalConfiguration.newConfig(hardwareMap, telemetry);

        drive = new DurandalDrive(this, config.leftMotor, config.rightMotor);
        tankDrive = new DurandalTankDrive(this, gamepad1, drive);
        spinner = new GamePadMomentaryMotor(this, gamepad2, config.spinnerMotor, ButtonControl.Y, 0.5f);
        lift = new DurandalLift(this, config.liftMotor, config.liftSwitch);
        harvester = new DurandalHarvester(this, config.leftHarvester, config.rightHarvester);
    }

    @Override
    protected void activeLoop() throws InterruptedException {
        tankDrive.update();
        spinner.update();



        lift.setPower(.2);
        if (up_pressed && !gamepad2.dpad_up) {
            lift.liftUp();
        } else if (down_pressed && !gamepad2.dpad_down) {
            lift.liftDown();
        }

        up_pressed = gamepad2.dpad_up;
        down_pressed = gamepad2.dpad_down;

        lift.update();

        if(gamepad2.left_bumper) {
            harvester.setPower(-1.0);
        } else if (gamepad2.right_bumper) {
            harvester.setPower(0.5);
        } else {
            harvester.setPower(0);
        }

        if(config.liftSwitch.isPressed()) {
            telemetry.addLine("Switch Down");

        } else {
            telemetry.addLine("Switch Up");
        }

        harvester.update();

    }
}