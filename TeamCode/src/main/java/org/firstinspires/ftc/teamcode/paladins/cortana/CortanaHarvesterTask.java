package org.firstinspires.ftc.teamcode.paladins.cortana;

import static org.firstinspires.ftc.teamcode.paladins.cortana.CortanaLift.lift_positions;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.tasks.BaseTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;
import org.firstinspires.ftc.teamcode.paladins.cortana.CortanaLift;

    public class CortanaHarvesterTask extends BaseTask implements Task {

        private final CortanaLift lift;
        private int liftIndex;
        private Boolean IsClampClosed;

        public CortanaHarvesterTask(PaladinsOpMode opMode, double time, CortanaLift drive, Boolean IsClampClosed, int LiftIndex) {
            super(opMode, time);
            this.lift = drive;
            this.liftIndex = liftIndex;
            this.IsClampClosed = IsClampClosed;
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
            // i didnt like this, so i kept it as a just in case way ;)
//            if (liftIndex == 0) {lift.liftDown(); lift.liftDown(); lift.liftDown(); lift.liftDown();};
//            if (liftIndex == 1) {lift.liftDown(); lift.liftDown(); lift.liftDown(); lift.liftDown(); lift.liftUp();};
//            if (liftIndex == 2) {lift.liftDown(); lift.liftDown(); lift.liftDown(); lift.liftDown(); lift.liftUp(); lift.liftUp();};
//            if (liftIndex == 3) {lift.liftDown(); lift.liftDown(); lift.liftDown(); lift.liftDown(); lift.liftUp(); lift.liftUp(); lift.liftUp();};
//            if (liftIndex == 4) {lift.liftDown(); lift.liftDown(); lift.liftDown(); lift.liftDown(); lift.liftUp(); lift.liftUp(); lift.liftUp(); lift.liftUp();};

            lift.liftMotor.setTargetPosition(lift_positions[liftIndex]);
            //thats much nicer

            if (IsClampClosed = true) {
                lift.liftClampClose();
            } else {
                lift.liftClampOpen();
            }
        }
    }
