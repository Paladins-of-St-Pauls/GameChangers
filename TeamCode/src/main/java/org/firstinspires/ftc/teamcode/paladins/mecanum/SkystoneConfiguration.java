package org.firstinspires.ftc.teamcode.paladins.mecanum;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.Camera;
import org.firstinspires.ftc.teamcode.paladins.common.RobotConfiguration;

/**
 * It is assumed that there is a configuration that is currently activated on the robot controller
 * (run menu / Configure Robot ) with the same name as this class.
 * It is also assumed that the device names in the 'init()' method below are the same as the devices
 * named on the activated configuration on the robot.
 */
public class SkystoneConfiguration extends RobotConfiguration {

    public DcMotor frontLeftMotor = null;
    public DcMotor frontRightMotor = null;
    public DcMotor backLeftMotor = null;
    public DcMotor backRightMotor = null;

    /**
     * Assign your class instance variables to the saved device names in the hardware map
     *
     * @param hardwareMap
     * @param telemetry
     */
    @Override
    protected void init(HardwareMap hardwareMap, final Telemetry telemetry) {
        setTelemetry(telemetry);

        try {
            frontLeftMotor  = hardwareMap.get(DcMotor.class, "FL");
        } catch (Exception e) {
            telemetry.addLine("FrontLeftMotor failed to configure");
        }
        try {
            frontRightMotor = hardwareMap.get(DcMotor.class, "FR");
        } catch (Exception e) {
            telemetry.addLine("FrontRightMotor failed to configure");
        }
        try {
            backLeftMotor = hardwareMap.get(DcMotor.class,"BL");
        } catch (Exception e) {
            telemetry.addLine("BackLeftMotor failed to configure");
        }
        try {
            backRightMotor = hardwareMap.get(DcMotor.class,"BR");
        } catch (Exception e) {
            telemetry.addLine("BackRightMotor failed to configure");
        }


        if (frontLeftMotor != null && frontRightMotor != null && backLeftMotor != null && backRightMotor != null) {
            frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
            frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
            backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
            backRightMotor.setDirection(DcMotor.Direction.FORWARD);

            frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

//        Iterator<HardwareMap.DeviceMapping<? extends HardwareDevice>> iter  = hardwareMap.allDeviceMappings.iterator();
//        while (iter.hasNext()) {
//            telemetry.addLine(iter.next().toString());
//        }



        telemetry.addData("Initialized", "True");
        telemetry.update();
    }
    //down 1.35
    //up 0.8

    /**
     * Factory method for this class
     *
     * @param hardwareMap
     * @param telemetry
     * @return
     */
    public static SkystoneConfiguration newConfig(HardwareMap hardwareMap, Telemetry telemetry) {
        SkystoneConfiguration config = new SkystoneConfiguration();
        config.init(hardwareMap, telemetry);
        return config;

    }


}
