package org.firstinspires.ftc.teamcode.paladins.excalibur;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;
import org.firstinspires.ftc.teamcode.paladins.tasks.WaitTask;

import java.util.ArrayDeque;

public class ExcaliburAutonomous extends PaladinsOpMode {
    private ExcaliburConfiguration config;
    private ExcaliburDrive drive;
    private ArrayDeque<Task> tasks = new ArrayDeque<>();

    public int element_zone = 1;
    private int ServoHoldTime = 1;

    final private Alliance alliance;

    private ExcaliburVision excaliburVision;

    public ExcaliburAutonomous(Alliance alliance) {
        super();
        this.alliance = alliance;
    }

    @Override
    protected void onInit() {
        config = ExcaliburConfiguration.newConfig(hardwareMap, telemetry);
        drive = new ExcaliburDrive(this, config.backLeftMotor, config.backRightMotor, config.frontLeftMotor, config.frontRightMotor);
        excaliburVision = new ExcaliburVision(hardwareMap, telemetry);

        tasks.add(new WaitTask(this, 3));
        tasks.add(new VisionTask(this, tasks, alliance, 27));
    }

    @Override
    protected void activeLoop() throws InterruptedException {

        Task currentTask = tasks.peekFirst();
        if (currentTask == null) {
            return;
        }
        currentTask.run();
        if (currentTask.isFinished()) {
            tasks.removeFirst();

        }
        if (tasks.isEmpty()) {
            drive.setPower(0, 0, 0, 0);
            drive.update();
        }
    }
}