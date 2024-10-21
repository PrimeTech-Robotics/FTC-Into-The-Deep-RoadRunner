package org.firstinspires.ftc.teamcode.ro025.Outtake;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ro025.Gamepad.GamepadClass;

public class Claw {
    private static Claw instance = null;
    Servo openingServo = null;
    Servo rotationServo = null;
    Servo frontBackServo = null;

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
        rotationServo = hardwareMap.get(Servo.class, "rotationServo");
        frontBackServo = hardwareMap.get(Servo.class, "frontBackServo");

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
                    frontBackServo.setPosition(BACK_POS);
                    frontBackState = FrontBackState.BACK;
                }
                break;
            case BACK:
                if (GamepadClass.getInstance().square()) {
                    // Transition to FRONT state
                    frontBackServo.setPosition(FRONT_POS);
                    frontBackState = FrontBackState.FRONT;
                }
                break;
        }

        // Left/right movement FSM
        switch (leftRightState) {
            case RIGHT:
                if (GamepadClass.getInstance().left_bumper()){
                    // Transition to INRANGE state
                    rotationServo.setPosition(rotationServo.getPosition() - ROTATION_INCREMENT);
                    leftRightState = LeftRightState.INRANGE;
                }
                break;
            case LEFT:
                if (GamepadClass.getInstance().right_bumper()){
                    // Transition to INRANGE state
                    rotationServo.setPosition(rotationServo.getPosition() + ROTATION_INCREMENT);
                    leftRightState = LeftRightState.INRANGE;
                }
                break;
            case INRANGE:
                if (GamepadClass.getInstance().left_bumper()){
                    rotationServo.setPosition(rotationServo.getPosition() - ROTATION_INCREMENT);
                    if (rotationServo.getPosition() <= LEFT_FINAL_STATE) {
                        // Transition to LEFT state
                        rotationServo.setPosition(LEFT_FINAL_STATE);
                        leftRightState = LeftRightState.LEFT;
                    }
                }
                if (GamepadClass.getInstance().right_bumper()){
                    rotationServo.setPosition(rotationServo.getPosition() + ROTATION_INCREMENT);
                    if (rotationServo.getPosition() >= RIGHT_FINAL_STATE){
                        // Transition to RIGHT state
                        rotationServo.setPosition(RIGHT_FINAL_STATE);
                        leftRightState = LeftRightState.RIGHT;
                    }
                }
                break;
        }

    }
}
