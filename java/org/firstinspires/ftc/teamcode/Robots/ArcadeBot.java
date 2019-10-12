package org.firstinspires.ftc.teamcode.Robots;

import org.firstinspires.ftc.teamcode.Devices.MotorGroup;

public class ArcadeBot {

    private MotorGroup left, right;

    public ArcadeBot(MotorGroup left, MotorGroup right) {
        this.left = left;
        this.right = right;
    }

    public void setMotorPower(float y, float x) {
        defaultDirection();
        if (Math.abs(y+x) <= 1 && Math.abs(y-x) <= 1) {
            left.setGroupPower((y + x));
            right.setGroupPower((y - x));
        } else {
            left.setGroupPower((float) (0.5 * (y+x)));
            right.setGroupPower((float) (0.5 * (y-x)));
        }
    }

    private void defaultDirection() {
        left.setDirection("default");
        right.setDirection("reverse");
    }

}
