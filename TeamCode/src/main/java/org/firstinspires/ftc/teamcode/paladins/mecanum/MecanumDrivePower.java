package org.firstinspires.ftc.teamcode.paladins.mecanum;

import com.qualcomm.robotcore.util.Range;

public class MecanumDrivePower {
    public double frontLeftPower = 0.0;
    public double frontRightPower = 0.0;
    public double backLeftPower = 0.0;
    public double backRightPower = 0.0;

    public void setDrivePower(double forward, double strafe_left, double rotate_right) {
        // Implement Mecanum drive using drive equations from Internet:

        if (forward > 0.1 || forward < -0.1) {
            frontLeftPower = forward;
            backLeftPower   = forward;
            frontRightPower = forward;
            backRightPower = forward;
        }
        else if (rotate_right > 0.1 || rotate_right < -0.1) {
            frontLeftPower = rotate_right;
            backLeftPower   = rotate_right;
            frontRightPower = -rotate_right;
            backRightPower = -rotate_right;
        }
        else {
            frontLeftPower = Range.clip(forward + strafe_left + rotate_right, -1.0, 1.0);
            backLeftPower   = Range.clip(forward - strafe_left + rotate_right, -1.0, 1.0);
            frontRightPower = Range.clip(forward - strafe_left - rotate_right, -1.0, 1.0);
            backRightPower = Range.clip(forward + strafe_left - rotate_right, -1.0, 1.0);
        }
    }
}
