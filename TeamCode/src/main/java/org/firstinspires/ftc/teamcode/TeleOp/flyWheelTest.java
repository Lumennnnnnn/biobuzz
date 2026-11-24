package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Libraries.ProgrammingBoard2;

@TeleOp
public class flyWheelTest extends OpMode {
    ProgrammingBoard2 board=new ProgrammingBoard2();

    double speed = 0;
    boolean go = false;
    @Override
    public void init() {
        board.init(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.dpadUpWasReleased()) {
            speed += 0.1;
        }
        if (gamepad1.dpadDownWasReleased()) {
            speed -= 0.1;
        }
        if (gamepad1.dpadRightWasReleased()) {
            speed += 0.01;
        }
        if (gamepad1.dpadLeftWasReleased()) {
            speed -= 0.01;
        }
        if (gamepad1.aWasReleased()) {
            go = !go;
        }
        speed = Range.clip(speed, -1,1);
        if (go) {
            board.setMotorSpeed(speed);
        } else {
            board.setMotorSpeed(0);
        }

        if (gamepad1.b) {
            speed = 0;
            go = false;
        }
        telemetry.addData("Speed: ", speed);
        telemetry.addData("Running:", go);
        telemetry.addData("Motor rotations", board.getMotorRotations());
    }
}
