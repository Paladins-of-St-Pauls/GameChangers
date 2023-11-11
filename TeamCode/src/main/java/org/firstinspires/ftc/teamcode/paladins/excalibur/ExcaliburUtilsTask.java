package org.firstinspires.ftc.teamcode.paladins.excalibur;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.tasks.BaseTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;

public class ExcaliburUtilsTask extends BaseTask implements Task {

    private final ExcaliburUtils utils;

    private final double harvesterSpeed;
    private final double backOutakePos;
    private final double frontOutakeSpeed;
    private final double liftSpeed;


    public ExcaliburUtilsTask(PaladinsOpMode opMode, double time, ExcaliburUtils utils, double harvesterSpeed, double backOutakePos, double frontOutakeSpeed, double liftSpeed) {
        super(opMode, time);
        this.utils = utils;
        this.harvesterSpeed = harvesterSpeed;
        this.backOutakePos = backOutakePos;
        this.frontOutakeSpeed = frontOutakeSpeed;
        this.liftSpeed = liftSpeed;
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void run() {
        utils.harvesterSpeed = harvesterSpeed;
        utils.backOutakePos = backOutakePos;
        utils.frontOutakeSpeed = frontOutakeSpeed;
        utils.liftSpeed = liftSpeed;
        utils.update();
        if (isFinished()) {
            utils.harvesterSpeed = 0;
            utils.backOutakePos = 0;
            utils.frontOutakeSpeed = 0;
            utils.liftSpeed = 0;
            utils.update();
            return;
        }

    }

}
