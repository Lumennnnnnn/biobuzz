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
