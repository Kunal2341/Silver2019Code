package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Devices.*;

public class PIDBot {

  private PIDMotor[] pids;
  private GyroSensor gyro;
 
  public PIDBot(DcMotor[] myMotors, GyroSensor myGyro, long ticks) {
    pids = new PIDMotor[myMotors.length];
    for (int i = 0; i < pids.length; i++) {
      pids[i] = new PIDMotor(myMotors[i], ticks);
    }
    gyro = myGyro;
  }
  
  public void driveTarget(long target) {
    // set targets
    for (PIDMotor pid : pids) {
      pid.setTarget((int)target);
    }
    // drive to position
    for (PIDMotor pid : pids) {
      pid.runToPosition();
    }
  }
  
  public void turnTarget(int degree) {
    gyro.calibrate();
    while (gyro.isCalibrating()) continue;
    
    // turn
    while (!gyro.isCalibrating() && gyro.getHeading() < degree) {
      for (PIDMotor pid : pids) {
        pid.setSpeed((degree - gyro.getHeading())/degree);
      }
    }
  }

}
