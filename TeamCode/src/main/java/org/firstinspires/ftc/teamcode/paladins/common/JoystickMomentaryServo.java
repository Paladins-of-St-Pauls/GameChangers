package org.firstinspires.ftc.teamcode.paladins.common;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;


/**
 * Operation to assist with Gamepad actions on DCMotors
 */
public class JoystickMomentaryServo extends PaladinsComponent {

    private final ButtonControl buttonControl;
    private final JoystickControl joystickControl;

    private final CRServo motor;
    private final Gamepad gamepad;
    private final float motorPower;
    private final Telemetry.Item item;
    private boolean showtelemetry = false;


    /**
     * Constructor for operation.  Telemetry enabled by default.
     *
     * @param opMode
     * @param gamepad         Gamepad
     * @param motor           DcMotor to operate on
     * @param buttonControl   {@link ButtonControl}
     * @param joystickControl {@link JoystickControl}
     * @param power           power to apply when using gamepad buttons
     * @param showTelemetry   display the power values on the telemetry
     */
    public JoystickMomentaryServo(PaladinsOpMode opMode, Gamepad gamepad, CRServo motor, ButtonControl buttonControl, float power, JoystickControl joystickControl, boolean showTelemetry) {
        super(opMode);

        this.gamepad = gamepad;
        this.motor = motor;
        this.buttonControl = buttonControl;
        this.joystickControl = joystickControl;
        this.motorPower = power;

        if (showTelemetry) {
            item = opMode.telemetry.addData("Control " + buttonControl.name(), 0.0f);
            item.setRetained(true);
        } else {
            item = null;
        }
    }

    public JoystickMomentaryServo(PaladinsOpMode opMode, Gamepad gamepad, CRServo motor, ButtonControl buttonControl, float power, JoystickControl joystickControl) {
        this(opMode, gamepad, motor, buttonControl, power, joystickControl, true);
    }


    /**
     * Update motors with latest gamepad state
     */
    public void update() {
        boolean pressed = ButtonControl.isSelected(gamepad, buttonControl);
        float absJoystickValue = Math.abs(JoystickControl.getValue(gamepad, joystickControl));
        float power = (pressed) ? motorPower : 0.0f;
        if (absJoystickValue > Math.abs(power)) {
            power = absJoystickValue * Math.signum(power);
        }
        motor.setPower(power);
        if (showtelemetry) {
            getOpMode().telemetry.log().add("%s motor power: %.2f", buttonControl.name(), power);
        }


    }


}
