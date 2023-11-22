package org.firstinspires.ftc.teamcode.paladins.excalibur;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.opencv.core.Scalar;

@Autonomous(name = "Excalibur_RedRight_Autonomous")
public class ExcaliburAutonomousRedRight extends ExcaliburAutonomous {
    public ExcaliburAutonomousRedRight() {
        super(Alliance.REDRIGHT, new Scalar(127.0, 0.0, 0.0, 0.0), new Scalar(255.0, 127.0, 127.0, 255.0));

    }
}
