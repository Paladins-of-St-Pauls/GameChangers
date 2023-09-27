package org.firstinspires.ftc.teamcode.paladins.joyeuse;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.paladins.common.ButtonControl;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.tasks.MessageTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
@Disabled
@Autonomous(name = "JoyeuseSimpleAutonomous")
public class JoyeuseSimpleAutonomous extends PaladinsOpMode {
    private JoyeuseConfiguration config;
    private JoyeuseDrive drive;
    private JoyeuseShoot shoot;
    private JoyeuseIntake intake;
    private ArrayDeque<Task> tasks = new ArrayDeque<>();

    private int ServoHoldTime = 1;

    @Override
    protected void onInit() {
        config = JoyeuseConfiguration.newConfig(hardwareMap, telemetry);

        drive = new JoyeuseDrive(this, config.leftMidMotor, config.leftBackMotor, config.rightMidMotor, config.rightBackMotor);
        shoot = new JoyeuseShoot(this, config.leftShooterMotor, config.rightShooterMotor);
        intake = new JoyeuseIntake(this, config.intakeMotor, config.bumpMotor, config.conveyorServo, config.indexerServo);

        HashMap<ButtonControl, String> buttonMap = new HashMap<>();

        buttonMap.put(ButtonControl.Y, "Blue Outer");
        buttonMap.put(ButtonControl.X, "Blue Inner");
        buttonMap.put(ButtonControl.A, "Red Outer");
        buttonMap.put(ButtonControl.B, "Red Inner");
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
                tasks.add(new JoyeuseDriveEncoderTask(this, 5, drive, 1000, 500, 0.3, 0.15));
                tasks.add(new JoyeuseDriveTask(this, 1, drive, 0, 0));
                tasks.add(new JoyeuseDriveEncoderTask(this, 5, drive, -1000, -500, 0.3, 0.15));

                break;

            case Y:
                // Tasks can go here
                tasks.add(new MessageTask(this, 1.0, "Running Y"));
                //        Drive forward
                tasks.add(new JoyeuseDriveTask(this, 1.8, drive, -0.5, -0.5));
                tasks.add(new JoyeuseDriveTask(this, 0.1, drive, 0, 0));

                tasks.add(new JoyeuseDriveTask(this, 1.25, drive, 0.1, -0.1));
                tasks.add(new JoyeuseDriveTask(this, 0.1, drive, 0, 0));
//        Shoot three rings
//        Spin up the shooters
                tasks.add(new JoyeuseSetShooterTask(this, 3, shoot, 0.8));
//        Shoot the rings
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.05));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.25));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.05));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.25));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.05));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.25));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.05));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.25));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.05));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.25));
//        Kill shooters
                tasks.add(new JoyeuseSetShooterTask(this, 0.1, shoot, 0));

//        Drive to white line
//        tasks.add(new TwoSensorTracerTask(this, 4, drive, -0.2, -0.2, config.leftColourSensor, config.rightColourSensor));

                tasks.add(new JoyeuseDriveTask(this, 1, drive, -0.5, -0.5));
                tasks.add(new JoyeuseDriveTask(this, 0.8, drive, -0.5, 0.5));
                tasks.add(new JoyeuseDriveTask(this, 0.1, drive, 0, 0));

                tasks.add(new JoyeuseWGTask(this, 1, config.wgArm, config.wgHook, true));
                break;
            case X:
                // Tasks can go here
                tasks.add(new MessageTask(this, 1.0, "Running X"));
                //        Drive forward
                tasks.add(new JoyeuseDriveTask(this, 1.8, drive, -0.5, -0.5));
                tasks.add(new JoyeuseDriveTask(this, 0.1, drive, 0, 0));

                tasks.add(new JoyeuseDriveTask(this, 1.25, drive, -0.1, 0.1));
                tasks.add(new JoyeuseDriveTask(this, 0.1, drive, 0, 0));

//        Shoot three rings
//        Spin up the shooters
                tasks.add(new JoyeuseSetShooterTask(this, 3, shoot, 0.8));
//        Shoot the rings
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.05));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.25));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.05));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.25));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.05));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.25));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.05));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.25));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.05));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.25));
//        Kill shooters
                tasks.add(new JoyeuseSetShooterTask(this, 0.1, shoot, 0));

//        Drive to white line
//        tasks.add(new TwoSensorTracerTask(this, 4, drive, -0.2, -0.2, config.leftColourSensor, config.rightColourSensor));

                tasks.add(new JoyeuseDriveTask(this, 1, drive, -0.5, -0.5));
                tasks.add(new JoyeuseDriveTask(this, 0.8, drive, 0.5, -0.5));
                tasks.add(new JoyeuseDriveTask(this, 0.1, drive, 0, 0));

                tasks.add(new JoyeuseWGTask(this, 1, config.wgArm, config.wgHook, true));
                break;
            case A:
                // Tasks can go here
                tasks.add(new MessageTask(this, 1.0, "Running A"));
                //        Drive forward
                tasks.add(new JoyeuseDriveTask(this, 1.8, drive, -0.5, -0.5));
                tasks.add(new JoyeuseDriveTask(this, 0.1, drive, 0, 0));
//        Shoot three rings
//        Spin up the shooters
                tasks.add(new JoyeuseSetShooterTask(this, 3, shoot, 0.9));
//        Shoot the rings
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.05));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.25));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.05));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.25));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.05));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.25));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.05));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.25));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.05));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.25));
//        Kill shooters
                tasks.add(new JoyeuseSetShooterTask(this, 0.1, shoot, 0));

//        Drive to white line
//        tasks.add(new TwoSensorTracerTask(this, 4, drive, -0.2, -0.2, config.leftColourSensor, config.rightColourSensor));

                tasks.add(new JoyeuseDriveTask(this, 1, drive, -0.5, -0.5));
//        tasks.add(new JoyeuseDriveTask(this, 1, drive, -0.5, 0.5));
                tasks.add(new JoyeuseDriveTask(this, 0.1, drive, 0, 0));

                tasks.add(new JoyeuseWGTask(this, 1, config.wgArm, config.wgHook, true));
                break;
            case B:
                // Tasks can go here
                tasks.add(new MessageTask(this, 1.0, "Running B"));
                //        Drive forward
                tasks.add(new JoyeuseDriveTask(this, 1.8, drive, -0.5, -0.5));
                tasks.add(new JoyeuseDriveTask(this, 0.1, drive, 0, 0));
//        Shoot three rings
//        Spin up the shooters
                tasks.add(new JoyeuseSetShooterTask(this, 3, shoot, 1));
//        Shoot the rings
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.05));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.25));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.05));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.25));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.05));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.25));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.05));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.25));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.05));
                tasks.add(new JoyeuseIndexerShootTask(this, ServoHoldTime, intake, 0.25));
//        Kill shooters
                tasks.add(new JoyeuseSetShooterTask(this, 0.1, shoot, 0));

//        Drive to white line
//        tasks.add(new TwoSensorTracerTask(this, 4, drive, -0.2, -0.2, config.leftColourSensor, config.rightColourSensor));

                tasks.add(new JoyeuseDriveTask(this, 1, drive, -0.5, -0.5));
                tasks.add(new JoyeuseDriveTask(this, 0.8, drive, -0.5, 0.5));
                tasks.add(new JoyeuseDriveTask(this, 0.1, drive, 0, 0));

                tasks.add(new JoyeuseWGTask(this, 1, config.wgArm, config.wgHook, true));
                break;
        }

        tasks.add(new JoyeuseWGTask(this, 1, config.wgArm, config.wgHook, false));

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