package org.firstinspires.ftc.teamcode.paladins.joyeuse;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.util.ReadWriteFile;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.tasks.BaseTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

import java.io.File;
import java.util.ArrayDeque;

public class StackChoiceTask extends BaseTask implements Task {

    private final ArrayDeque<Task> tasks;
    private final ArrayDeque<Task> tasks_none_rings;
    private final ArrayDeque<Task> tasks_one_ring;
    private final ArrayDeque<Task> tasks_four_rings;

    private static Point topLeftAnchor;

    OpenCvWebcam webcam;
    StarterStack.StarterStackDeterminationPipeline pipeline;


    public StackChoiceTask(PaladinsOpMode opMode, double time, Point topLeftAnchor, ArrayDeque<Task> tasks, ArrayDeque<Task> tasks_none_rings, ArrayDeque<Task> tasks_one_ring, ArrayDeque<Task> tasks_four_rings) {
        super(opMode, time);
        this.tasks = tasks;
        this.tasks_none_rings = tasks_none_rings;
        this.tasks_one_ring = tasks_one_ring;
        this.tasks_four_rings = tasks_four_rings;

        this.topLeftAnchor = topLeftAnchor;
    }

    @Override
    public void init() {
        super.init();

        String filename = "StarterStackCalibration.txt";
        File readFile = AppUtil.getInstance().getSettingsFile(filename);
        String fileContents = ReadWriteFile.readFile(readFile);

        String[] nums = fileContents.split(",");

        int oneRingThreshold = Integer.parseInt(nums[0]);
        int fourRingThreshold = Integer.parseInt(nums[1]);

        int cameraMonitorViewId = opMode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", opMode.hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(opMode.hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        pipeline = new StarterStack.StarterStackDeterminationPipeline(oneRingThreshold, fourRingThreshold);
        webcam.setPipeline(pipeline);

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }
        });
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void run() {
        if (isFinished()) {
            if (pipeline.position == StarterStack.StarterStackDeterminationPipeline.RingPosition.FOUR) {
                tasks.addAll(tasks_four_rings);
            } else if (pipeline.position == StarterStack.StarterStackDeterminationPipeline.RingPosition.ONE) {
                tasks.addAll(tasks_one_ring);
            } else {
                tasks.addAll(tasks_none_rings);
            }
        }
    }

    public static class StarterStackDeterminationPipeline extends OpenCvPipeline {
        /*
         * An enum to define the skystone position
         */
        public enum RingPosition {
            FOUR,
            ONE,
            NONE
        }

        /*
         * Some color constants
         */
        static final Scalar BLUE = new Scalar(0, 0, 255);
        static final Scalar GREEN = new Scalar(0, 255, 0);

        /*
         * The core values which define the location and size of the sample regions
         */
        static final Point REGION1_TOPLEFT_ANCHOR_POINT = topLeftAnchor;

        static final int REGION_WIDTH = 100;
        static final int REGION_HEIGHT = 100;

        final int FOUR_RING_THRESHOLD;
        final int ONE_RING_THRESHOLD;

        public StarterStackDeterminationPipeline(int oneRingThreshold, int fourRingThreshold) {
            FOUR_RING_THRESHOLD = fourRingThreshold;
            ONE_RING_THRESHOLD = oneRingThreshold;
        }

        Point region1_pointA = new Point(
                REGION1_TOPLEFT_ANCHOR_POINT.x,
                REGION1_TOPLEFT_ANCHOR_POINT.y);
        Point region1_pointB = new Point(
                REGION1_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
                REGION1_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

        /*
         * Working variables
         */
        Mat region1_Cb;
        Mat YCrCb = new Mat();
        Mat Cb = new Mat();
        int avg1;

        // Volatile since accessed by OpMode thread w/o synchronization
        protected volatile StarterStack.StarterStackDeterminationPipeline.RingPosition position = StarterStack.StarterStackDeterminationPipeline.RingPosition.FOUR;

        /*
         * This function takes the RGB frame, converts to YCrCb,
         * and extracts the Cb channel to the 'Cb' variable
         */
        void inputToCb(Mat input) {
            Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
            Core.extractChannel(YCrCb, Cb, 1);
        }

        @Override
        public void init(Mat firstFrame) {
            inputToCb(firstFrame);

            region1_Cb = Cb.submat(new Rect(region1_pointA, region1_pointB));
        }

        @Override
        public Mat processFrame(Mat input) {
            inputToCb(input);

            avg1 = (int) Core.mean(region1_Cb).val[0];

            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    BLUE, // The color the rectangle is drawn in
                    2); // Thickness of the rectangle lines

            position = StarterStack.StarterStackDeterminationPipeline.RingPosition.FOUR; // Record our analysis
            if (avg1 > FOUR_RING_THRESHOLD) {
                position = StarterStack.StarterStackDeterminationPipeline.RingPosition.FOUR;
            } else if (avg1 > ONE_RING_THRESHOLD) {
                position = StarterStack.StarterStackDeterminationPipeline.RingPosition.ONE;
            } else {
                position = StarterStack.StarterStackDeterminationPipeline.RingPosition.NONE;
            }

            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    GREEN, // The color the rectangle is drawn in
                    4); // Negative thickness means solid fill

            return input;
        }

        public int getAnalysis() {
            return avg1;
        }
    }

}
