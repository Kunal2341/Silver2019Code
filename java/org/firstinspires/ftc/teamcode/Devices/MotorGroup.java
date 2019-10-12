package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.DcMotor;

public class MotorGroup {

    private DcMotor[] group;

    public MotorGroup(DcMotor[] motors) {
        group = motors;
    }

    public void setGroupPower(float pow) {
        for (DcMotor m : group) {
            m.setPower(pow);
        }
    }

    public void setDirection(String dir) {
        if (dir.equals("default")) {
            for (DcMotor m : group) {
                m.setDirection(DcMotor.Direction.FORWARD);
            }
        } else {
            for (DcMotor m : group) {
                m.setDirection(DcMotor.Direction.REVERSE);
            }
        }
    }

    public DcMotor getMotor(int index) {
        return group[index];
    }

}
