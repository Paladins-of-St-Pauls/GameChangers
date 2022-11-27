

/*
 * Copyright (c) 2021 OpenFTC Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.firstinspires.ftc.teamcode.paladins.cortana;
/*
 * Copyright (c) 2021 OpenFTC Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
// robotcore
import static java.lang.Boolean.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
// opencv
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.paladins.durandal.DurandalConfiguration;
import org.firstinspires.ftc.teamcode.paladins.durandal.DurandalDrive;
import org.firstinspires.ftc.teamcode.paladins.durandal.DurandalDriveEncoderTask;
import org.firstinspires.ftc.teamcode.paladins.durandal.DurandalSpinnerTask;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
// utils
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

// robot config imports
import org.firstinspires.ftc.teamcode.paladins.common.ButtonControl;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.cortana.CortanaConfiguration;
import org.firstinspires.ftc.teamcode.paladins.cortana.CortanaDriveTask;
import org.firstinspires.ftc.teamcode.paladins.joyeuse.JoyeuseDriveEncoderTask;
import org.firstinspires.ftc.teamcode.paladins.durandal.DurandalDriveTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.MessageTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;
import org.firstinspires.ftc.teamcode.paladins.tasks.WaitTask;

@Autonomous(name="OpenCV tasks")
public class CortanaAprilTagAutonomous extends PaladinsOpMode
{
    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    static final double FEET_PER_METER = 3.28084;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;
    boolean IS_BLUE = FALSE;

    private CortanaConfiguration config;
    private CortanaDrive drive;
    private ArrayDeque<Task> tasks = new ArrayDeque<>();


    // UNITS ARE METERS
    double tagsize = 0.05;

    int ID_TAG_OF_INTEREST = 111;
    TreeMap<Integer, Integer> TAGS_OF_INTEREST = new TreeMap<>();

    AprilTagDetection tagOfInterest = null;

    @Override
    protected void onInit() {
        config = CortanaConfiguration.newConfig(hardwareMap, telemetry);
        drive = new CortanaDrive(this, config.frontLeftMotor, config.frontRightMotor, config.backLeftMotor, config.backRightMotor);
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(1920,1080, OpenCvCameraRotation.UPRIGHT);
            }

//            @Override
//            public void onError(int errorCode) {}
        });

        telemetry.setMsTransmissionInterval(50);

        HashMap<ButtonControl, String> buttonMap = new HashMap<>();
        buttonMap.put(ButtonControl.LEFT_BUMPER, "Blue");
        buttonMap.put(ButtonControl.RIGHT_BUMPER, "Red");
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
                if (ButtonControl.isSelected(gamepad1, es.getKey())) {
                    selectedButton = es.getKey();
                    break outerLoop;
                }
            }

            idle();
        }
        telemetry.addLine(String.format("%s was selected: Running %s", selectedButton.name(), buttonMap.get(selectedButton)));
        telemetry.addLine("IF THIS SELECTION IS INCORRECT, QUIT THE OPMODE AND CHOOSE RESELECT");
        telemetry.update();
        telemetry.setAutoClear(autoClearState);
        telemetry.addLine(String.format("Loading tasks for %s", selectedButton.name()));
        switch (selectedButton) {
            case LEFT_BUMPER: // TEST BLUE
                IS_BLUE = TRUE;
                break;

            case RIGHT_BUMPER: // TEST RED
                IS_BLUE = FALSE;
                break;
        }

        TAGS_OF_INTEREST.put(111,1);
        TAGS_OF_INTEREST.put(222,2);
        TAGS_OF_INTEREST.put(333,3);

        int count=0;
        while (tagOfInterest == null && !isStarted() && !isStopRequested()) {
            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();
            if (currentDetections.size() != 0) {
                for (AprilTagDetection tag : currentDetections) {
                    if (TAGS_OF_INTEREST.containsKey(tag.id)) {
                        tagOfInterest = tag;
                        telemetry.addLine(String.format("Tag id: %d", tag.id));
                        break;

                    } else {
                        telemetry.addLine(String.format("Unrecognised Tag id: %d", tag.id));
                    }
                }
            } else {
                telemetry.addLine(String.format("No detections: %d", count));
            }
            count++;
            telemetry.update();
            sleep(20);
        }

        /* Actually do something useful */
        if(tagOfInterest == null)
        {
            tasks.add(new CortanaDriveTask(this, 1, drive, -1,1,1,-1));
        }
        else
        {
            /*
             * Insert your autonomous code here, probably using the tag pose to decide your configuration.
             */

            // e.g.
            if(tagOfInterest.id == 111 && IS_BLUE == TRUE)
            {
                tasks.add(new CortanaDriveTask(this, 1, drive, -1, 1,1,-1));
                tasks.add(new CortanaDriveTask(this, 1, drive, 1,1,1,1));
            }
            else if(tagOfInterest.id == 222 && IS_BLUE == TRUE)
            {
                tasks.add(new CortanaDriveTask(this, 1, drive, -1, 1,1,-1));
                tasks.add(new CortanaDriveTask(this, 2, drive, 1,1,1,1));
            }
            else if(tagOfInterest.id == 333 && IS_BLUE == TRUE)
            {
                tasks.add(new CortanaDriveTask(this, 1, drive, -1, 1,1,-1));
                tasks.add(new CortanaDriveTask(this, 3, drive, 1,1,1,1));
            }
            if(tagOfInterest.id == 111 && IS_BLUE == FALSE)
            {
                tasks.add(new CortanaDriveTask(this, 1, drive, 1, -1,-1,1));
                tasks.add(new CortanaDriveTask(this, 1, drive, 1,1,1,1));
            }
            else if(tagOfInterest.id == 222 && IS_BLUE == FALSE)
            {
                tasks.add(new CortanaDriveTask(this, 1, drive, 1, -1,-1,1));
                tasks.add(new CortanaDriveTask(this, 2, drive, 1,1,1,1));
            }
            else if(tagOfInterest.id == 333 && IS_BLUE == FALSE)
            {
                tasks.add(new CortanaDriveTask(this, 1, drive, 1, -1,-1,1));
                tasks.add(new CortanaDriveTask(this, 3, drive, 1,1,1,1));
            }
        }

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
            config.backLeftMotor.setPower(0);
            config.backRightMotor.setPower(0);
            config.frontLeftMotor.setPower(0);
            config.frontRightMotor.setPower(0);
            config.liftMotor.setPower(0);
        }
    }



}