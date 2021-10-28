package org.firstinspires.ftc.teamcode.paladins.common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;


/**
 * Operation to assist with Gamepad actions on DCMotors
 */
public class GamePadMomentaryMotor extends PaladinsComponent {

    private final ButtonControl buttonControl;

    private final DcMotor motor;
    private final Gamepad gamepad;
    private final float motorPower;
    private final Telemetry.Item item;
    private boolean showtelemetry = false;


    /**
     * Constructor for operation.  Telemetry enabled by default.
     *
     * @param opMode
     * @param gamepad       Gamepad
     * @param motor         DcMotor to operate on
     * @param buttonControl {@link ButtonControl}
     * @param power         power to apply when using gamepad buttons
     * @param showTelemetry display the power values on the telemetry
     */
    public GamePadMomentaryMotor(PaladinsOpMode opMode, Gamepad gamepad, DcMotor motor, ButtonControl buttonControl, float power, boolean showTelemetry) {
        super(opMode);

        this.gamepad = gamepad;
        this.motor = motor;
        this.buttonControl = buttonControl;
        this.motorPower = power;

        if (showTelemetry) {
            item = opMode.telemetry.addData("Control " + buttonControl.name(), 0.0f);
            item.setRetained(true);
        } else {
            item = null;
        }
    }

    public GamePadMomentaryMotor(PaladinsOpMode opMode, Gamepad gamepad, DcMotor motor, ButtonControl buttonControl, float power) {
        this(opMode, gamepad, motor, buttonControl, power, true);
    }


    /**
     * Update motors with latest gamepad state
     */
    public void update() {
        // Only toggle when the button state changes from false to true, ie when the
        // button is pressed down (and not when the button comes back up)
        boolean pressed = buttonPressed(gamepad, buttonControl);
        if (pressed) {
            motor.setPower(motorPower);
            if (showtelemetry) {
                getOpMode().telemetry.log().add("%s motor power: %.2f", buttonControl.name(), motorPower);
            }
        } else {
            motor.setPower(0.0);
        }


    }


}
