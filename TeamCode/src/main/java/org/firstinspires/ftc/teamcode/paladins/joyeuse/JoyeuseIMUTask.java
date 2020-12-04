package org.firstinspires.ftc.teamcode.paladins.joyeuse;

import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.tasks.BaseTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;

public class JoyeuseIMUTask extends BaseTask implements Task {

    private final JoyeuseDrive drive;
    private final double speed;
    private final BNO055IMU imu;
    private final float angle;
    private Orientation angles;

    public JoyeuseIMUTask(PaladinsOpMode opMode, double time, JoyeuseDrive drive, double speed, BNO055IMU imu, double angle) {
        super(opMode, time);
        this.drive = drive;
        this.speed = speed;
        this.imu = imu;
        this.angle = (float) angle;

    }

    @Override
    public void init() {
        super.init();
        imu.startAccelerationIntegration(new Position(), new Velocity(), 50);
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        drive.setPower(speed, -speed);
    }

    @Override
    public void run() {
        if (isFinished()) {
            drive.setPower(0.0, 0.0);
            imu.stopAccelerationIntegration();
            return;
        }

        Orientation currentAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        if (currentAngles.firstAngle > angles.firstAngle + angle) {
            drive.setPower(0.0, 0.0);
            isFinished = true;
        }
    }
}
