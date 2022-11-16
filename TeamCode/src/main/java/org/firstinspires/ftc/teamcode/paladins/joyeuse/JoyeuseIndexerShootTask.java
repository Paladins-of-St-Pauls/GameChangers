package org.firstinspires.ftc.teamcode.paladins.joyeuse;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.tasks.BaseTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;

public class JoyeuseIndexerShootTask extends BaseTask implements Task {

    private final JoyeuseIntake intake;
    private double indexerPosition;

    public JoyeuseIndexerShootTask(PaladinsOpMode opMode, double time, JoyeuseIntake intake, double indexerPosition) {
        super(opMode, time);
        this.intake = intake;
        this.indexerPosition = indexerPosition;
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
        intake.setIndexerPos(indexerPosition);
    }
}
