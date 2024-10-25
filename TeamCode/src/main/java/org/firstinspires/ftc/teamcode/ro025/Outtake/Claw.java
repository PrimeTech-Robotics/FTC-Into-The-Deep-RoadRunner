package org.firstinspires.ftc.teamcode.ro025.Outtake;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ro025.Gamepad.GamepadClass;

public class Claw {
    private static Claw instance = null;
    Servo openingServo = null;
    Servo rotationServo = null;
    Servo frontBackServo_left = null; // ne trebuie 2 servouri de back front ca o sa avem 2 slidere
    Servo frontBackServo_right = null;

    private final ElapsedTime actionTimer = new ElapsedTime();

    enum OpenState {
        OPEN, CLOSED
    }

    OpenState openState = OpenState.OPEN;

    enum FrontBackState {
        FRONT, BACK
    }

    FrontBackState frontBackState = FrontBackState.FRONT;

    enum LeftRightState {
        RIGHT, LEFT, INRANGE
    }

    LeftRightState leftRightState = LeftRightState.INRANGE;

    //Servo positions
    //TODO: adjust with actual values
    final double OPEN_POS = 1.0;
    final double CLOSED_POS = 0.0;

    final double FRONT_POS = 1.0;
    final double BACK_POS = 0.0;

    final double ROTATION_INCREMENT = 0.0;
    final double RIGHT_FINAL_STATE = 0.0;
    final double LEFT_FINAL_STATE = 0.0;

    public static synchronized Claw getInstance() {
        if (instance == null) {
            instance = new Claw();
        }
        return instance;
    }

    public void init() {
        openingServo = hardwareMap.get(Servo.class, "openingServo");
        // TODO: setare pozitii initiale
        rotationServo = hardwareMap.get(Servo.class, "rotationServo");
        frontBackServo_left = hardwareMap.get(Servo.class, "frontBackServo_left");
        frontBackServo_right = hardwareMap.get(Servo.class, "frontBackServo_right");

        actionTimer.reset();
    }

    public void loop() {
        // Opening/closing FSM
        switch (openState) {
            case OPEN:
                if (GamepadClass.getInstance().triangle()) {
                    // Transition to CLOSED state
                    openingServo.setPosition(CLOSED_POS);
                    openState = OpenState.CLOSED;
                }
                break;
            case CLOSED:
                if (GamepadClass.getInstance().triangle()) {
                    // Transition to OPEN state
                    openingServo.setPosition(OPEN_POS);
                    openState = OpenState.OPEN;
                }
                break;
        }

        // Front/back movement FSM
        switch (frontBackState) {
            case FRONT:
                if (GamepadClass.getInstance().square()) {
                    // Transition to BACK state
                    frontBackServo_right.setPosition(BACK_POS);
                    frontBackServo_left.setPosition(FRONT_POS);// s-ar putea sa le schimbam
                    frontBackState = FrontBackState.BACK;
                }
                break;
            case BACK:
                if (GamepadClass.getInstance().square()) {
                    // Transition to FRONT state
                    frontBackServo_right.setPosition(FRONT_POS);
                    frontBackServo_left.setPosition(BACK_POS); // s-ar putea sa le schimbam
                    frontBackState = FrontBackState.FRONT;
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
