package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Devices.MotorGroup;

public class StrafeBot {

    private MotorGroup downD, upD;


    // if right_x and left_y have same sign
    // then it strafes upright and downleft
    // if right_x and left_y have opposite sign
    // then it strafes upleft and downright

    public StrafeBot(MotorGroup diag1, MotorGroup diag2) {
        downD = diag1;
        upD = diag2;
    }

    public void reverseDiag(MotorGroup diag, int scale) {
        if (scale >= 0) {
            diag.getMotor(0).setDirection(DcMotor.Direction.FORWARD);
            diag.getMotor(1).setDirection(DcMotor.Direction.REVERSE);
        } else {
            diag.getMotor(0).setDirection(DcMotor.Direction.REVERSE);
            diag.getMotor(1).setDirection(DcMotor.Direction.FORWARD);
        }
    }

    public void strafe(float x, float y) {
        if (x * y >= 0) {
            // strafe upright or downleft
            defaultDirection();
            if (Math.abs(x) > 0 && Math.abs(y) > 0) {
                // downD moves
                downD.setGroupPower(y);
                // upD becomes reversed
                reverseDiag(upD, -1);// changed the scale from 0 to -1
            }
            // strafe left or right
            else  {
                defaultDirection();
                downD.setGroupPower(x);
                upD.setGroupPower(x);
            }
        } else {
            // strafe upleft or downright
            defaultDirection();
            // upD moves
            upD.setGroupPower(y);
            // downD becomes reversed
            reverseDiag(downD, -1);
        }
    }

    private void defaultDirection() {
        downD.setDirection("default");
        upD.setDirection("default");

    }
    /*
    public void strafe(float x, float y) {
        if (x * y >= 0) {
            // strafe upright or downleft
            defaultDirection();
            if (Math.abs(x) > 0 && Math.abs(y) > 0) {
                // downD moves
                downD.setGroupPower(y);
                // upD becomes reversed
                reverseDiag(upD, 0);
                upD.setGroupPower(y);
            }
            // strafe left or right
            else  {
                defaultDirection();
                downD.setGroupPower(x);
                upD.setGroupPower(x);
            }
        } else {
            // strafe upleft or downright
            defaultDirection();
            // upD moves
            upD.setGroupPower(y);
            // downD becomes reversed
            reverseDiag(downD, -1);
            downD.setGroupPower(y);
        }
    }

    private void defaultDirection() {
        downD.setDirection("default");
        upD.setDirection("default");
    }
    */

}
