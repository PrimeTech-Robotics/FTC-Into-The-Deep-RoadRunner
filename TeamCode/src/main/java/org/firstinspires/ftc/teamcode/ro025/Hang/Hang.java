package org.firstinspires.ftc.teamcode.ro025.Hang;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.ro025.Gamepad.GamepadClass;
import org.firstinspires.ftc.teamcode.ro025.Outtake.Claw;

public class Hang {
    private static Hang instance = null;

    Servo leftServo = null;
    Servo rightServo = null;

    public static final double OPEN_POS = 1.0;
    public static final double CLOSED_POS = 0.0;

    public static synchronized Hang getInstance() {
        if (instance == null) {
            instance = new Hang();
        }
        return instance;
    }

    public void init() {
        leftServo = hardwareMap.get(Servo.class, "leftServo");
        leftServo.setPosition(-CLOSED_POS);

        rightServo = hardwareMap.get(Servo.class, "rightServo");
        rightServo.setPosition(CLOSED_POS);
    }

    public void loop() {
        if(GamepadClass.getInstance().triangle()){
            leftServo.setPosition(OPEN_POS);
            rightServo.setPosition(OPEN_POS);
        }
    }
}
