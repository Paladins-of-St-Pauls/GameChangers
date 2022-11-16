package org.firstinspires.ftc.teamcode.paladins.common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;


/**
 * Operation to assist with Gamepad actions on DCMotors
 */
public class GamePadToggleDualMotor extends PaladinsComponent {

    private final ButtonControl buttonControl;
    private final ButtonControl reverseButtonControl;

    private final DcMotor motor1;
    private final DcMotor motor2;
    private final Gamepad gamepad;
    private final float motorPower;
    private final Telemetry.Item item;
    private boolean motorOn = false;
    private boolean reversed = false;
    private boolean lastButtonState = false;
    private double lastpower;
    private boolean showtelemetry = false;


    /**
     * Constructor for operation.  Telemetry enabled by default.
     *
     * @param opMode
     * @param gamepad       Gamepad
     * @param motor1        DcMotor to operate on
     * @param buttonControl {@link ButtonControl}
     * @param power         power to apply when using gamepad buttons
     * @param showTelemetry display the power values on the telemetry
     */
    public GamePadToggleDualMotor(PaladinsOpMode opMode, Gamepad gamepad, DcMotor motor1, DcMotor motor2,
                                  ButtonControl buttonControl, ButtonControl reverseButtonControl, float power, boolean showTelemetry) {
        super(opMode);

        this.gamepad = gamepad;
        this.motor1 = motor1;
        this.motor2 = motor2;
        this.buttonControl = buttonControl;
        this.reverseButtonControl = reverseButtonControl;
        this.motorPower = power;

        if (showTelemetry) {
            item = opMode.telemetry.addData("Control " + buttonControl.name(), 0.0f);
            item.setRetained(true);
        } else {
            item = null;
        }
    }

    public GamePadToggleDualMotor(PaladinsOpMode opMode, Gamepad gamepad, DcMotor motor1, DcMotor motor2,
                                  ButtonControl buttonControl, ButtonControl reverseButtonControl,
                                  float power) {
        this(opMode, gamepad, motor1, motor2, buttonControl, reverseButtonControl, power, true);
    }


    /**
     * Update motors with latest gamepad state
     */
    public void update() {
        // Only toggle when the button state changes from false to true, ie when the
        // button is pressed down (and not when the button comes back up)
        boolean pressed = buttonPressed(gamepad, buttonControl);
        boolean reversePressed = buttonPressed(gamepad, reverseButtonControl);

        if (pressed && lastButtonState != pressed) {
            motorOn = !motorOn;
            float power = motorOn ? motorPower : 0.0f;

            if (!reversePressed) {
                power = -power;
            }

            motor1.setPower(power);
            motor2.setPower(power);
            lastpower = power;
            if (item != null) {
                item.setValue(power);
            }
            if (showtelemetry) {
                getOpMode().telemetry.log().add("%s motor1 power: %.2f", buttonControl.name(), power);
            }
        }
        lastButtonState = pressed;
        if (lastpower != motor1.getPower()) {
            motor1.setPower(lastpower);
            motor2.setPower(lastpower);
        }

    }


}
