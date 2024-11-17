package org.firstinspires.ftc.teamcode.ro025.Outtake;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;


import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ro025.Gamepad.GamepadClass;

public class Claw {
    private static Claw instance = null;
    Servo openingServo = null;
    Servo rotationServo = null;
    Servo frontBackServo_left = null;
    Servo frontBackServo_right = null;

    enum OpenState {
        OPEN, CLOSED
    }

    OpenState openState = OpenState.CLOSED;

    // Servo positions
    // TODO: Adjust with actual positions
    public static final double OPEN_POS = 1.0;
    public static final double CLOSED_POS = 0.0;


    public static final double FRONT_BACK_INIT = 0.0;
    public static final double OMEGA = 0.0;

    public static final double ROTATION_INIT = 0.0;

    public static synchronized Claw getInstance() {
        if (instance == null) {
            instance = new Claw();
        }
        return instance;
    }

    public void init() {
        openingServo = hardwareMap.get(Servo.class, "openingServo");
        openingServo.setPosition(CLOSED_POS);

        rotationServo = hardwareMap.get(Servo.class, "rotationServo");
        rotationServo.setPosition(ROTATION_INIT);

        frontBackServo_left = hardwareMap.get(Servo.class, "frontBackServo_left");
        frontBackServo_left.setDirection(Servo.Direction.REVERSE);
        frontBackServo_left.setPosition(FRONT_BACK_INIT);


        frontBackServo_right = hardwareMap.get(Servo.class, "frontBackServo_right");
        frontBackServo_right.setPosition(FRONT_BACK_INIT);
    }

    public void parallel_to_the_ground_front(){
        double alpha = Pivot.getInstance().motorPivot.getCurrentPosition()/2.088/180 - (0.5 - OMEGA/180);

        frontBackServo_left.setPosition(alpha);
        frontBackServo_right.setPosition(alpha);
    }

    public void perpendicular_on_the_ground(){
        double alpha = Pivot.getInstance().motorPivot.getCurrentPosition()/2.088/180 + OMEGA/180;

        frontBackServo_left.setPosition(alpha);
        frontBackServo_right.setPosition(alpha);
    }

    public void parallel_to_the_ground_back() {
        double alpha = -(1 -(Pivot.getInstance().motorPivot.getCurrentPosition()/2.088/180 - (0.5 - OMEGA/180)));
        if(alpha < -0.5){
            alpha = -0.5;
        }
        frontBackServo_left.setPosition(alpha);
        frontBackServo_right.setPosition(alpha);
    }

    public void move_to_angle(double angle){
        frontBackServo_left.setPosition(angle);
        frontBackServo_right.setPosition(angle);
    }
    public void open_close() {
        // Opening/closing FSM
        switch (openState) {
            case OPEN:
                if (GamepadClass.getInstance().cross()) {
                    // Transition to CLOSED state
                    openingServo.setPosition(CLOSED_POS);
                    openState = OpenState.CLOSED;
                }
                break;
            case CLOSED:
                if (GamepadClass.getInstance().cross()) {
                    // Transition to OPEN state
                    openingServo.setPosition(OPEN_POS);
                    openState = OpenState.OPEN;
                }
                break;
        }
    }
    public void rotate(double angle){
        rotationServo.setPosition(angle);
    }
}

