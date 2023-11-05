package org.firstinspires.ftc.teamcode.paladins.excalibur;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.paladins.common.RobotConfiguration;

/**
 * It is assumed that there is a configuration that is currently activated on the robot controller
 * (run menu / Configure Robot ) with the same name as this class.
 * It is also assumed that the device names in the 'init()' method below are the same as the devices
 * named on the activated configuration on the robot.
 */
public class ExcaliburConfiguration extends RobotConfiguration {
    // Front motors
    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;

    // Back motors
    public DcMotor backLeftMotor;
    public DcMotor backRightMotor;

    //Flywheel Config
    public CRServo LSpinner;
    public CRServo RSpinner;

    // lift config
    public DcMotor RightLiftMotor;
    public DcMotor LeftLiftMotor;
    public DcMotor Harvester;
    BNO055IMU imu;

    public double countsPerMotorRev = 288;
    public double driveGearReduction = 72.0 / 90.0; // 72 Teeth -> 90 Teeth
    public double wheelDiameterCm = 9.0;

    public double countsPerCm = (countsPerMotorRev * driveGearReduction) / (wheelDiameterCm * Math.PI);

    /**
     * Factory method for this class
     *
     * @param hardwareMap
     * @param telemetry
     * @return
     */
    public static ExcaliburConfiguration newConfig(HardwareMap hardwareMap, Telemetry telemetry) {
        ExcaliburConfiguration config = new ExcaliburConfiguration();
        config.init(hardwareMap, telemetry);
        return config;
    }

    /**
     * Assign your class instance variables to the saved device names in the hardware map
     *
     * @param hardwareMap
     * @param telemetry
     */
    @Override
    protected void init(HardwareMap hardwareMap, Telemetry telemetry) {

        setTelemetry(telemetry);

        frontLeftMotor = (DcMotor) getHardwareOn("frontLeftMotor", hardwareMap.dcMotor);
        frontRightMotor = (DcMotor) getHardwareOn("frontRightMotor", hardwareMap.dcMotor);

        backLeftMotor = (DcMotor) getHardwareOn("backLeftMotor", hardwareMap.dcMotor);
        backRightMotor = (DcMotor) getHardwareOn("backRightMotor", hardwareMap.dcMotor);

        Harvester = (DcMotor) getHardwareOn("Harvester", hardwareMap.dcMotor);

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        LeftLiftMotor = (DcMotor) getHardwareOn("leftLiftMotor", hardwareMap.dcMotor);
        RightLiftMotor = (DcMotor) getHardwareOn("rightLiftMotor", hardwareMap.dcMotor);

//        leftMidMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightMidMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//        leftBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//        rightBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        // Set up the parameters with which we will use our IMU. Note that integration
        // algorithm here just reports accelerations to the logcat log; it doesn't actually
        // provide positional information.
//        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
//        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
//        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
//        parameters.calibrationDataFile = "AdafruitIMUCalibration.json"; // see the calibration sample opmode
//        parameters.loggingEnabled      = true;
//        parameters.loggingTag          = "IMU";
//        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
//        imu = hardwareMap.get(BNO055IMU.class, "imu");
//        imu.initialize(parameters);
    }


}
