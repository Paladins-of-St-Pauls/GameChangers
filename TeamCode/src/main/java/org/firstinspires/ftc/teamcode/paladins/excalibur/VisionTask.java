package org.firstinspires.ftc.teamcode.paladins.excalibur;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.tasks.BaseTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.MessageTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;

import java.util.ArrayDeque;

public class VisionTask extends BaseTask implements Task {

    final private ArrayDeque<Task> tasks;
    final private Alliance alliance;

    public VisionTask(PaladinsOpMode opMode, ArrayDeque<Task> tasks, Alliance alliance, double time) {
        super(opMode, time);
        this.tasks = tasks;
        this.alliance = alliance;
    }

    void update() {

    }

    @Override
    public void run() {
        if (isFinished()) {
            return;
        }

        int zone = SplitAveragePipeline.get_element_zone();

        if (alliance == Alliance.RED) {
            if (zone == 1) {
                tasks.add(new MessageTask(opMode, 1, "Red Zone 1"));
            } else if (zone == 2) {
                tasks.add(new MessageTask(opMode, 1, "Red Zone 2"));
            } else if (zone == 3) {
                tasks.add(new MessageTask(opMode, 1, "Red Zone 3"));
            }
        }
        if (alliance == Alliance.BLUE) {
            if (zone == 1) {
                tasks.add(new MessageTask(opMode, 1, "Blue Zone 1"));
            } else if (zone == 2) {
                tasks.add(new MessageTask(opMode, 1, "Blue Zone 2"));
            } else if (zone == 3) {
                tasks.add(new MessageTask(opMode, 1, "Blue Zone 3"));
            }
        }
        this.isFinished = true;

    }

}
