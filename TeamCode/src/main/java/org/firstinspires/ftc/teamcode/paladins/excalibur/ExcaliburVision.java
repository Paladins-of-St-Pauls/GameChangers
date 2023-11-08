package org.firstinspires.ftc.teamcode.paladins.excalibur;

import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvPipeline;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import org.firstinspires.ftc.teamcode.paladins.excalibur.SplitAveragePipeline;

public class ExcaliburVision {
    public static int ElementDetection;
    OpenCvCamera camera;
    SplitAveragePipeline splitAveragePipeline;
    int camW = 1280;
    int camH = 720;

    int zone = 1;

    public ExcaliburVision(HardwareMap hardwareMap, Telemetry telemetry){
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"));
        splitAveragePipeline = new SplitAveragePipeline(telemetry);

        camera.setPipeline(splitAveragePipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(camW, camH, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });
    }

    public int ElementDetection(Telemetry telemetry) {
        zone = SplitAveragePipeline.get_element_zone();
        telemetry.addData("Element in zone", zone);
        return zone;
    }
}