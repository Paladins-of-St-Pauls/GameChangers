package org.firstinspires.ftc.teamcode.paladins.jeffs;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.paladins.common.RobotConfiguration;

/**
 * It is assumed that there is a configuration that is currently activated on the robot controller
 * (run menu / Configure Robot ) with the same name as this class.
 * It is also assumed that the device names in the 'init()' method below are the same as the devices
 * named on the activated configuration on the robot.
 */
public class JeffsBotConfiguration extends RobotConfiguration {
    // Left motors
    public DcMotor leftMotor;
    public DcMotor rightMotor;

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
    public static JeffsBotConfiguration newConfig(HardwareMap hardwareMap, Telemetry telemetry) {

        JeffsBotConfiguration config = new JeffsBotConfiguration();
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
    }


}
