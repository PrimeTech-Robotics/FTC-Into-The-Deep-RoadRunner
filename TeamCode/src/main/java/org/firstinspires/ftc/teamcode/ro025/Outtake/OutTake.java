package org.firstinspires.ftc.teamcode.ro025.Outtake;

import org.firstinspires.ftc.teamcode.ro025.Gamepad.GamepadClass;
import org.firstinspires.ftc.teamcode.ro025.Modes.FSM_modes;

public class OutTake {
    private static OutTake instance = null;
    ReturnToInitPos returnToInitPos = ReturnToInitPos.OFF;

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
        switch (returnToInitPos) {
            case OFF:
                if (GamepadClass.getInstance().circle()) {
                    returnToInitPos = ReturnToInitPos.ON;
                }
                FSM_modes.getInstance().FSM();
                break;
            case ON:
                Init_pos.getInstance().return_to_init_pos();
                break;

        }

    }

    enum ReturnToInitPos {
        ON, OFF
    }
}
