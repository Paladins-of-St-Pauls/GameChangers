package org.firstinspires.ftc.teamcode.paladins.joyeuse;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.tasks.BaseTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;

public class JoyeuseSetShooterTask extends BaseTask implements Task {

    private final JoyeuseShoot shoot;
    private final double shooterSpeed;

    public JoyeuseSetShooterTask(PaladinsOpMode opMode, double time, JoyeuseShoot shoot, double shooterSpeed) {
        super(opMode, time);
        this.shoot = shoot;
        this.shooterSpeed = shooterSpeed;
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
        shoot.setPower(shooterSpeed);
    }
}
