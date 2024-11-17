package org.firstinspires.ftc.teamcode.ro025.Modes;

import org.firstinspires.ftc.teamcode.ro025.Gamepad.GamepadClass;
import org.firstinspires.ftc.teamcode.ro025.Outtake.Claw;
import org.firstinspires.ftc.teamcode.ro025.Outtake.Extension;
import org.firstinspires.ftc.teamcode.ro025.Outtake.OutTake;
import org.firstinspires.ftc.teamcode.ro025.Outtake.Pivot;

public class FSM_modes {
    private static FSM_modes instance = null;

    enum Modes{
        GENERAL, INTAKE_SAMPLE, INTAKE_OUTTAKE_SPECIMEN, OUTTAKE_SAMPLE
    }

    Modes modes = Modes.GENERAL;

    public static synchronized FSM_modes getInstance() {
        if (instance == null) {
            instance = new FSM_modes();
        }
        return instance;
    }

    public void FSM(){
        switch(modes){
            case GENERAL:
                if(GamepadClass.getInstance().dpad_right()){
                    modes = Modes.INTAKE_OUTTAKE_SPECIMEN;
                }
                if(GamepadClass.getInstance().dpad_left()){
                    modes = Modes.OUTTAKE_SAMPLE;
                }
                if(GamepadClass.getInstance().dpad_down()){
                    modes = Modes.INTAKE_SAMPLE;
                }
                Extension.getInstance().loop();
                Pivot.getInstance().loop();
                break;
            case INTAKE_SAMPLE:
                if(GamepadClass.getInstance().dpad_down()){
                    modes = Modes.GENERAL;
                }
                Claw.getInstance().rotate(0.0);
                Claw.getInstance().move_to_angle(0.5);
                do {
                    Pivot.getInstance().run_to_target(Pivot.TICKS_FOR_PARALLEL);
                } while (Pivot.getInstance().motorPivot.getCurrentPosition() != Pivot.TICKS_FOR_PARALLEL);
                //TODO: limelight implementaion & extension
                break;
            case INTAKE_OUTTAKE_SPECIMEN:
                if(GamepadClass.getInstance().dpad_right()){
                    modes = Modes.GENERAL;
                }
                Extension.getInstance().loop();
                Pivot.getInstance().loop();
                Claw.getInstance().rotate(0.0);
                Claw.getInstance().parallel_to_the_ground_front();
                Claw.getInstance().open_close();
                break;
            case OUTTAKE_SAMPLE:
                if(GamepadClass.getInstance().dpad_left()){
                    modes = Modes.GENERAL;
                }
                Extension.getInstance().loop();
                Pivot.getInstance().loop();
                Claw.getInstance().rotate(0.5);
                Claw.getInstance().parallel_to_the_ground_back();
                Claw.getInstance().open_close();
                break;
        }
    }
}
