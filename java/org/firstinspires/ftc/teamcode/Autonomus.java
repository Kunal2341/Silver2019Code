package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Devices.MotorGroup;
import org.firstinspires.ftc.teamcode.Robots.ArcadeBot;
import org.firstinspires.ftc.teamcode.Robots.StrafeBot;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous(name = "Move", group = "Concept")
public class Autonomus extends LinearOpMode {

    private DcMotor frontLeft, frontRight, backLeft, backRight, elevLeft, elevRight, arm;
    private MotorGroup left, right, up, down;

    private Servo leftServo, rightServo, handTopServo, handBottomServo;

    private ArcadeBot robot;
    private StrafeBot strafe;
    private int counter;
    private int thingthing = 0;

    double ticksPerRevolution = 1440; // Number of encoder ticks per motor rotation
    private ElapsedTime runtime = new ElapsedTime();




        @Override
        /*
        while(opModeIsActive()){
            frontLeft.setPower(-0.5);
            backLeft.setPower(-0.5);
            frontRight.setPower(0.5);
            backRight.setPower(0.5);
        }

         */

        public void runOpMode() {
            frontLeft = hardwareMap.get(DcMotor.class, "fL");
            frontRight = hardwareMap.get(DcMotor.class, "fR");
            backLeft = hardwareMap.get(DcMotor.class, "bL");
            backRight = hardwareMap.get(DcMotor.class, "bR");

            elevLeft = hardwareMap.get(DcMotor.class, "eLeft");
            elevRight = hardwareMap.get(DcMotor.class, "eRight");

            arm = hardwareMap.get(DcMotor.class, "arm");

            leftServo = hardwareMap.get(Servo.class, "leftServo"); //left servo going in left dir
            rightServo = hardwareMap.get(Servo.class, "rightServo");

            handTopServo = hardwareMap.get(Servo.class, "topSer");
            handBottomServo = hardwareMap.get(Servo.class, "botSer");

            leftServo.setDirection(Servo.Direction.REVERSE);
            rightServo.setDirection(Servo.Direction.FORWARD);

            handTopServo.setDirection(Servo.Direction.REVERSE);
            handBottomServo.setDirection(Servo.Direction.FORWARD);

            elevLeft.setDirection(DcMotor.Direction.REVERSE);
            elevRight.setDirection(DcMotor.Direction.FORWARD);

            arm.setDirection(DcMotor.Direction.REVERSE);
            arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            left = new MotorGroup(new DcMotor[]{frontLeft, backLeft});
            right = new MotorGroup(new DcMotor[]{frontRight, backRight});
            down = new MotorGroup(new DcMotor[]{frontLeft, backRight});
            up = new MotorGroup(new DcMotor[]{backLeft, frontRight});

            robot = new ArcadeBot(left, right);
            strafe = new StrafeBot(down, up);
            telemetry.addData("Status", "Initialized");
            telemetry.addData("Hello Driver", "Press Play Button");
            telemetry.update();
            //reset servos


            waitForStart();


            //FIRST NEED TO TURN
            /*
            frontLeft.setPower(0.5);
            backLeft.setPower(0.5);
            frontRight.setPower(0.5);
            backRight.setPower(0.5);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

             */


            //MOVE
            frontLeft.setPower(-0.5);
            backLeft.setPower(-0.5);
            frontRight.setPower(0.5);
            backRight.setPower(0.5);

            //justWait(2000);

            try {
                Thread.sleep(870);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            frontLeft.setPower(0.0);
            backLeft.setPower(0.0);
            frontRight.setPower(0.0);
            backRight.setPower(0.0);

        }



        public void foundation(double position) {
            // The motors are being set to a speed given within the parameters
            leftServo.setPosition(position);
            rightServo.setPosition(position);

        }
        public void justWait(int millisec){
            double currTime = getRuntime();
            double waitTime = currTime + (double) (millisec/1000);
            while (getRuntime() < waitTime){

            }
        }
        public void driveMethod( double rightSpeed, double leftSpeed, double driving_time) {
            // The motors are being set to a speed given within the parameters
            if (counter <= driving_time) {
                frontLeft.setPower(leftSpeed);
                backLeft.setPower(leftSpeed);
                frontRight.setPower(rightSpeed);
                backRight.setPower(rightSpeed);
                //Thread.sleep(1000);
            }




            // Resets the runtime to 0 so that when calculating speed, the previous time is not impacting the amount of time a call should
            // run for (double sec). If you are still confused about this ask Shreya.
            runtime.reset();

            // Runs the action only if the OpMode is still running and active and if the reset runtime is less than the variable, double sec.


            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();


        }

    }


    //counter++ how many times does the loop run in a second
    //tf is going on with the iterative loop
