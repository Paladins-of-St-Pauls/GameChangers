package org.firstinspires.ftc.teamcode.paladins.joyeuse;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.paladins.common.RobotConfiguration;

/**
 * It is assumed that there is a configuration that is currently activated on the robot controller
 * (run menu / Configure Robot ) with the same name as this class.
 * It is also assumed that the device names in the 'init()' method below are the same as the devices
 * named on the activated configuration on the robot.
 */
public class JoyeuseConfiguration extends RobotConfiguration {
    // Left motors
    public DcMotor leftMidMotor;
    public DcMotor leftBackMotor;

    // Right motors
    public DcMotor rightMidMotor;
    public DcMotor rightBackMotor;

    // Shooter motors
    public DcMotor leftShooterMotor;
    public DcMotor rightShooterMotor;

    //Intake motors
    public DcMotor intakeMotor;
    public DcMotor bumpMotor;

    //Intake servos
    public CRServo conveyorServo;
    public Servo leftIndexerServo;
    public Servo rightIndexerServo;

    // Colour sensors
    public ColorSensor leftColourSensor;
    public ColorSensor rightColourSensor;

    // Servo
    public Servo wgArm;
    public Servo wgHand;
    public Servo wgHook;

//    BNO055IMU imu;

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
    public static JoyeuseConfiguration newConfig(HardwareMap hardwareMap, Telemetry telemetry) {
        JoyeuseConfiguration config = new JoyeuseConfiguration();
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

        leftMidMotor = (DcMotor) getHardwareOn("leftMidMotor", hardwareMap.dcMotor);
        leftBackMotor = (DcMotor) getHardwareOn("leftBackMotor", hardwareMap.dcMotor);

        rightMidMotor = (DcMotor) getHardwareOn("rightMidMotor", hardwareMap.dcMotor);
        rightBackMotor = (DcMotor) getHardwareOn("rightBackMotor", hardwareMap.dcMotor);

        leftShooterMotor = (DcMotor) getHardwareOn("leftShooterMotor", hardwareMap.dcMotor);
        rightShooterMotor = (DcMotor) getHardwareOn("rightShooterMotor", hardwareMap.dcMotor);

        intakeMotor = (DcMotor) getHardwareOn("intakeMotor", hardwareMap.dcMotor);
        bumpMotor = (DcMotor) getHardwareOn("bumpMotor", hardwareMap.dcMotor);

        conveyorServo = (CRServo) getHardwareOn("conveyorServo", hardwareMap.crservo);
//        leftIndexerServo = (Servo) getHardwareOn("leftIndexerServo", hardwareMap.servo);
//        rightIndexerServo = (Servo) getHardwareOn("rightHardwareServo,", hardwareMap.servo);

        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        bumpMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        rightMidMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        leftColourSensor = hardwareMap.get(ColorSensor.class, "leftColourSensor");
        rightColourSensor = hardwareMap.get(ColorSensor.class, "rightColourSensor");

        wgArm = (Servo) getHardwareOn("wgArm", hardwareMap.servo);
        wgHand = (Servo) getHardwareOn("wgHand", hardwareMap.servo);
        wgHook = (Servo) getHardwareOn("wgHook", hardwareMap.servo);


//        // Set up the parameters with which we will use our IMU. Note that integration
//        // algorithm here just reports accelerations to the logcat log; it doesn't actually
//        // provide positional information.
//        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
//        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
//        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
//        parameters.calibrationDataFile = "AdafruitIMUCalibration.json"; // see the calibration sample opmode
//        parameters.loggingEnabled = true;
//        parameters.loggingTag = "IMU";
//        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
//
//        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
//        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
//        // and named "imu".
//        imu = hardwareMap.get(BNO055IMU.class, "imu");
//        imu.initialize(parameters);

    }


}
