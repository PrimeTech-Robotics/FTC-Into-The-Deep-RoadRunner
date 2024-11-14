package org.firstinspires.ftc.teamcode.ro025.Outtake;

import org.firstinspires.ftc.teamcode.ro025.Gamepad.GamepadClass;
import org.firstinspires.ftc.teamcode.ro025.Modes.FSM_modes;

public class OutTake {
    private static OutTake instance = null;

    public static synchronized OutTake getInstance() {
        if (instance == null) {
            instance = new OutTake();
        }
        return instance;
    }

    public void init() {
        Claw.getInstance().init();
        Extension.getInstance().init();
        Pivot.getInstance().init();
    }

    public void loop() {
        FSM_modes.getInstance().FSM();
        if(GamepadClass.getInstance().circle()){
            Init_pos.getInstance().return_to_init_pos();
        }
    }
}
