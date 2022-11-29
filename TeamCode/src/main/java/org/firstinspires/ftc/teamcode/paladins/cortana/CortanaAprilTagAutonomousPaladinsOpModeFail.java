//
//
///*
// * Copyright (c) 2021 OpenFTC Team
// *
// * Permission is hereby granted, free of charge, to any person obtaining a copy
// * of this software and associated documentation files (the "Software"), to deal
// * in the Software without restriction, including without limitation the rights
// * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// * copies of the Software, and to permit persons to whom the Software is
// * furnished to do so, subject to the following conditions:
// *
// * The above copyright notice and this permission notice shall be included in all
// * copies or substantial portions of the Software.
// * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// * SOFTWARE.
// */
//package org.firstinspires.ftc.teamcode.paladins.cortana;
///*
// * Copyright (c) 2021 OpenFTC Team
// *
// * Permission is hereby granted, free of charge, to any person obtaining a copy
// * of this software and associated documentation files (the "Software"), to deal
// * in the Software without restriction, including without limitation the rights
// * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// * copies of the Software, and to permit persons to whom the Software is
// * furnished to do so, subject to the following conditions:
// *
// * The above copyright notice and this permission notice shall be included in all
// * copies or substantial portions of the Software.
// * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// * SOFTWARE.
// */
//// robotcore
//
//import static java.lang.Boolean.FALSE;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//
//import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
//import org.firstinspires.ftc.teamcode.paladins.tasks.Task;
//
//import java.util.ArrayDeque;
//
//@Autonomous(name="No OpenCV")
//public class CortanaAprilTagAutonomousPaladinsOpModeFail extends PaladinsOpMode
//{
//    boolean IS_BLUE = FALSE;
//
//    private CortanaConfiguration config;
//    private CortanaDrive drive;
//    private ArrayDeque<Task> tasks = new ArrayDeque<>();
//
//
//    // UNITS ARE METERS
//    double tagsize = 0.05;
//
//    @Override
//    protected void onInit() {
//        config = CortanaConfiguration.newConfig(hardwareMap, telemetry);
//        drive = new CortanaDrive(this, config.frontLeftMotor, config.frontRightMotor, config.backLeftMotor, config.backRightMotor, config.liftMotor);
//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
////
////        HashMap<ButtonControl, String> buttonMap = new HashMap<>();
////        buttonMap.put(ButtonControl.LEFT_BUMPER, "Blue");
////        buttonMap.put(ButtonControl.RIGHT_BUMPER, "Red");
////        ButtonControl selectedButton = ButtonControl.X;
////        boolean autoClearState = telemetry.isAutoClear();
////        telemetry.setAutoClear(false);
////        telemetry.addLine("PRESSING ONE OF THE FOLLOWING BUTTONS WILL INITIALISE THE ROBOT TO THE RELEVANT OPERATION MODE:");
////        for (Map.Entry<ButtonControl, String> es : buttonMap.entrySet()) {
////            System.out.printf("%s: %s%n", es.getKey().name(), es.getValue());
////            telemetry.addLine(String.format("%s: %s", es.getKey().name(), es.getValue()));
////            if (ButtonControl.isSelected(gamepad1, es.getKey())) {
////                selectedButton = es.getKey();
////            }
////        }
////        telemetry.addLine(String.format("IF NO BUTTON IS PRESSED WITHIN 3 SECONDS, *%s* WILL BE RUN", buttonMap.get(selectedButton)));
////        telemetry.update();
////        long startTime = System.nanoTime();
////        outerLoop:
////        while (System.nanoTime() < startTime + 3000000000L) {
////            for (Map.Entry<ButtonControl, String> es : buttonMap.entrySet()) {
////                if (ButtonControl.isSelected(gamepad1, es.getKey())) {
////                    selectedButton = es.getKey();
////                    break outerLoop;
////                }
////            }
////
////            idle();
////        }
////        telemetry.addLine(String.format("%s was selected: Running %s", selectedButton.name(), buttonMap.get(selectedButton)));
////        telemetry.addLine("IF THIS SELECTION IS INCORRECT, QUIT THE OPMODE AND CHOOSE RESELECT");
////        telemetry.update();
////        telemetry.setAutoClear(autoClearState);
////        telemetry.addLine(String.format("Loading tasks for %s", selectedButton.name()));
////        switch (selectedButton) {
////            case LEFT_BUMPER: // TEST BLUE
////                IS_BLUE = TRUE;
////                break;
////
////            case RIGHT_BUMPER: // TEST RED
////                IS_BLUE = FALSE;
////                break;
////        }
//
//        tasks.add(new CortanaDriveTask(this, 1, drive, -1,1,1,-1,0));
//        tasks.add(new CortanaDriveTask(this, 2, drive, 1,1,1,1,0));
//
//    }
//
//    @Override
//    protected void activeLoop() throws InterruptedException {
//        Task currentTask = tasks.peekFirst();
//        if (currentTask == null) {
//            return;
//        }
//        currentTask.run();
//        if (currentTask.isFinished()) {
//            tasks.removeFirst();
//
//        }
//        if (tasks.isEmpty()) {
//            drive.setPower(0, 0,0, 0, 0);
//            drive.update();
//        }
//    }
//
//
//}