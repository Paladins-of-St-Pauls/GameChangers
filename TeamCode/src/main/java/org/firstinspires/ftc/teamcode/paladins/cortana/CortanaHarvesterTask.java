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
        DcMotor liftMotor;


        public CortanaHarvesterTask(PaladinsOpMode opMode, double time, CortanaLift lift, Boolean IsClampClosed, int liftPos) {
            super(opMode, time);
            this.lift = lift;
            this.IsClampClosed = IsClampClosed;
            this.liftMotor = liftMotor;
            this.liftPos = liftPos;
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

            if (IsClampClosed = true) {
                lift.liftClampClose();
            } else if (IsClampClosed = false) {
                lift.liftClampOpen();
            }
            lift.liftMotor.setTargetPosition(lift_positions[liftPos]);
            lift.liftMotor.setPower(1);
            lift.update();

        }
    }
