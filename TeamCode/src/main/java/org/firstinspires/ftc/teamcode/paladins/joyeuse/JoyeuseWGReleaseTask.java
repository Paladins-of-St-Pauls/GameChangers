package org.firstinspires.ftc.teamcode.paladins.joyeuse;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.tasks.BaseTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;

public class JoyeuseWGReleaseTask extends BaseTask implements Task {

    public JoyeuseWGReleaseTask(PaladinsOpMode opMode, double time, Servo wgArm) {
        super(opMode, time);

    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void run() {
        opMode.telemetry.addLine("Dropping Wobble Goal");


        if (isFinished()) {
            return;
        }
    }

}
