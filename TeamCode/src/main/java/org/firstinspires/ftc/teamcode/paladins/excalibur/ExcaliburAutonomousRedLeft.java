package org.firstinspires.ftc.teamcode.paladins.excalibur;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.opencv.core.Scalar;

@Autonomous(name = "Excalibur_RedLeft_Autonomous")
public class ExcaliburAutonomousRedLeft extends ExcaliburAutonomous {
    public ExcaliburAutonomousRedLeft() {
        super(Alliance.REDLEFT, new Scalar(127.0, 0.0, 0.0, 0.0), new Scalar(255.0, 127.0, 127.0, 255.0));
    }
}
