package org.firstinspires.ftc.teamcode.paladins.durandal;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.paladins.common.ButtonControl;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.durandal.DurandalConfiguration;
import org.firstinspires.ftc.teamcode.paladins.durandal.DurandalDrive;
import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseDriveEncoderTask;
import org.firstinspires.ftc.teamcode.paladins.durandal.DurandalDriveTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.MessageTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

@Autonomous(name = "DurandalSimpleAutonomous")
public class DurandalSimpleAutonomous extends PaladinsOpMode {
    private DurandalConfiguration config;
    private DurandalDrive drive;
    private ArrayDeque<Task> tasks = new ArrayDeque<>();

    private int ServoHoldTime = 1;

    @Override
    protected void onInit() {
        config = DurandalConfiguration.newConfig(hardwareMap, telemetry);

        drive = new DurandalDrive(this, config.leftMotor, config.rightMotor);

        HashMap<ButtonControl, String> buttonMap = new HashMap<>();

        buttonMap.put(ButtonControl.Y, "Red #1");
        buttonMap.put(ButtonControl.X, "Red #2");
        buttonMap.put(ButtonControl.B, "Blue #1");
        buttonMap.put(ButtonControl.A, "Blue #2");
        buttonMap.put(ButtonControl.RIGHT_BUMPER, "Test");

        ButtonControl selectedButton = ButtonControl.X;

        boolean autoClearState = telemetry.isAutoClear();

        telemetry.setAutoClear(false);

        telemetry.addLine("PRESSING ONE OF THE FOLLOWING BUTTONS WILL INITIALISE THE ROBOT TO THE RELEVANT OPERATION MODE:");
        for (Map.Entry<ButtonControl, String> es : buttonMap.entrySet()) {
            System.out.printf("%s: %s%n", es.getKey().name(), es.getValue());
            telemetry.addLine(String.format("%s: %s", es.getKey().name(), es.getValue()));
            if (ButtonControl.isSelected(gamepad1, es.getKey())) {
                selectedButton = es.getKey();
            }
        }

        telemetry.addLine(String.format("IF NO BUTTON IS PRESSED WITHIN 3 SECONDS, *%s* WILL BE RUN", buttonMap.get(selectedButton)));
        telemetry.update();


        long startTime = System.nanoTime();

        outerLoop:
        while (System.nanoTime() < startTime + 3000000000L) {
            for (Map.Entry<ButtonControl, String> es : buttonMap.entrySet()) {
//                System.out.printf("%s: %s%n", es.getKey().name(), es.getValue());
//                telemetry.addLine(String.format("%s: %s", es.getKey().name(), es.getValue()));
                if (ButtonControl.isSelected(gamepad1, es.getKey())) {
                    selectedButton = es.getKey();
                    break outerLoop;
                }
            }

            idle();
        }

        telemetry.addLine(String.format("%s was selected: Running %s", selectedButton.name(), buttonMap.get(selectedButton)));
        telemetry.addLine("IF THIS SELECTION IS NOT DESIRABLE, QUIT THE OPMODE AND CHOOSE AGAIN");
        telemetry.update();

        telemetry.setAutoClear(autoClearState);


        telemetry.addLine(String.format("Loading tasks for %s", buttonMap.get(selectedButton)));
        switch (selectedButton) {
            case RIGHT_BUMPER:
//                tasks.add(new JoyeuseDriveEncoderTask(this, 5, drive, 1000, 1000, 0.3, 0.3));
//                tasks.add(new JoyeuseDriveTask(this, 2, drive, 0, 0));
//                tasks.add(new JoyeuseDriveEncoderTask(this, 5, drive, -1000, -1000, 0.3, 0.3));
//                tasks.add(new JoyeuseDriveEncoderTask(this, 5, drive, 1000, 500, 0.3, 0.15));
                tasks.add(new DurandalDriveTask(this, 1, drive, 1, 1));
//                tasks.add(new JoyeuseDriveEncoderTask(this, 5, drive, -1000, -500, 0.3, 0.15));

                break;

            case Y: // Red #1
                // Tasks can go here
                tasks.add(new MessageTask(this, 1.0, "Running Y"));
                //        READ BARCODE
                tasks.add(new DurandalDriveTask(this, 1, drive, 0.8, 0.8));
                //         OPERARE SPINNER
                tasks.add(new DurandalDriveTask(this, 1, drive, 0.5, -0.5));
                tasks.add(new DurandalDriveTask(this, 2, drive, 0.8, 0.8));
                //          LIFT TO APPROPRIATE LEVEL
                //          DEPOSIT CARGO
                //          LOWER LIFT
                tasks.add(new DurandalDriveTask(this, 2, drive, 1, 1));
                tasks.add(new DurandalDriveTask(this, 0.5, drive, 0.5, -0.5));
                break;
            case X:
                // Tasks can go here
                tasks.add(new MessageTask(this, 1.0, "Running X"));
                //        Drive forward
                tasks.add(new DurandalDriveTask(this, 1.8, drive, -0.5, -0.5));
                tasks.add(new DurandalDriveTask(this, 0.1, drive, 0, 0));

                break;
            case B:
                // Tasks can go here
                tasks.add(new MessageTask(this, 1.0, "Running A"));
                //        Drive forward
                tasks.add(new DurandalDriveTask(this, 1.8, drive, -0.5, -0.5));
                tasks.add(new DurandalDriveTask(this, 0.1, drive, 0, 0));
                break;
            case A:
                // Tasks can go here
                tasks.add(new MessageTask(this, 1.0, "Running B"));
                //        Drive forward
                tasks.add(new DurandalDriveTask(this, 1.8, drive, -0.5, -0.5));
                tasks.add(new DurandalDriveTask(this, 0.1, drive, 0, 0));
                break;
        }


//        Variable Delay
//        tasks.add(new JoyeuseDriveTask(this, 5, drive, 0,0));
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
            drive.setPower(0, 0);
            drive.update();
        }
    }
}