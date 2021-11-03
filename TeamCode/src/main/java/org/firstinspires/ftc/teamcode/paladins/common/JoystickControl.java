package org.firstinspires.ftc.teamcode.paladins.common;

import com.qualcomm.robotcore.hardware.Gamepad;

public enum JoystickControl {
    LEFT_STICK_X,
    LEFT_STICK_Y,
    RIGHT_STICK_X,
    RIGHT_STICK_Y,
    LEFT_TRIGGER,
    RIGHT_TRIGGER;


    public static float getValue(Gamepad gamepad, JoystickControl joystickControl) {
        switch (joystickControl) {

            case LEFT_STICK_X:
                return gamepad.left_stick_x;
            case LEFT_STICK_Y:
                return gamepad.left_stick_y;
            case RIGHT_STICK_X:
                return gamepad.right_stick_x;
            case RIGHT_STICK_Y:
                return gamepad.right_stick_y;
            case LEFT_TRIGGER:
                return gamepad.left_trigger;
            case RIGHT_TRIGGER:
                return gamepad.right_trigger;
            default:
                return 0.0f;
        }

    }
}
