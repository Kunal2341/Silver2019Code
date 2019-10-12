package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Devices.MotorGroup;
import org.firstinspires.ftc.teamcode.Robots.ArcadeBot;
import org.firstinspires.ftc.teamcode.Robots.StrafeBot;

@TeleOp(name="Mecanum")
public class MyTeleOp extends OpMode {

    private DcMotor frontLeft, frontRight, backLeft, backRight, elevLeft, elevRight, arm;
    private MotorGroup left, right, up, down;

    private Servo leftServo, rightServo, handTopServo, handBottomServo;

    private ArcadeBot robot;
    private StrafeBot strafe;

    double ticksPerRevolution = 1440; // Number of encoder ticks per motor rotation
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void init() {
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
    }

    @Override
    public void loop() {
        //reset servos


        robot.setMotorPower(gamepad1.right_stick_y, gamepad1.right_stick_x);
        //strafe.strafe(gamepad1.right_stick_x, gamepad1.left_stick_y);
        if (gamepad1.left_stick_x >= 0.9 && gamepad1.left_stick_y < 0.2 && gamepad1.left_stick_y > -0.2){
            strafeRight();
        }
        if (gamepad1.left_stick_x <= -0.9 && gamepad1.left_stick_y < 0.2 && gamepad1.left_stick_y > -0.2){
            strafeLeft();
        }
        if (gamepad2.left_stick_y > 0.2) {
            elevatorDown(0.4);
        }
        if (gamepad2.left_stick_y == 0.0) {
            elevatorUp(0.0);
        }
        if (gamepad2.left_stick_y < -0.2) {
            elevatorUp(0.4);
        }
        if (gamepad2.left_stick_y < 0.2 && gamepad2.left_stick_y > -0.2)
        {
            elevLeft.setPower(0.0);
            elevRight.setPower(0.0);
        }
        /*
        if (gamepad2.left_trigger > 0.2 ) {
            arm(0.3);
        }
        if (gamepad2.right_trigger > 0.2) {
            arm(-0.3);
        }

         */

        if (gamepad2.left_trigger < 0.2  && gamepad2.right_trigger < 0.2)
        {
            arm(0.0);
        }else if (gamepad2.left_trigger > 0.2) {
            arm(0.3);
        }else if (gamepad2.right_trigger > 0.2) {
            arm(-0.3);
        }else
        {

        }

        if(gamepad1.y) {
            hand(1);
        }
        if(gamepad1.x){
            hand(0);
        }
        if(gamepad2.y) {
            foundation(1);
        }
        if (gamepad2.x) {
            foundation(0);
        }
        if (gamepad2.dpad_up)  {
            allArm();
        }
        if (gamepad2.a){
            autoArm(1);
        }
        if (gamepad2.b){
            autoArm(-1);
        }
    }
    public void foundation(int position) {
        leftServo.setPosition(position);
        rightServo.setPosition(position);
    }

    public void hand(int position){
        handTopServo.setPosition(position);
        handBottomServo.setPosition(position);

    }
    public void elevatorUp(double speed) {
        elevRight.setPower(speed);
        elevLeft.setPower(speed);
    }
    public void elevatorDown(double speed) {
        elevRight.setPower(-1 * speed);
        elevLeft.setPower(-1 * speed);
    }
    public void autoArm(int position) {

        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setTargetPosition(position * turnUp(50));
        while (arm.getCurrentPosition() < arm.getTargetPosition() && arm.isBusy()) {
            arm.setPower((arm.getTargetPosition()-arm.getCurrentPosition())/(arm.getTargetPosition()));
        }
        arm.setPower(0);
    }
    public void strafeLeft () {
        frontLeft.setPower(-0.7);
        backLeft.setPower(0.7);
        frontRight.setPower(0.7);
        backRight.setPower(-0.7);

    }
    public void strafeRight () {
        frontLeft.setPower(0.7);
        backLeft.setPower(-0.7);
        frontRight.setPower(-0.7);
        backRight.setPower(0.7);

    }
    public void arm(double position) {
        arm.setPower(position);

        /*
        if (gamepad2.y) {

            while (runtime.seconds() < 1.3) {
                telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
                telemetry.update();
                arm.setPower(0.7);
            }
        }
        else if (gamepad2.x) {
            while (runtime.seconds() < 1.3) {
                telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
                telemetry.update();
                arm.setPower(-0.7);
            }
        }
        */

    }
    public int turnUp(int degree) {
        return (int) ((degree/360)*ticksPerRevolution);
    }

    public void allArm() {

        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setTargetPosition(-turnUp(0));
        while (arm.getCurrentPosition() < arm.getTargetPosition() && arm.isBusy()) {
            arm.setPower((arm.getTargetPosition()-arm.getCurrentPosition())/(arm.getTargetPosition()));
        }
        arm.setPower(0);

        handTopServo.setPosition(1);
        handBottomServo.setPosition(1);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setTargetPosition(turnUp(90));
        while (arm.getCurrentPosition() < arm.getTargetPosition() && arm.isBusy()) {
            arm.setPower((arm.getTargetPosition()-arm.getCurrentPosition())/(arm.getTargetPosition()));
        }
        arm.setPower(0);

    }

}
