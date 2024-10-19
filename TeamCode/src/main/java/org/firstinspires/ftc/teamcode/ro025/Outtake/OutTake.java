package org.firstinspires.ftc.teamcode.ro025.Outtake;

import org.firstinspires.ftc.teamcode.ro025.Gamepad.GamepadClass;

public class OutTake {
    private static OutTake instance=null;
    public static synchronized OutTake getInstance(){ //creezi o instanta
        if(instance == null){
            instance = new OutTake();
        }
        return instance;
    }
    public void init(){
        Claw.getInstance().init();
        //apelare Extindere init ( ne trebuie PID )
        //apelare Pivot init ( ne trebuie PID )
    }
    public void loop(){
        Claw.getInstance().loop();
        //apelare Extindere loop ( ne trebuie PID )
        //apelare Pivot loop ( ne trebuie PID )
    }
}
