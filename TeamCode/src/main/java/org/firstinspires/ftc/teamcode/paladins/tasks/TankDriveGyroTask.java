package org.firstinspires.ftc.teamcode.paladins.tasks;

import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.common.TankDrive;

public class TankDriveGyroTask extends BaseTask implements Task {

    private final TankDrive drive;
    private double leftSpeed;
    private double rightSpeed;
    private double tgtLeftSpeed;
    private double tgtRightSpeed;
    private final BNO055IMU imu;
    private Orientation angles;
    private double initialHeading;

    public TankDriveGyroTask(PaladinsOpMode opMode, BNO055IMU imu, double time, TankDrive drive, double leftSpeed, double rightSpeed) {
        super(opMode, time);
        this.drive = drive;
        this.imu = imu;

        this.leftSpeed = leftSpeed;
        this.rightSpeed = rightSpeed;
        this.tgtLeftSpeed = leftSpeed;
        this.tgtRightSpeed = rightSpeed;
    }

    @Override
    public void init() {
        super.init();
        drive.setEncoderMode(false);

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        initialHeading = angles.firstAngle;

    }

    @Override
    public void run() {
        if (isFinished()) {
            drive.setPower(0, 0);
            drive.update();
            return;
        }

//        this.tgtLeftSpeed = leftSpeed;
//        this.tgtRightSpeed = rightSpeed;

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        opMode.telemetry.addData("Initial Heading", initialHeading);

        opMode.telemetry.addData("First", angles.firstAngle);
        opMode.telemetry.addData("Second", angles.secondAngle);
        opMode.telemetry.addData("Third", angles.thirdAngle);

        double diff = initialHeading - angles.firstAngle;
        double deltaSpeed = Math.abs(diff) * 0.001;
        double sign = Math.signum(diff);

        leftSpeed = tgtLeftSpeed - (deltaSpeed * sign);
        rightSpeed = tgtRightSpeed + (deltaSpeed * sign);

        drive.setPower(leftSpeed, rightSpeed);
        drive.update();
    }

}
