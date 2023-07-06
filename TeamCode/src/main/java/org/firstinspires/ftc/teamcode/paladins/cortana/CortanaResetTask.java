package org.firstinspires.ftc.teamcode.paladins.cortana;

import static org.firstinspires.ftc.teamcode.paladins.cortana.CortanaLift.lift_positions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.tasks.BaseTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;


public class CortanaResetTask extends BaseTask implements Task {

    private final CortanaLift lift;

    DcMotor liftMotor;


    public CortanaResetTask(PaladinsOpMode opMode, double time, CortanaLift lift) {
        super(opMode, time);
        this.lift = lift;
        this.liftMotor = liftMotor;
    }

    @Override
    public void init() {
        super.init();


        lift.liftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        lift.liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.liftMotor.setTargetPosition(0);
        lift.liftMotor.setPower(1);
    }

    @Override
    public void run() {
        if (isFinished()) {
            if (lift.isFinished()) {
                lift.liftMotor.setPower(0);
                lift.update();
                return;
            };
        }

        lift.liftMotor.setTargetPosition(0);
        lift.liftMotor.setPower(1);
        lift.update();

    }
}
