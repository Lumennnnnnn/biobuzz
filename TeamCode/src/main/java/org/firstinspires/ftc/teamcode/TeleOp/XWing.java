package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Libraries.Robot1;

@TeleOp(name = "X-Wing", group = "TeleOp")
public class XWing extends OpMode {
    Robot1 robot = new Robot1();

    @Override
    public void init() {
        robot.init(hardwareMap);
        telemetry.update();
    }

    @Override
    public void start() {}

    @Override
    public void loop() {
        //Driving----------------
        double y = gamepad1.left_stick_y / 2;   // Forward/back
        double x = -gamepad1.left_stick_x / 2;    // Strafe
        double rx = -gamepad1.right_stick_x / 2;  // Turn


        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1); //Math

        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        // Set motor powers
        robot.setDesiredFrontLeft(frontLeftPower);
        robot.setDesiredFrontRight(frontRightPower);
        robot.setDesiredBackLeft(backLeftPower);
        robot.setDesiredBackRight(backRightPower);

        robot.actMotors();
    }

    @Override
    public void stop() {
        telemetry.addLine("Stopped");
        telemetry.update();
    }

}
