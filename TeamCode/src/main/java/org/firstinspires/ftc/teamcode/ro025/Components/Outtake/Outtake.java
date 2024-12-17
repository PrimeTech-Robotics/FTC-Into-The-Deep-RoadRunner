package org.firstinspires.ftc.teamcode.ro025.Components.Outtake;

import org.firstinspires.ftc.teamcode.ro025.Components.Gamepad.GamepadClass;
import org.firstinspires.ftc.teamcode.ro025.Components.Modes.FSM_modes;

public class Outtake {
    private static Outtake instance = null;
    ReturnToInitPos returnToInitPos = ReturnToInitPos.OFF;

    public static synchronized Outtake getInstance() {
        if (instance == null) {
            instance = new Outtake();
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
                InitPos.getInstance().return_to_init_pos();
                break;

        }

    }

    enum ReturnToInitPos {
        ON, OFF
    }
}
