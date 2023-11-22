package org.firstinspires.ftc.teamcode.paladins.excalibur;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.opencv.core.Scalar;

@Autonomous(name = "Excalibur_BlueLeft_Autonomous")
public class ExcaliburAutonomousBlueLeft extends ExcaliburAutonomous {
    public ExcaliburAutonomousBlueLeft() {
        super(Alliance.BLUELEFT, new Scalar(0.0, 0.0, 127.0, 0.0), new Scalar(127.0, 127.0, 255.0, 255.0));
    }
}
