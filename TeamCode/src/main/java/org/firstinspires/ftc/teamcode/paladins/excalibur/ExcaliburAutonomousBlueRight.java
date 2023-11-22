package org.firstinspires.ftc.teamcode.paladins.excalibur;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.opencv.core.Scalar;

@Autonomous(name = "Excalibur_BlueRight_Autonomous")
public class ExcaliburAutonomousBlueRight extends ExcaliburAutonomous {
    public ExcaliburAutonomousBlueRight() {
        super(Alliance.BLUERIGHT, new Scalar(0, 0, 127.0, 0.0), new Scalar(127.0, 127.0, 255.0, 255.0));
    }
}
