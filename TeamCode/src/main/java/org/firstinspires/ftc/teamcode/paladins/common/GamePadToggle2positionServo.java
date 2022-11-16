package org.firstinspires.ftc.teamcode.paladins.common;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Operation to assist with Gamepad actions on DCMotors
 */
public class GamePadToggle2positionServo extends PaladinsComponent {


    private final Servo servo;
    private final Gamepad gamepad;

    private final double position1;
    private final double position2;
    private final ButtonControl control1;
    private final ButtonControl control2;

    private double lastPosition = -1.0;

    public GamePadToggle2positionServo(PaladinsOpMode opMode, Gamepad gamepad, Servo servo,
                                       ButtonControl control1, ButtonControl control2,
                                       double position1, double position2) {
        super(opMode);

        this.gamepad = gamepad;
        this.servo = servo;
        this.control1 = control1;
        this.control2 = control2;
        this.position1 = position1;
        this.position2 = position2;
    }

    /**
     * Update servo with latest gamepad state
     */
    public void update() {
        // Only toggle when the button state changes from false to true, ie when the
        // button is pressed down (and not when the button comes back up)
        double position = -1.0;
        boolean pressed = false;
        if (buttonPressed(gamepad, control1)) {
            position = position1;
            pressed = true;
        } else if (buttonPressed(gamepad, control2)) {
            position = position2;
            pressed = true;
        }

        if (pressed && lastPosition != position) {
            servo.setPosition(position);
        }

        lastPosition = position;
    }
}


