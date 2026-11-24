package org.firstinspires.ftc.teamcode.Libraries;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class ProgrammingBoard2 {
    private DcMotor intakeWheelMotor;
    private DcMotor flyWheelMotor;
    private double ticksPerRotation;


    public void init(HardwareMap hwMap){
        flyWheelMotor = hwMap.get(DcMotor.class, "exp1");
        flyWheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ticksPerRotation = flyWheelMotor.getMotorType().getTicksPerRev();
        intakeWheelMotor = hwMap.get(DcMotor.class, "exp0");
        intakeWheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void setMotorSpeed(double speed){
        flyWheelMotor.setPower(speed);
    }
    public void setintakeMotorSpeed(double speed){
        intakeWheelMotor.setPower(speed);
    }
    public double getMotorRotations(){
             return flyWheelMotor.getCurrentPosition() / ticksPerRotation;
    }
}