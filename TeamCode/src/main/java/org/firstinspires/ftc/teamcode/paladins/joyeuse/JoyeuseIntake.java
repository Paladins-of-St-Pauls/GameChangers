package org.firstinspires.ftc.teamcode.paladins.joyeuse;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsComponent;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;

/**
 * Created by Caleb on 30/10/2020.
 */

public class JoyeuseIntake extends PaladinsComponent {
    private static float[] power_curve =
            {0.00f, 0.2f, 0.25f, 0.3f, 0.5f, 0.7f, 0.8f, 1.0f};
    private static float[] steer_curve =
            {0.00f, 0.2f, 0.25f, 0.3f, 0.35f, 0.4f, 0.5f, 1.0f};

    final private DcMotor intakeMotor;
    final private DcMotor bumpMotor;
    final private CRServo conveyorServo;
    final private Servo indexerServo;

    final private PaladinsOpMode opMode;



    public JoyeuseIntake(PaladinsOpMode opMode, DcMotor intakeMotor, DcMotor bumpMotor, CRServo conveyorServo, Servo indexerServo) {
        super(opMode);

        this.opMode = opMode;

        this.intakeMotor = intakeMotor;
        this.bumpMotor = bumpMotor;

        this.conveyorServo = conveyorServo;

        this.indexerServo = indexerServo;
    }

    /*
     * Update the motor power based on the gamepad state
     */
    @SuppressLint("DefaultLocale")
    public void setIntakePower(double power) {
        intakeMotor.setPower(power);
    }
    public void setBumpPower(double power) {
        bumpMotor.setPower(power);
    }
    public void setConveyorPower(double power) {
        conveyorServo.setPower(power);
    }
    public void setIndexerPos(double pos) {
        indexerServo.setPosition(pos);
    }


//    public void setIndexerPos(double pos) {
//        leftIndexerServo.setPosition(pos);
//        rightIndexerServo.setPosition(pos);
//    }

    /**
     * The  DC motors are scaled to make it easier to control them at slower speeds
     * The clip method guarantees the value never exceeds the range 0-1.
     */


    public boolean isFinished() {
        return !(intakeMotor.isBusy() | bumpMotor.isBusy());
    }
}
