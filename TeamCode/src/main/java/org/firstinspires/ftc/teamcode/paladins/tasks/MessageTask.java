package org.firstinspires.ftc.teamcode.paladins.tasks;

import android.annotation.SuppressLint;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;

public class MessageTask extends BaseTask implements Task {

    private String message;

    public MessageTask(PaladinsOpMode opMode, double time, String message) {
        super(opMode, time);
        this.message = message;

    }

    @SuppressLint("DefaultLocale")
    @Override
    public void run() {
        if (isFinished()) {
            return;
        }
        opMode.telemetry.addLine(String.format("%s %.2f", message, time));
    }

}
