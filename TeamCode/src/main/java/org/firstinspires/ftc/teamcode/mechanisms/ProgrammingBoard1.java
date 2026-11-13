package org.firstinspires.ftc.teamcode.mechanisms;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class ProgrammingBoard1 {
    private TouchSensor touchSensor;
    private LED led;
    private DcMotor motor;
     private double ticksPerRotation;


    public void init(HardwareMap hwMap){

        touchSensor=hwMap.get(TouchSensor.class,"touch_sensor");
        led = hwMap.get(LED.class, "led3");
        motor = hwMap.get(DcMotor.class, "motor");
         motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         ticksPerRotation = motor.getMotorType().getTicksPerRev();
         motor.getPower();

    }

    public boolean getTouchSensorState(){
        return touchSensor.isPressed();
    }

    public boolean getReleased(){return !touchSensor.isPressed();}
    public String getTouchSensorStateWithWords(){
        if (touchSensor.isPressed()) {
            led.on();
            return "pressed";
        }
           else{
               led.off();
            return "!pressed";
        }
    }
    public void setMotorSpeed(double speed){
        motor.setPower(speed);
    }
    public double getMotorRotations(){
             return motor.getCurrentPosition() / ticksPerRotation;
    }

    public boolean isTouchSensorPressed() {
        return touchSensor.isPressed();
    }
}