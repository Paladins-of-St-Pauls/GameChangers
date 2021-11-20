package org.firstinspires.ftc.teamcode.paladins.durandal;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.tasks.BaseTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;

public class DurandalSpinnerTask extends BaseTask implements Task {

    private final double spinnerSpeed;
    DcMotor spinnerMotor;

    public DurandalSpinnerTask(PaladinsOpMode opMode, double time, DcMotor spinnerMotor, double spinnerSpeed) {
        super(opMode, time);
        this.spinnerMotor = spinnerMotor;
        this.spinnerSpeed = spinnerSpeed;
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void run() {
        spinnerMotor.setPower(spinnerSpeed);
        if (isFinished()) {
            spinnerMotor.setPower(0);
            return;
        }

    }

}
