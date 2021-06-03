package org.firstinspires.ftc.teamcode.paladins.mecanum;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsComponent;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;

/**
 * Created by Baconeers on 8/11/2018.
 */
public class SkystoneDrive extends PaladinsComponent {
    // members:
    private PaladinsOpMode opmode =null;
    private SkystoneConfiguration config = null;
    public boolean buttonState = false;
    public boolean lastButtonState = false;
    public boolean state = false;
    private NormalisedMecanumDrive mecanumDrive = null;

    MecanumDrivePower drivePower = new MecanumDrivePower();

   /* public boolean toggleFunction = false;

   public double toggle(){
        buttonState = gamepad1.right_bumper;
        if (buttonState && !lastButtonState) {
            state = !state;
        }

        if (buttonState != lastButtonState) {
            lastButtonState = buttonState;
        }


        if (state) {
            //On state
            return 0.5f;

        }
        else {
            //Off state

            return 1.0f;

        }


   } */



       public SkystoneDrive(PaladinsOpMode opmodeIn, SkystoneConfiguration configIn) {
        super(opmodeIn);
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        opmode = opmodeIn;
        config = configIn;

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery


    }

    public void update(){
        //Drive not working, strafing works fine, turning is faulty and forward isn't working
        //When going forward the front and back wheels are going opposite directions
        //when turning it seems that some wheel might be going slower then others causing it to NOT turn on the centre

        double forward = opmode.gamepad1.left_stick_y; //forward
        double strafe_left  = opmode.gamepad1.left_stick_x; //strafe
        double rotate_right = -opmode.gamepad1.right_stick_x; //turning

        drivePower.setDrivePower(forward, strafe_left, rotate_right);

        config.frontLeftMotor.setPower(-drivePower.frontLeftPower);
        config.frontRightMotor.setPower(-drivePower.frontRightPower);
        config.backLeftMotor.setPower(-drivePower.backLeftPower);
        config.backRightMotor.setPower(-drivePower.backRightPower);

        // Show the elapsed game time and wheel power.
        opmode.telemetry.addData("Motors", "front left (%.2f), front right (%.2f)",
                drivePower.frontLeftPower, drivePower.frontRightPower);
        opmode.telemetry.addData("Motors", "rear left (%.2f), rear right (%.2f)",
                drivePower.backLeftPower  , drivePower.backRightPower);
        opmode.telemetry.update();

    }
}