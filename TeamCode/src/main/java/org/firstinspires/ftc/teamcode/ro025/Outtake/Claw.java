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

    enum FrontBackState {
        FRONT, BACK, INRANGE
    }

    FrontBackState frontBackState = FrontBackState.BACK;

    enum LeftRightState {
        RIGHT, LEFT, INRANGE
    }

    LeftRightState leftRightState = LeftRightState.INRANGE;

    // Servo positions
    // TODO: Adjust with actual positions
    public static final double OPEN_POS = 1.0;
    public static final double CLOSED_POS = 0.0;

    public static final double FRONT_POS = 1.0;
    public static final double BACK_POS = 0.0;
    public static final double ROTATION_INCREMENT_FRONT_BACK = 0.0;

    public static final double ROTATION_INCREMENT = 0.0;
    public static final double RIGHT_FINAL_STATE = 0.0;
    public static final double LEFT_FINAL_STATE = 0.0;
    public static final double MID_POS = 0.0;

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
        rotationServo.setPosition(MID_POS);

        frontBackServo_left = hardwareMap.get(Servo.class, "frontBackServo_left");
        frontBackServo_left.setDirection(Servo.Direction.REVERSE);
        frontBackServo_left.setPosition(BACK_POS);


        frontBackServo_right = hardwareMap.get(Servo.class, "frontBackServo_right");
        frontBackServo_right.setPosition(BACK_POS);
    }

    public void loop() {
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

        // Front/back movement FSM
        switch (frontBackState) {
            case FRONT:
                if (GamepadClass.getInstance().dpad_up()) {
                    // Transition to INRANGE state
                    frontBackServo_right.setPosition(frontBackServo_right.getPosition() - ROTATION_INCREMENT_FRONT_BACK);
                    frontBackServo_left.setPosition(frontBackServo_left.getPosition() + ROTATION_INCREMENT_FRONT_BACK);
                    frontBackState = FrontBackState.INRANGE;
                }
                break;
            case BACK:
                if (GamepadClass.getInstance().dpad_down()) {
                    // Transition to FRONT state
                    frontBackServo_right.setPosition(frontBackServo_right.getPosition() + ROTATION_INCREMENT_FRONT_BACK);
                    frontBackServo_left.setPosition(frontBackServo_left.getPosition() - ROTATION_INCREMENT_FRONT_BACK);
                    frontBackState = FrontBackState.INRANGE;
                }
                break;
            case INRANGE:
                if (GamepadClass.getInstance().dpad_up()) {
                    frontBackServo_right.setPosition(frontBackServo_right.getPosition() - ROTATION_INCREMENT_FRONT_BACK);
                    frontBackServo_left.setPosition(frontBackServo_left.getPosition() + ROTATION_INCREMENT_FRONT_BACK);
                    if(frontBackServo_right.getPosition() < BACK_POS) {
                        //transition to BACK_POS
                        frontBackServo_right.setPosition(BACK_POS);
                        frontBackServo_left.setPosition(BACK_POS);
                        frontBackState = FrontBackState.BACK;
                    }
                }
                if (GamepadClass.getInstance().dpad_down()) {
                    frontBackServo_right.setPosition(frontBackServo_right.getPosition() + ROTATION_INCREMENT_FRONT_BACK);
                    frontBackServo_left.setPosition(frontBackServo_left.getPosition() - ROTATION_INCREMENT_FRONT_BACK);
                    if(frontBackServo_right.getPosition() > FRONT_POS) {
                        //transition to FRONT_POS
                        frontBackServo_right.setPosition(FRONT_POS);
                        frontBackServo_left.setPosition(FRONT_POS);
                        frontBackState = FrontBackState.FRONT;
                    }
                }
                break;
        }

        // Left/right movement FSM
        switch (leftRightState) {
            case RIGHT:
                if (GamepadClass.getInstance().dpad_left()) {
                    // Transition to INRANGE state
                    rotationServo.setPosition(rotationServo.getPosition() - ROTATION_INCREMENT);
                    leftRightState = LeftRightState.INRANGE;
                }
                break;
            case LEFT:
                if (GamepadClass.getInstance().dpad_right()) {
                    // Transition to INRANGE state
                    rotationServo.setPosition(rotationServo.getPosition() + ROTATION_INCREMENT);
                    leftRightState = LeftRightState.INRANGE;
                }
                break;
            case INRANGE:
                if (GamepadClass.getInstance().dpad_left()) {
                    rotationServo.setPosition(rotationServo.getPosition() - ROTATION_INCREMENT);
                    if (rotationServo.getPosition() < LEFT_FINAL_STATE) {
                        // Transition to LEFT state
                        rotationServo.setPosition(LEFT_FINAL_STATE);
                        leftRightState = LeftRightState.LEFT;
                    }
                }
                if (GamepadClass.getInstance().dpad_right()) {
                    rotationServo.setPosition(rotationServo.getPosition() + ROTATION_INCREMENT);
                    if (rotationServo.getPosition() > RIGHT_FINAL_STATE) {
                        // Transition to RIGHT state
                        rotationServo.setPosition(RIGHT_FINAL_STATE);
                        leftRightState = LeftRightState.RIGHT;
                    }
                }
                break;
        }
    }
}
