package org.firstinspires.ftc.teamcode.paladins.tasks;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;

public class WaitTask extends BaseTask implements Task {


    public WaitTask(PaladinsOpMode opMode, double time) {
        super(opMode, time);

    }

    void update() {

    }

    @Override
    public void run() {
        if (isFinished()) {
            return;
        }

    }

}
