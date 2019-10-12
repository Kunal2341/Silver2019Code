package org.firstinspires.ftc.teamcode.Robots;

import org.firstinspires.ftc.teamcode.Devices.MotorGroup;

public class TankBot {

    private MotorGroup left, right;

    public TankBot(MotorGroup left, MotorGroup right) {
        this.left = left;
        this.right = right;
    }

    public void setMotorPower(float l, float r) {
        left.setGroupPower(l);
        right.setGroupPower(r);
    }

}
