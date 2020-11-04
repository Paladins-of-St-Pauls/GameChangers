package org.firstinspires.ftc.teamcode.paladins.jeffs;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.paladins.common.PaladinsOpMode;
import org.firstinspires.ftc.teamcode.paladins.common.TankDrive;
import org.firstinspires.ftc.teamcode.paladins.tasks.TankDriveEncTask;
import org.firstinspires.ftc.teamcode.paladins.tasks.Task;

import java.util.ArrayDeque;

//@Disabled
@Autonomous(name = "BeltsShooterEncTest")
public class BeltsShooterEncTest extends PaladinsOpMode {
    private JeffsBotConfiguration config;
    private BeltsShoot shoot;
    private ArrayDeque<Task> tasks = new ArrayDeque<>();

    @Override
    protected void onInit() {
        config = JeffsBotConfiguration.newConfig(hardwareMap, telemetry);
        shoot = new BeltsShoot(this, config.leftMotor, config.rightMotor);
    }

    @Override
    protected void activeLoop() throws InterruptedException {
        shoot.setPower(0.8);
        shoot.update();
        Task currentTask = tasks.peekFirst();
        if (currentTask == null) {
            return;
        }
        currentTask.run();
        if (currentTask.isFinished()) {
            tasks.removeFirst();

        }
        if (tasks.isEmpty()) {
//            drive.setPower(0, 0);
//            drive.update();
        }
    }
}