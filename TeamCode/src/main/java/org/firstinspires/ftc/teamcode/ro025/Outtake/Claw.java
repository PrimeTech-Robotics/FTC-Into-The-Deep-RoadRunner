package org.firstinspires.ftc.teamcode.ro025.Outtake;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.ro025.Gamepad.GamepadClass;

public class Claw {
    private static Claw instance = null;
    Servo openingServo = null;
    Servo rotationServo = null;
    Servo frontBackServo = null;

    public static synchronized Claw getInstance() { //creezi o instanta
        if (instance == null) {
            instance = new Claw();
        }
        return instance;
    }

    public void init() {
        openingServo = hardwareMap.get(Servo.class, "openingServo");

        rotationServo = hardwareMap.get(Servo.class, "rotationServo");

        frontBackServo = hardwareMap.get(Servo.class, "frontBackServo");
    }

    public void loop() {
        if (GamepadClass.getInstance().triangle()) {
            //FSM intre inchis si deschis
        }
        if (GamepadClass.getInstance().square()) {
            ///FSM intre fata spate
        }

        //TODO trebuie rotatie (ne intelegem cum o facem)
    }
}
