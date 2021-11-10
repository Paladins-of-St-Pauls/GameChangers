package org.firstinspires.ftc.teamcode.paladins.common;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;


/**
 * Operation to assist with Gamepad actions on DCMotors
 */
public class JoystickMomentaryServo extends PaladinsComponent {

    private final ButtonControl forwardButtonControl;
    private final ButtonControl reverseButtonControl;
    private final CRServo motor;
    private final Gamepad gamepad;
    private final float forwardMotorPower;
    private final float reverseMotorPower;
    private final Telemetry.Item item;
    private boolean showtelemetry = false;


    /**
     * Constructor for operation.  Telemetry enabled by default.
     *
     * @param opMode
     * @param gamepad              Gamepad
     * @param motor                DcMotor to operate on
     * @param forwardButtonControl {@link ButtonControl}
     * @param joystickControl      {@link JoystickControl}
     * @param power                power to apply when using gamepad buttons
     * @param showTelemetry        display the power values on the telemetry
     */
    public JoystickMomentaryServo(PaladinsOpMode opMode, Gamepad gamepad, CRServo motor,
                                  ButtonControl forwardButtonControl, float forwardPower,
                                  ButtonControl reverseButtonControl, float reversePower,
                                  boolean showTelemetry) {
        super(opMode);

        this.gamepad = gamepad;
        this.motor = motor;
        this.forwardButtonControl = forwardButtonControl;
        this.reverseButtonControl = reverseButtonControl;
        this.forwardMotorPower = forwardPower;
        this.reverseMotorPower = reversePower;

        if (showTelemetry) {
            item = opMode.telemetry.addData("Control " + forwardButtonControl.name(), 0.0f);
            item.setRetained(true);
        } else {
            item = null;
        }
    }

    public JoystickMomentaryServo(PaladinsOpMode opMode, Gamepad gamepad, CRServo motor,
                                  ButtonControl forwardButtonControl, float forwardPower,
                                  ButtonControl reverseButtonControl, float reversePower) {
        this(opMode, gamepad, motor, forwardButtonControl, forwardPower, reverseButtonControl, reversePower, true);
    }
    public JoystickMomentaryServo(PaladinsOpMode opMode, Gamepad gamepad, CRServo motor,
                                  ButtonControl forwardButtonControl, float forwardPower) {
        this(opMode, gamepad, motor, forwardButtonControl, forwardPower, ButtonControl.NONE, 0.0f, true);
    }


    /**
     * Update motors with latest gamepad state
     */
    public void update() {
        boolean forwardPressed = ButtonControl.isSelected(gamepad, forwardButtonControl);
        boolean reversePressed = ButtonControl.isSelected(gamepad, reverseButtonControl);
        float power = (forwardPressed) ? forwardMotorPower : (reversePressed) ? reverseMotorPower : 0.0f;
        motor.setPower(power);
        if (showtelemetry) {
            getOpMode().telemetry.log().add("%s motor power: %.2f", forwardButtonControl.name(), power);
        }
    }


}
