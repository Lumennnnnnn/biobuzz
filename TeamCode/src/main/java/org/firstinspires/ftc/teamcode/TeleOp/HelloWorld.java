package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class HelloWorld extends OpMode {
    @Override
    public void init(){
        telemetry.update();
    }
    @Override
    public void loop() {
        telemetry.addData("Pedro 3 Quickstart Hello!", 42);
        telemetry.update();
    }
}

