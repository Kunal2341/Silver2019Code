package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.DcMotor;

public class PIDMotor {

  private DcMotor motor;
  private final long TICKS;

  public PIDMotor(DcMotor myMotor, long myTicks) {
    motor = myMotor;
    TICKS = myTicks;
  }
  
  public void setSpeed(float pow) {
    motor.setPower(pow);
  }
  
  public void setDirec(String dir) {
    if (dir.equals("default")) {
      motor.setDirection(DcMotor.Direction.FORWARD);
    } else {
      motor.setDirection(DcMotor.Direction.REVERSE);
    }
  }
  
  public float getSpeed() {
    return (float)motor.getPower();
  }
  
  public String getDirec() {
    return (motor.getDirection().equals(DcMotor.Direction.FORWARD)) ? "default" : "reverse";
  }
  
  public void resetEncoders() {
    motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
  }
  
  public long getNumTicks() {
    return TICKS;
  }
  
  public void setTarget(int tickVal) {
    motor.setTargetPosition(tickVal);
  }
  
  public int getPosition() {
    return motor.getCurrentPosition();
  }
  
  public int getTarget() {
    return motor.getTargetPosition();
  }
  
  public void runToPosition() {
    resetEncoders();
    motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    while (getPosition() < getTarget() && motor.isBusy()) {
      double err = TICKS / getTarget();
      setSpeed((float)((getTarget() - getPosition()) / getTarget() + err));
    }
    setSpeed(0); // just in case
    resetEncoders();
  }

}
