package org.firstinspires.ftc.teamcode.paladins.excalibur;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.Arrays;
import java.util.List;


public class SplitAveragePipeline extends OpenCvPipeline {

    int CAMERA_WIDTH = 1280;
    int CAMERA_HEIGHT = 720;

    final List<Integer> ELEMENT_COLOR = Arrays.asList(255, 255, 0); //(red, green, blue)

    int line1x = CAMERA_WIDTH / 3;
    int line2x = (CAMERA_WIDTH / 3) * 2;

    Telemetry telemetry;

    static int color_zone = 1;

    public SplitAveragePipeline(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public Mat processFrame(Mat input) {

        //input = input.submat(new Rect(0));

        //Defining Zones
        //Rect(top left x, top left y, bottom right x, bottom right y)
        Mat zone1 = input.submat(new Rect(0, 0, line1x, CAMERA_HEIGHT));
        Mat zone2 = input.submat(new Rect(line1x, 0, line2x - line1x, CAMERA_HEIGHT));
        Mat zone3 = input.submat(new Rect(line2x, 0, CAMERA_WIDTH - line2x, CAMERA_HEIGHT));
        Mat zone1bot = input.submat(new Rect(0, CAMERA_HEIGHT-20, line1x, 20));
        Mat zone2bot = input.submat(new Rect(line1x, CAMERA_HEIGHT-20, line2x - line1x, 20));
        Mat zone3bot = input.submat(new Rect(line2x, CAMERA_HEIGHT-20, CAMERA_WIDTH - line2x, 20));

        //Averaging the colors in the zones
        Scalar avgColor1 = Core.mean(zone1);
        Scalar avgColor2 = Core.mean(zone2);
        Scalar avgColor3 = Core.mean(zone3);

        //Putting averaged colors on zones (we can see on camera now)
        zone1bot.setTo(avgColor1);
        zone2bot.setTo(avgColor2);
        zone3bot.setTo(avgColor3);

        double distance1 = color_distance(avgColor1, ELEMENT_COLOR);
        double distance2 = color_distance(avgColor2, ELEMENT_COLOR);
        double distance3 = color_distance(avgColor3, ELEMENT_COLOR);

        double max_distance = Math.min(distance3, Math.min(distance1, distance2));

        if (max_distance == distance1) {
            telemetry.addData("Zone 1 Has Element", distance1);
            color_zone = 1;

        } else if (max_distance == distance2) {
            telemetry.addData("Zone 2 Has Element", distance2);
            color_zone = 2;
        } else {
            telemetry.addData("Zone 3 Has Element", distance3);
            color_zone = 3;
        }

        telemetry.addData("\nZone 1 Color", avgColor1);
        telemetry.addData("Zone 2 Color", avgColor2);
        telemetry.addData("Zone 3 Color", avgColor3);
        telemetry.update();
        return input;
    }

    public double color_distance(Scalar color1, List color2) {
        double r1 = color1.val[0];
        double g1 = color1.val[1];
        double b1 = color1.val[2];

        int r2 = (int) color2.get(0);
        int g2 = (int) color2.get(1);
        int b2 = (int) color2.get(2);

        return Math.sqrt(Math.pow((r1 - r2), 2) + Math.pow((g1 - g2), 2) + Math.pow((b1 - b2), 2));
    }

    public static int get_element_zone() {
        return color_zone;
    }

}