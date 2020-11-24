package org.firstinspires.ftc.teamcode.paladins.joyeuse;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.tasks.BaseTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;

public class JoyeuseWGDropTask extends BaseTask implements Task {

    public JoyeuseWGDropTask(PaladinsOpMode opMode, double time) {
        super(opMode, time);
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void run() {
        if (isFinished()) {
            return;
        }
        opMode.telemetry.addLine("Dropping Wobble Goal");
    }

}
