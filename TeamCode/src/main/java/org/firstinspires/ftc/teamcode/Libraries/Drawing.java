package org.firstinspires.ftc.teamcode.Libraries;

import com.bylazar.field.FieldManager;
import com.pedropathing.follower.Follower;
import com.bylazar.field.PanelsField;
import com.bylazar.field.Style;
import com.pedropathing.math.Pose;
import com.pedropathing.math.Vector;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathSegment;

import java.util.List;

public class Drawing {
    private final static FieldManager panelsField = PanelsField.INSTANCE.getField();

    private static final Style robotLook = new Style("", "#3F51B5", 0.75 );
    private static final Style historyLook = new Style("", "#4CAF50", 0.75);
    private static final int ROBOT_RADIUS = 9;

    /**
     * This prepares Panels Field for using Pedro Offsets
     */
    public static void init() {
        panelsField.setOffsets(PanelsField.INSTANCE.getPresets().getPEDRO_PATHING());
    }

    /**
     * This draws everything that will be used in the Follower's telemetryDebug() method. This takes
     * a Follower as an input, so an instance of the DashboardDrawingHandler class is not needed.
     *
     * @param follower Pedro Follower instance.
     */
    public static void drawDebug(Follower follower) {
        if (follower.currentPath() != null) {
            drawPath(follower.currentPath(), robotLook);
            Pose closestPoint = follower.closestPose();
            drawRobot(closestPoint, robotLook);
        }
        drawRobot(follower.pose(), historyLook);

        sendPacket();
    }

    /**
     * This draws a robot at a specified Pose with a specified
     * look. The heading is represented as a line.
     *
     * @param pose  the Pose to draw the robot at
     * @param style the parameters used to draw the robot with
     */
    public static void drawRobot(Pose pose, Style style) {
        if (pose == null || Double.isNaN(pose.x()) || Double.isNaN(pose.y()) || Double.isNaN(pose.heading())) {
            return;
        }

        panelsField.setStyle(style);
        panelsField.moveCursor(pose.x(), pose.y());
        panelsField.circle(ROBOT_RADIUS);

        // TODO: Draw heading indicator

    }

    /**
     * This draws a robot at a specified Pose. The heading is represented as a line.
     *
     * @param pose the Pose to draw the robot at
     */
    public static void drawRobot(Pose pose) {
        drawRobot(pose, robotLook);
    }

    /**
     * This draws a Path with a specified look.
     *
     * @param path  the Path to draw
     * @param style the parameters used to draw the Path with
     */
    public static void drawPath(Path path, Style style) {
        /*ist<PathSegment> segments = path.getSegments();
        for (PathSegment s : segments) {
        }
        double[][] points = path.getPanelsDrawingPoints();

        for (int i = 0; i < points[0].length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (Double.isNaN(points[j][i])) {
                    points[j][i] = 0;
                }
            }
        }

        panelsField.setStyle(style);
        panelsField.moveCursor(points[0][0], points[0][1]);
        panelsField.line(points[1][0], points[1][1]);
        */

    }


    /**
     * This tries to send the current packet to FTControl Panels.
     */
    public static void sendPacket() {
        panelsField.update();
    }
}
