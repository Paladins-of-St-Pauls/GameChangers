package org.firstinspires.ftc.teamcode.paladins.joyeuse;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.tasks.BaseTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;

public class JoyeuseWGTask extends BaseTask implements Task {
    final private Servo wgArm;
    final private Servo wgHook;
    final private boolean drop;

    public JoyeuseWGTask(PaladinsOpMode opMode, double time, Servo wgArm, Servo wgHook, boolean drop) {
        super(opMode, time);
        this.wgArm = wgArm;
        this.wgHook = wgHook;
        this.drop = drop;
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void run() {
        if (drop) {
            wgArm.setPosition(0.5);
            wgHook.setPosition(1);
        } else {
            wgArm.setPosition(1);
            wgHook.setPosition(0);
        }


        if (isFinished()) {
            return;
        }
    }

}
