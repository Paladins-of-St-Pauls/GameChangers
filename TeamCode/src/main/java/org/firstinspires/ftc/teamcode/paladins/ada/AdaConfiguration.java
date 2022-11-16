package org.firstinspires.ftc.teamcode.paladins.ada;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.paladins.common.RobotConfiguration;

/**
 * It is assumed that there is a configuration that is currently activated on the robot controller
 * (run menu / Configure Robot ) with the same name as this class.
 * It is also assumed that the device names in the 'init()' method below are the same as the devices
 * named on the activated configuration on the robot.
 */
public class AdaConfiguration extends RobotConfiguration {
    // Left motors
    public DcMotor leftMotor;
    public DcMotor rightMotor;

    public DcMotor armMotor;

    public CRServo armServo;

    public TouchSensor touchSensor;

//    public ColorSensor colorSensor;

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
    public static AdaConfiguration newConfig(HardwareMap hardwareMap, Telemetry telemetry) {

        AdaConfiguration config = new AdaConfiguration();
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

        leftMotor = (DcMotor) getHardwareOn("leftMotor", hardwareMap.dcMotor);
        rightMotor = (DcMotor) getHardwareOn("rightMotor", hardwareMap.dcMotor);
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        Telemetry.Item motorsItem = telemetry.addData("Drive Motors: ", new Func<String>() {
            @Override
            public String value() {
                return String.format("Left: %.2f  Right: %.2f", leftMotor.getPower(), rightMotor.getPower());
            }
        });
        motorsItem.setRetained(true);


        armMotor = (DcMotor) getHardwareOn("armMotor", hardwareMap.dcMotor);
        armMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        armServo = (CRServo) getHardwareOn("gripperServo", hardwareMap.crservo);
        touchSensor = (TouchSensor) getHardwareOn("touchSensor", hardwareMap.touchSensor);

        Telemetry.Item armItem = telemetry.addData("Arm: ", new Func<String>() {
            @Override
            public String value() {
                return String.format("Power: %.2f  Touch: %b", armMotor.getPower(), touchSensor.isPressed());
            }
        });
        armItem.setRetained(true);


//        colorSensor = (ColorSensor) getHardwareOn("colourSensor", hardwareMap.colorSensor);
//        colorSensor.enableLed(false);
//
//        Telemetry.Item colourItem = telemetry.addData("Colour Sensor: ", new Func<String>() {
//            @Override
//            public String value() {
//                return String.format("R: %d  G: %d  B: %d  A: %d", colorSensor.red(), colorSensor.green(), colorSensor.blue(), colorSensor.alpha());
//            }
//        });
//        colourItem.setRetained(true);


        // Set up the parameters with which we will use our IMU. Note that integration
        // algorithm here just reports accelerations to the logcat log; it doesn't actually
        // provide positional information.
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "AdafruitIMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

    }


}
