package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Libraries.Robot1;

@TeleOp(name = "Testing", group = "TeleOp")
public class Testing extends OpMode {
    Robot1 robot = new Robot1();

    @Override
    public void init() {
        robot.init(hardwareMap);
        telemetry.update();
    }

    @Override
    public void loop(){
        if(gamepad1.aWasPressed()){
            robot.setDesiredBackLeft(1);
        }
        if(gamepad1.bWasPressed()){
            robot.setDesiredBackRight(1);
        }
        if(gamepad1.yWasPressed()){
            robot.setDesiredFrontRight(1);
        }
        if(gamepad1.xWasPressed()){
            robot.setDesiredFrontLeft(1);
        }
        if(gamepad1.dpadDownWasPressed()){
            robot.setDesiredFrontLeft(0);
            robot.setDesiredFrontRight(0);
            robot.setDesiredBackRight(0);
            robot.setDesiredBackLeft(0);
        }
        robot.actMotors();
    }
}