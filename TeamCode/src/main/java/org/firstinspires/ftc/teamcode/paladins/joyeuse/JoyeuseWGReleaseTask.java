package org.firstinspires.ftc.teamcode.paladins.joyeuse;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.tasks.BaseTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;

public class JoyeuseWGReleaseTask extends BaseTask implements Task {
    final private Servo wgArm;
    final private Servo wgHand;

    public JoyeuseWGReleaseTask(PaladinsOpMode opMode, double time, Servo wgArm, Servo wgHand) {
        super(opMode, time);
        this.wgArm = wgArm;
        this.wgHand = wgHand;
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void run() {
        opMode.telemetry.addLine("Dropping Wobble Goal");
        wgArm.setPosition(0.5);
        wgHand.setPosition(1);


        if (isFinished()) {
            return;
        }
    }

}
