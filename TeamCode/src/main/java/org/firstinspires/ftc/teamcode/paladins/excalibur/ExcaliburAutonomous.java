package org.firstinspires.ftc.teamcode.paladins.excalibur;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;
import org.firstinspires.ftc.teamcode.paladins.tasks.WaitTask;
import org.opencv.core.Scalar;

import java.util.ArrayDeque;

public class ExcaliburAutonomous extends PaladinsOpMode {
    private ExcaliburConfiguration config;
    private ExcaliburDrive drive;
    private ArrayDeque<Task> tasks = new ArrayDeque<>();

    public int element_zone = 1;
    private int ServoHoldTime = 1;

    final private Alliance alliance;
    final private Scalar upperColour;
    final private Scalar lowerColour;

    ColourCountVision vision;

    public ExcaliburAutonomous(Alliance alliance, Scalar lowerColour, Scalar upperColour) {
        super();
        this.alliance = alliance;
        this.upperColour = upperColour;
        this.lowerColour = lowerColour;
    }

    @Override
    protected void onInit() {
        config = ExcaliburConfiguration.newConfig(hardwareMap, telemetry);
        drive = new ExcaliburDrive(this, config.backLeftMotor, config.backRightMotor, config.frontLeftMotor, config.frontRightMotor);
        vision = new ColourCountVision(hardwareMap, telemetry, 640, 480, lowerColour, upperColour);
        tasks.add(new VisionTask(this, config,  tasks, alliance, vision, 3));
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