package org.firstinspires.ftc.teamcode.paladins.common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;


/**
 * Operation to assist with Gamepad actions on DCMotors
 */
public class GamePadToggleForwardReverseMotor extends PaladinsComponent {

    private final ButtonControl forwardButtonControl;
    private final ButtonControl backwardButtonControl;

    private final DcMotor motor1;
    private final DcMotor motor2;
    private final Gamepad gamepad;
    private final float motorPower;
    private final Telemetry.Item item;
    private boolean motorOn = false;
    private boolean lastButtonState = false;
    private double lastpower;
    private boolean showtelemetry = false;
    private MotorState lastMotorState = MotorState.STOPPED;

    /**
     * Constructor for operation.  Telemetry enabled by default.
     *
     * @param opMode
     * @param gamepad              Gamepad
     * @param motor1               DcMotor to operate on
     * @param forwardButtonControl {@link ButtonControl}
     * @param power                power to apply when using gamepad buttons
     * @param showTelemetry        display the power values on the telemetry
     */
    public GamePadToggleForwardReverseMotor(PaladinsOpMode opMode, Gamepad gamepad, DcMotor motor1, DcMotor motor2,
                                            ButtonControl forwardButtonControl, ButtonControl backwardButtonControl,
                                            float power, boolean showTelemetry) {
        super(opMode);

        this.gamepad = gamepad;
        this.motor1 = motor1;
        this.motor2 = motor2;
        this.forwardButtonControl = forwardButtonControl;
        this.backwardButtonControl = backwardButtonControl;
        this.motorPower = power;

        if (showTelemetry) {
            item = opMode.telemetry.addData("Control " + forwardButtonControl.name(), 0.0f);
            item.setRetained(true);
        } else {
            item = null;
        }
    }


    // Not finished yet

    public GamePadToggleForwardReverseMotor(PaladinsOpMode opMode, Gamepad gamepad, DcMotor motor1, DcMotor motor2,
                                            ButtonControl forwardButtonControl, ButtonControl backwardButtonControl,
                                            float power) {
        this(opMode, gamepad, motor1, motor2, forwardButtonControl, backwardButtonControl, power, true);
    }

    /**
     * Update motors with latest gamepad state
     */
    public void update() {
        // Only toggle when the button state changes from false to true, ie when the
        // button is pressed down (and not when the button comes back up)
        boolean pressed = buttonPressed(gamepad, forwardButtonControl);
        MotorState newState;
        if (buttonPressed(gamepad, forwardButtonControl)) {
            newState = MotorState.FORWARD;
        } else if (buttonPressed(gamepad, backwardButtonControl)) {
            newState = MotorState.REVERSE;
        } else {
            newState = MotorState.STOPPED;
        }

        if (newState == MotorState.FORWARD && lastMotorState != MotorState.FORWARD) {
            motor1.setPower(motorPower);
            motor2.setPower(motorPower);
            lastMotorState = newState;
        }

        if (pressed && lastButtonState != pressed) {
            motorOn = !motorOn;
            float power = motorOn ? motorPower : 0.0f;
            motor1.setPower(power);
            lastpower = power;
            if (item != null) {
                item.setValue(power);
            }
            if (showtelemetry) {
                getOpMode().telemetry.log().add("%s motor1 power: %.2f", forwardButtonControl.name(), power);
            }
        }
        lastButtonState = pressed;
    }


    enum MotorState {STOPPED, FORWARD, REVERSE}


}
