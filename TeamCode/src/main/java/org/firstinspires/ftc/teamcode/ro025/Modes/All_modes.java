package org.firstinspires.ftc.teamcode.ro025.Modes;

import org.firstinspires.ftc.teamcode.ro025.Outtake.Claw;
import org.firstinspires.ftc.teamcode.ro025.Outtake.Extension;
import org.firstinspires.ftc.teamcode.ro025.Outtake.Pivot;

public class All_modes {
    static double pivotTarget = Pivot.TICKS_FOR_PARALLEL;
    static double extensionTarget = Extension.MIN_TICKS;
    /// schimbam cu valori reale
    static double increment = 0.0;

    /// schimbam cu valori reale

    enum WhatToRetract_intake_sample_init {
        CLAW, EXTENSION, PIVOT, OVER
    }

    static WhatToRetract_intake_sample_init whatToRetract_intake_sample_init = WhatToRetract_intake_sample_init.CLAW;

    enum FoundAPiece {
        YES, NO
    }

    static FoundAPiece foundAPiece = FoundAPiece.NO;

    enum WhatToDo_intake_sample_loop {
        OPEN_CLAW, GET_PIVOT_DOWN, CLOSE_CLAW, GET_PIVOT_UP, RETRACT, OVER
    }

    static WhatToDo_intake_sample_loop whatToDo_intake_sample_loop = WhatToDo_intake_sample_loop.OPEN_CLAW;

    public static void general() {
        Extension.getInstance().loop();
        Pivot.getInstance().loop();
    }

    public static void intake_sample_init() {
        pivotTarget = Pivot.TICKS_FOR_PARALLEL;
        extensionTarget = Extension.MIN_TICKS;

        switch (whatToRetract_intake_sample_init) {
            case CLAW:
                Claw.getInstance().rotate(0.0);
                Claw.getInstance().move_to_angle(0.5);
                whatToRetract_intake_sample_init = WhatToRetract_intake_sample_init.EXTENSION;
                break;
            case EXTENSION:
                if (Extension.getInstance().extindere_left.getCurrentPosition() != extensionTarget) {
                    Extension.getInstance().run_to_target(extensionTarget);
                } else {
                    whatToRetract_intake_sample_init = WhatToRetract_intake_sample_init.PIVOT;
                }
                break;
            case PIVOT:
                if (Pivot.getInstance().motorPivot.getCurrentPosition() != pivotTarget) {
                    Pivot.getInstance().run_to_target(pivotTarget);
                } else {
                    whatToRetract_intake_sample_init = WhatToRetract_intake_sample_init.OVER;
                }
                break;
            case OVER:
                FSM_modes.getInstance().init = FSM_modes.Init.OVER;
                break;
        }

        foundAPiece = FoundAPiece.NO;
    }

    public static void intake_sample_loop() {
        switch (foundAPiece) {
            case NO:
                Claw.getInstance().perpendicular_on_the_ground();
                Extension.getInstance().run_to_target(extensionTarget);
                extensionTarget += increment;

                if (/**codu lu tudi de gasit piesa*/) {
                    foundAPiece = FoundAPiece.YES;
                    whatToDo_intake_sample_loop = WhatToDo_intake_sample_loop.OPEN_CLAW;
                }
                break;
            case YES:
                switch (whatToDo_intake_sample_loop) {
                    case OPEN_CLAW:
                        Claw.getInstance().openingServo.setPosition(Claw.OPEN_POS);
                        whatToDo_intake_sample_loop = WhatToDo_intake_sample_loop.GET_PIVOT_DOWN;
                        break;
                    case GET_PIVOT_DOWN:
                        Claw.getInstance().rotate(/**codu lu tudi de rotatire*/);
                        Pivot.getInstance().run_to_target(pivotTarget);
                        Claw.getInstance().perpendicular_on_the_ground();
                        pivotTarget -= increment;

                        if (/**cod tudi de distanta*/) {
                            whatToDo_intake_sample_loop = WhatToDo_intake_sample_loop.CLOSE_CLAW;
                        }
                        break;
                    case CLOSE_CLAW:
                        Claw.getInstance().openingServo.setPosition(Claw.CLOSED_POS);
                        whatToDo_intake_sample_loop = WhatToDo_intake_sample_loop.GET_PIVOT_UP;
                        break;
                    case GET_PIVOT_UP:
                        if (Pivot.getInstance().motorPivot.getCurrentPosition() != Pivot.TICKS_FOR_PARALLEL) {
                            Pivot.getInstance().run_to_target(Pivot.TICKS_FOR_PARALLEL);
                            Claw.getInstance().perpendicular_on_the_ground();
                        } else {
                            Claw.getInstance().rotate(0.0);
                            whatToDo_intake_sample_loop = WhatToDo_intake_sample_loop.RETRACT;
                        }
                        break;
                    case RETRACT:
                        if (Extension.getInstance().extindere_left.getCurrentPosition() != Extension.MIN_TICKS) {
                            Extension.getInstance().run_to_target(Extension.MIN_TICKS);
                        } else {
                            whatToDo_intake_sample_loop = WhatToDo_intake_sample_loop.OVER;
                        }
                        break;
                    case OVER:
                        FSM_modes.getInstance().modes = FSM_modes.Modes.GENERAL;
                        break;
                }
                break;
        }
    }

    public static void outtake_sample_init() {
        Claw.getInstance().rotate(0.5);
    }

    public static void outtake_sample_loop() {
        Extension.getInstance().loop();
        Pivot.getInstance().loop();
        Claw.getInstance().parallel_to_the_ground_back();
        Claw.getInstance().open_close();
    }

    public static void intake_outtake_specimen_init() {
        Claw.getInstance().rotate(0.0);
    }

    public static void intake_outtake_specimen_loop() {
        Extension.getInstance().loop();
        Pivot.getInstance().loop();
        Claw.getInstance().parallel_to_the_ground_front();
        Claw.getInstance().open_close();
    }


}
