package org.firstinspires.ftc.teamcode.paladins.joyeuse;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.tasks.BaseTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;

public class JoyeuseIndexerShootTask extends BaseTask implements Task {

    private final JoyeuseIntake intake;

    public JoyeuseIndexerShootTask(PaladinsOpMode opMode, double time, JoyeuseIntake intake) {
        super(opMode, time);
        this.intake = intake;
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void run() {
        if (isFinished()) {
            intake.setIndexerPos(0.25);
            return;
        }
        intake.setIndexerPos(0.12);
    }
}
