package org.firstinspires.ftc.teamcode.ro025.Hang;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.ro025.Gamepad.GamepadClass;
import org.firstinspires.ftc.teamcode.ro025.Outtake.Claw;

public class Hang {
    private static Hang instance = null;

    Servo leftHangServo = null;
    Servo rightHangServo = null;

    public static final double OPEN_POS = 1.0;
    public static final double CLOSED_POS = 0.0;

    enum ServoState{
        OPEN, CLOSED
    }

    ServoState servostate = ServoState.CLOSED;

    public static synchronized Hang getInstance() {
        if (instance == null) {
            instance = new Hang();
        }
        return instance;
    }

    public void init() {
        leftHangServo = hardwareMap.get(Servo.class, "leftHangServo");
        leftHangServo.setPosition(-CLOSED_POS);

        rightHangServo = hardwareMap.get(Servo.class, "rightHangServo");
        rightHangServo.setPosition(CLOSED_POS);
    }

    public void loop() {
        switch(servostate){
            case CLOSED:
                if(GamepadClass.getInstance().triangle()){
                    leftHangServo.setPosition(OPEN_POS);
                    rightHangServo.setPosition(OPEN_POS);

                    servostate = ServoState.OPEN;
                }
                break;
            case OPEN:
                if(GamepadClass.getInstance().triangle()){
                    leftHangServo.setPosition(CLOSED_POS);
                    rightHangServo.setPosition(CLOSED_POS);

                    servostate = ServoState.CLOSED;

                    Safety.getInstance().square = Safety.Square.UNPRESSED;
                }
                break;
        }
    }
}
