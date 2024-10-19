package org.firstinspires.ftc.teamcode.ro025.Outtake;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.ro025.DriveTrain.DriveTrain;
import org.firstinspires.ftc.teamcode.ro025.Gamepad.GamepadClass;

public class Claw {
    Servo servoDeschidere = null;
    Servo servoRotatie = null;
    Servo servoFataSpate = null;
    private static Claw instance=null;
    public static synchronized Claw getInstance(){ //creezi o instanta
        if(instance == null){
            instance = new Claw();
        }
        return instance;
    }
    public void init(){
         servoDeschidere = hardwareMap.get(Servo.class, "servoDeschidere");

         servoRotatie = hardwareMap.get(Servo.class, "servoRotatie");

         servoFataSpate = hardwareMap.get(Servo.class, "servoFataSpate");
    }
    public void loop(){
        if(GamepadClass.getInstance().triangle()){
            //FSM intre inchis si deschis
        }
        if(GamepadClass.getInstance().square()){
            ///FSM intre fata spate
        }
        ///Mai trebuie rotatie ( ne intelegem cum o facem )
    }
}
