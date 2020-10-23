package org.firstinspires.ftc.teamcode.samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.RobotLog;

@Disabled
@TeleOp(name = "LoggingTest", group = "Samples")
public class SimpleLogging extends OpMode {

    long loopCount = 0;

    @Override
    public void init() {
        RobotLog.logDeviceInfo();
        RobotLog.dd("TEST", "init");
    }

    @Override
    public void loop() {
        loopCount++;

        if (loopCount % 1000 == 0) {
            RobotLog.dd("TEST", "Loop count: %d", loopCount);
        }
    }
}
