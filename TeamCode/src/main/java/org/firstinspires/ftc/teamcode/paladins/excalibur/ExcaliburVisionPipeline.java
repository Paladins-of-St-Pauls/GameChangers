package org.firstinspires.ftc.teamcode.paladins.excalibur;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class ExcaliburVisionPipeline extends OpenCvPipeline {

    double fx;
    double fy;
    double cx;
    double cy;
    int upperColorR;
    int upperColorG;
    int upperColorB;
    int lowerColorR;
    int lowerColorG;
    int lowerColorB;


    int[] blueLower = {98,50,50};
    int[] blueUpper = {139,255,255};

    public ExcaliburVisionPipeline(double fx, double fy, double cx, double cy, int upperColorR, int upperColorB, int upperColorG, int lowerColorR, int lowerColorG, int lowerColorB) {
        this.fx = fx;
        this.fy = fy;
        this.cx = cx;
        this.cy = cy;

        // why cant i use an array for this :( would be so much easier
        this.lowerColorR = lowerColorR;
        this.lowerColorG = lowerColorG;
        this.lowerColorB = lowerColorB;

        this.upperColorR = upperColorR;
        this.upperColorG = upperColorG;
        this.upperColorB = upperColorB;
    }


    @Override
    public Mat processFrame(Mat input)
    {
        //single out the color from the upper and lower limits set in the constructor then apply a filter
        //to the webcam image

        //code goes here

        //work out from the position of the colors/prop where it is (with BoundingBox?) and report an integer with
        //1 through 3 depending on which third of the image it is in

        //code goes here
        return input;
    }
}
