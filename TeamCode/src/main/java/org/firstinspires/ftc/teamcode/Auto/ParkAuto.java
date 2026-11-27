package org.firstinspires.ftc.teamcode.Auto;

import com.bylazar.configurables.annotations.IgnoreConfigurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.robocol.TelemetryMessage;

import static com.pedropathing.api.Paths.line;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous(name = "ParkAuto", group = "Auto")
public class ParkAuto extends OpMode {
    private Follower follower;
    private final PoseFactory poseFactory = PoseFactory.degrees();
    @IgnoreConfigurable
    static TelemetryManager telemetryM;

    private final TelemetryManager panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

    // Poses
    private final Pose startPose = poseFactory.of(30, 8, 90);
    private final Pose parkPose = poseFactory.of(10, 94, 90);

    // Path methods

    private Path park(){
        return line(startPose, parkPose).linear(startPose, parkPose);
    }

    private Command autoRoutine() {
        return sequential(
                // Add mechanism commands here.
                follow(follower, park())
        );
    }

    @Override
    public void init() {
        Scheduler.reset();

        follower = Constants.create(hardwareMap);
        follower.setPose(startPose);
        follower.update();
    }

    @Override
    public void start() {
        schedule(autoRoutine());
    }

    @Override
    public void loop() {
        follower.update();
        Scheduler.execute();
        // add your other methods needed in the loop here
        Constants.lastPose = follower.pose();

        panelsTelemetry.addData("X", follower.pose().x());
        panelsTelemetry.addData("Y", follower.pose().y());
        panelsTelemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
        panelsTelemetry.addData("Follower Mode", follower.mode());
        panelsTelemetry.update();
    }
}