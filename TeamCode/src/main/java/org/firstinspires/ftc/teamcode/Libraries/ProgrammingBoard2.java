package org.firstinspires.ftc.teamcode.mechanisms;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class ProgrammingBoard2 {
    private DcMotor flyWheelMotor;
    private double ticksPerRotation;


    public void init(HardwareMap hwMap){
        flyWheelMotor = hwMap.get(DcMotor.class, "exp1");
        flyWheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ticksPerRotation = flyWheelMotor.getMotorType().getTicksPerRev();

    }
    public void setMotorSpeed(double speed){
        flyWheelMotor.setPower(speed);
    }
    public double getMotorRotations(){
             return flyWheelMotor.getCurrentPosition() / ticksPerRotation;
    }
}