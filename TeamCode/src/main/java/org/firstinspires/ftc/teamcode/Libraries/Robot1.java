package org.firstinspires.ftc.teamcode.Libraries;

import com.pedropathing.math.Pose;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public class Robot1 {
    double desiredFrontRight;
    double desiredFrontLeft;
    double desiredBackRight;
    double desiredBackLeft;

    IMU IMU;
    DcMotor FrontLeftMotor;
    DcMotor BackLeftMotor;
    DcMotor FrontRightMotor;
    DcMotor BackRightMotor;

    public void init(HardwareMap hardwareMap) { //init, runs during the init phase of teleop
        IMU = hardwareMap.get(IMU.class, "imu");
        FrontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
        BackLeftMotor = hardwareMap.dcMotor.get("backLeft");
        FrontRightMotor = hardwareMap.dcMotor.get("frontRight");
        BackRightMotor = hardwareMap.dcMotor.get("backRight");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        IMU.initialize(parameters);
        BackRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        FrontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void resetIMU() {
        IMU.resetYaw();
    }//this can be helpful

    public void setDesiredBackLeft(double desiredBackLeft) {
        this.desiredBackLeft = desiredBackLeft;
    }

    public void setDesiredBackRight(double desiredBackRight) {
        this.desiredBackRight = desiredBackRight;
    }

    public void setDesiredFrontLeft(double desiredFrontLeft) {
        this.desiredFrontLeft = desiredFrontLeft;
    }

    public void setDesiredFrontRight(double desiredFrontRight) {
        this.desiredFrontRight = desiredFrontRight;
    }

    public void motorTest(DcMotor motor, double power) {
        motor.setPower(power);
    }

    public void actMotors() {
        FrontRightMotor.setPower(desiredFrontRight);
        FrontLeftMotor.setPower(desiredFrontLeft);
        BackRightMotor.setPower(desiredBackRight);
        BackLeftMotor.setPower(desiredBackLeft);
    }
}

class Drawing {
    public static final double ROBOT_RADIUS = 9; // woah
    private static final FieldManager panelsField = PanelsField.INSTANCE.getField();

    private static final Style robotLook = new Style(
            "", "#3F51B5", 0.75
    );
    private static final Style historyLook = new Style(
            "", "#4CAF50", 0.75
    );


    public static void init() {
        panelsField.setOffsets(PanelsField.INSTANCE.getPresets().getPEDRO_PATHING());
    }

    public static void drawDebug(Follower follower) {
        if (follower.getCurrentPath() != null) {
            drawPath(follower.getCurrentPath(), robotLook);
            Pose closestPoint = follower.getPointFromPath(follower.getCurrentPath().getClosestPointTValue());
            drawRobot(new Pose(closestPoint.getX(), closestPoint.getY(), follower.getCurrentPath().getHeadingGoal(follower.getCurrentPath().getClosestPointTValue())), robotLook);
        }
        drawPoseHistory(follower.getPoseHistory(), historyLook);
        drawRobot(follower.getPose(), historyLook);

        sendPacket();
    }

    public static void drawRobot(Pose pose, Style style) {
        if (pose == null || Double.isNaN(pose.getX()) || Double.isNaN(pose.getY()) || Double.isNaN(pose.getHeading())) {
            return;
        }

        panelsField.setStyle(style);
        panelsField.moveCursor(pose.getX(), pose.getY());
        panelsField.circle(ROBOT_RADIUS);

        Vector v = pose.getHeadingAsUnitVector();
        v.setMagnitude(v.getMagnitude() * ROBOT_RADIUS);
        double x1 = pose.getX() + v.getXComponent() / 2, y1 = pose.getY() + v.getYComponent() / 2;
        double x2 = pose.getX() + v.getXComponent(), y2 = pose.getY() + v.getYComponent();

        panelsField.setStyle(style);
        panelsField.moveCursor(x1, y1);
        panelsField.line(x2, y2);
    }


    public static void drawRobot(Pose pose) {
        drawRobot(pose, robotLook);
    }


    public static void drawPath(Path path, Style style) {
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
    }

    public static void drawPath(PathChain pathChain, Style style) {
        for (int i = 0; i < pathChain.size(); i++) {
            drawPath(pathChain.getPath(i), style);
        }
    }


    public static void drawPoseHistory(PoseHistory poseTracker, Style style) {
        panelsField.setStyle(style);

        int size = poseTracker.getXPositionsArray().length;
        for (int i = 0; i < size - 1; i++) {

            panelsField.moveCursor(poseTracker.getXPositionsArray()[i], poseTracker.getYPositionsArray()[i]);
            panelsField.line(poseTracker.getXPositionsArray()[i + 1], poseTracker.getYPositionsArray()[i + 1]);
        }
    }


    public static void drawPoseHistory(PoseHistory poseTracker) {
        drawPoseHistory(poseTracker, historyLook);
    }

    public static void sendPacket() {
        panelsField.update();
    }
}