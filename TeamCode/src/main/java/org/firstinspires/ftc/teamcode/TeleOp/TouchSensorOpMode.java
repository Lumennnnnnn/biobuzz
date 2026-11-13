package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Libraries.ProgrammingBoard1;

@TeleOp()
public class TouchSensorOpMode extends OpMode{
    ProgrammingBoard1 board=new ProgrammingBoard1();
    @Override
        public void init(){
        board.init(hardwareMap);
        }
        @Override
        public void loop() {
            if(board.isTouchSensorPressed()) {
                board.setMotorSpeed(0.5);
            }
            else {
                board.setMotorSpeed(0.0);
            }
            if(gamepad1.aWasPressed()){
                board.setServoDirection(Servo.Direction.FORWARD);
                board.setServoPosition(1);
            }
            if(gamepad1.bWasPressed()){
                board.setServoPosition(0);
            }
            telemetry.addData("Touch sensor", board.getTouchSensorState());
            telemetry.addData("Is the touch sensor released", board.getReleased());
            telemetry.addData("", board.getTouchSensorStateWithWords());
            telemetry.addData("Motor rotations", board.getMotorRotations());
        }
    }