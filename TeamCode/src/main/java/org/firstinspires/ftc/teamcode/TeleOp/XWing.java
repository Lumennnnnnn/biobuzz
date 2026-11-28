package org.firstinspires.ftc.teamcode.TeleOp;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Libraries.Drawing;
import org.firstinspires.ftc.teamcode.Libraries.Robot1;
import org.firstinspires.ftc.teamcode.pedro.Constants;

@TeleOp(name = "X-Wing", group = "TeleOp")
public class XWing extends OpMode {
    Robot1 robot = new Robot1();
    private Follower follower;

    private final TelemetryManager panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

    @Override
    public void start() {
        follower.setPose(Constants.lastPose);
        follower.update();
    }

    @Override
    public void init() {
        robot.init(hardwareMap);
        follower = Constants.create(hardwareMap);
        Drawing.init();
        panelsTelemetry.update();

    }

    @Override
    public void loop() {
        DrivePowers powers = ManualDrive.fieldCentric(
                -gamepad1.left_stick_y,
                -gamepad1.left_stick_x,
                -gamepad1.right_stick_x,
                follower.pose().heading()
        );
        panelsTelemetry.addData("X", follower.pose().x());
        panelsTelemetry.addData("Y", follower.pose().y());
        panelsTelemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
        panelsTelemetry.update();

        follower.manual(powers);
        follower.update();
        Drawing.drawDebug(follower);
    }

    @Override
    public void stop() {
        panelsTelemetry.update();
    }

}
