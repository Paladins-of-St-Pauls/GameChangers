package org.firstinspires.ftc.teamcode.paladins.common;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Operation to assist with Gamepad actions on DCMotors
 */
public class GamePadToggleServo extends PaladinsComponent {


    private final Servo servo;
    private final Gamepad gamepad;
    private final ButtonControl buttonControl;
    private final double[] positions;


    private int positionIndex = -1;
    private boolean lastButtonState = false;

    public GamePadToggleServo(PaladinsOpMode opMode, Gamepad gamepad, ButtonControl buttonControl, Servo servo, double[] positions, boolean setPositionOnStart) {
        super(opMode);

        this.gamepad = gamepad;
        this.buttonControl = buttonControl;
        this.servo = servo;
        this.positions = positions;

        if (setPositionOnStart) {
            incrementAndSetServo();
        }
    }

    public GamePadToggleServo(PaladinsOpMode opMode, Gamepad gamepad, ButtonControl buttonControl, Servo servo, double[] positions) {
        this(opMode, gamepad, buttonControl, servo, positions, false);
    }

    private void incrementAndSetServo() {
        positionIndex = (positionIndex + 1) % positions.length;
        servo.setPosition(positions[positionIndex]);
    }

    /**
     * Update servo with latest gamepad state
     */
    public void update() {
        // Only toggle when the button state changes from false to true, ie when the
        // button is pressed down (and not when the button comes back up)
        boolean buttonState = buttonPressed(gamepad, buttonControl);
        if (!lastButtonState && buttonState) {
            // Button changed from false to true
            incrementAndSetServo();
        }
        lastButtonState = buttonState;
    }
}


