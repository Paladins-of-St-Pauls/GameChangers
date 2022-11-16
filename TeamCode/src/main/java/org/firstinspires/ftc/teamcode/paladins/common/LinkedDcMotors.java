package org.firstinspires.ftc.teamcode.paladins.common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

public class LinkedDcMotors implements DcMotor {
    DcMotor motor1;
    DcMotor motor2;
    Direction direction1;
    Direction direction2;

    public LinkedDcMotors(DcMotor motor1, Direction direction1, DcMotor motor2, Direction direction2) {
        this.motor1 = motor1;
        this.direction1 = direction1;
        this.motor2 = motor2;
        this.direction2 = direction2;

        motor1.setDirection(direction1);
        motor2.setDirection(direction2);
    }

    @Override
    public MotorConfigurationType getMotorType() {
        return motor1.getMotorType();
    }

    @Override
    public void setMotorType(MotorConfigurationType motorType) {
        motor1.setMotorType(motorType);
        motor2.setMotorType(motorType);
    }

    @Override
    public DcMotorController getController() {
        return motor1.getController();
    }

    @Override
    public int getPortNumber() {
        return motor1.getPortNumber();
    }

    @Override
    public ZeroPowerBehavior getZeroPowerBehavior() {
        return motor1.getZeroPowerBehavior();
    }

    @Override
    public void setZeroPowerBehavior(ZeroPowerBehavior zeroPowerBehavior) {
        motor1.setZeroPowerBehavior(zeroPowerBehavior);
        motor2.setZeroPowerBehavior(zeroPowerBehavior);
    }

    @Override
    public void setPowerFloat() {
    }

    @Override
    public boolean getPowerFloat() {
        return false;
    }

    @Override
    public int getTargetPosition() {
        return motor1.getTargetPosition();
    }

    @Override
    public void setTargetPosition(int position) {
        motor1.setTargetPosition(position);
        motor2.setTargetPosition(position);
    }

    @Override
    public boolean isBusy() {
        return motor1.isBusy() || motor2.isBusy();
    }

    @Override
    public int getCurrentPosition() {
        return motor1.getCurrentPosition();
    }

    @Override
    public RunMode getMode() {
        return motor1.getMode();
    }

    @Override
    public void setMode(RunMode mode) {
        motor1.setMode(mode);
        motor2.setMode(mode);
    }

    @Override
    public Direction getDirection() {
        return motor1.getDirection();
    }

    @Override
    public void setDirection(Direction direction) {
        // Do nothing set the motors individually if you want to do this
    }

    @Override
    public double getPower() {
        return motor1.getPower();
    }

    @Override
    public void setPower(double power) {
        motor1.setPower(power);
        motor2.setPower(power);
    }

    @Override
    public Manufacturer getManufacturer() {
        return motor1.getManufacturer();
    }

    @Override
    public String getDeviceName() {
        return motor1.getDeviceName();
    }

    @Override
    public String getConnectionInfo() {
        return motor1.getConnectionInfo();
    }

    @Override
    public int getVersion() {
        return motor1.getVersion();
    }

    @Override
    public void resetDeviceConfigurationForOpMode() {
        motor1.resetDeviceConfigurationForOpMode();
        motor2.resetDeviceConfigurationForOpMode();
    }

    @Override
    public void close() {
        motor1.close();
        motor2.close();
    }
}
