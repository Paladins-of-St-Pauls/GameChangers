package org.firstinspires.ftc.teamcode.paladins.excalibur;

import static org.opencv.core.CvType.CV_8U;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

public class ColourCountVision {
    OpenCvCamera camera;
    ColourCountPipeline cvPipeline;

    public ColourCountVision(HardwareMap hardwareMap, Telemetry telemetry, int width, int height, Scalar colourLow, Scalar colourHigh) {
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"));
        cvPipeline = new ColourCountPipeline(telemetry, width, height, colourLow, colourHigh);

        camera.setPipeline(cvPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(cvPipeline.cameraWidth, cvPipeline.cameraHeight, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });
    }

    public int colourZone() {
        return cvPipeline.get_element_zone();
    }

    public int colourPercent(int zone) {
        if (zone == 1) {
            return cvPipeline.percentCount1;
        } else if (zone == 2) {
            return cvPipeline.percentCount2;
        } else if (zone == 3) {
            return cvPipeline.percentCount3;
        } else {
            return 0;
        }
    }


    public static class ColourCountPipeline extends OpenCvPipeline {

        final int cameraWidth;
        final int cameraHeight;

        final int line1x;
        final int line2x;


        Telemetry telemetry;

        final Rect zone1Rect, zone2Rect, zone3Rect;
        final Rect zone1TopRect, zone2TopRect, zone3TopRect;
        Mat zone1, zone2, zone3;

        Mat zone1top;
        Mat zone2top;
        Mat zone3top;

        final Scalar colourLow;
        final Scalar colourHigh;
        Mat countMat;
        Mat countSubmat1, countSubmat2, countSubmat3;

        int percentCount1 = 0;
        int percentCount2 = 0;
        int percentCount3 = 0;

        int inColourZone = 1;


        public ColourCountPipeline(Telemetry telemetry, int width, int height, Scalar colourLow, Scalar colourHigh) {
            this.telemetry = telemetry;
            this.cameraHeight = height;
            this.cameraWidth = width;
            line1x = cameraWidth / 3;
            line2x = (cameraWidth / 3) * 2;
            zone1Rect = new Rect(0, 0, line1x, cameraHeight);
            zone2Rect = new Rect(line1x, 0, line2x - line1x, cameraHeight);
            zone3Rect = new Rect(line2x, 0, cameraWidth - line2x, cameraHeight);
            zone1TopRect = new Rect(0, 0, line1x, 20);
            zone2TopRect = new Rect(line1x, 0, line2x - line1x, 20);
            zone3TopRect = new Rect(line2x, 0, cameraWidth - line2x, 20);
            this.colourLow = colourLow;
            this.colourHigh = colourHigh;
        }

        public int getCameraWidth() {
            return cameraWidth;
        }

        public int getCameraHeight() {
            return cameraHeight;
        }

        @Override
        public void init(Mat input) {
            zone1 = input.submat(zone1Rect);
            zone2 = input.submat(zone2Rect);
            zone3 = input.submat(zone3Rect);
            zone1top = input.submat(zone1TopRect);
            zone2top = input.submat(zone2TopRect);
            zone3top = input.submat(zone3TopRect);

            countMat = new Mat(cameraHeight, cameraWidth, CV_8U);
            countSubmat1 = countMat.submat(zone1Rect);
            countSubmat2 = countMat.submat(zone2Rect);
            countSubmat3 = countMat.submat(zone3Rect);
        }

        @Override
        public Mat processFrame(Mat input) {
            // Create a 1D image of pixels that fill in range. In range value has value of 255,
            // if out of range has value of 0
            Core.inRange(input, colourLow, colourHigh, countMat);

            // Count the number of non-zero pixels in each sub matrix, which will equal
            // the number of pixels in range and convert to an integer percentage value
            // That way we don't need to care about resolution.
            percentCount1 = (100 * Core.countNonZero(countSubmat1)) / (cameraWidth * cameraHeight);
            percentCount2 = (100 * Core.countNonZero(countSubmat2)) / (cameraWidth * cameraHeight);
            percentCount3 = (100 * Core.countNonZero(countSubmat3)) / (cameraWidth * cameraHeight);


            int maxCount = Math.max(percentCount1, Math.max(percentCount2, percentCount3));


            if (maxCount == percentCount1) {
                telemetry.addData("Zone 1 Has Element", percentCount1);
                inColourZone = 1;

            } else if (maxCount == percentCount2) {
                telemetry.addData("Zone 2 Has Element", percentCount2);
                inColourZone = 2;
            } else {
                telemetry.addData("Zone 3 Has Element", percentCount3);
                inColourZone = 3;
            }

            telemetry.addData(String.format("Pixel count %%: %4d%% - %4d%% - %4d%%  - in Zone ", percentCount1, percentCount2, percentCount3), inColourZone);

            //Putting averaged colors on zones (we can see on camera now)
            zone1top.setTo((inColourZone == 1) ? colourHigh : colourLow);
            zone2top.setTo((inColourZone == 2) ? colourHigh : colourLow);
            zone3top.setTo((inColourZone == 3) ? colourHigh : colourLow);

            telemetry.update();

            return input;
        }


        public int get_element_zone() {
            return inColourZone;
        }

    }
}
