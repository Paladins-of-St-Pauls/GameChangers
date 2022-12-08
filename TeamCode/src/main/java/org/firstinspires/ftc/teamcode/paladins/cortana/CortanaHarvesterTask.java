package org.firstinspires.ftc.teamcode.paladins.cortana;

import static org.firstinspires.ftc.teamcode.paladins.cortana.CortanaLift.lift_positions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.tasks.BaseTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;


    public class CortanaHarvesterTask extends BaseTask implements Task {

        private final CortanaLift lift;
        private int liftPos;
        private Boolean IsClampClosed;

        public CortanaHarvesterTask(PaladinsOpMode opMode, double time, CortanaLift lift, Boolean IsClampClosed, int LiftPos) {
            super(opMode, time);
            this.lift = lift;
            this.IsClampClosed = IsClampClosed;
        }

        @Override
        public void init() {
            super.init();

            lift.liftClamp.setPosition(0.2);
            lift.liftClamp.scaleRange(0, 1);
            lift.liftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            lift.liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lift.liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lift.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lift.liftMotor.setTargetPosition(0);
            lift.liftPower = 0;
        }

        @Override
        public void run() {
            if (isFinished()) {
                if (lift.isFinished()) {
                    return;
                };
            }

            if (IsClampClosed = true) {
                lift.liftClampClose();
            } else if (IsClampClosed = false) {
                lift.liftClampOpen();
            }
            lift.liftMotor.setTargetPosition(lift_positions[liftPos]);
            lift.update();
        }
    }
