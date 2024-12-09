package org.firstinspires.ftc.teamcode.ro025.Outtake;

public class Init_pos {
    private static Init_pos instance = null;

    public static synchronized Init_pos getInstance() {
        if (instance == null) {
            instance = new Init_pos();
        }
        return instance;
    }

    enum WhatToRetract {
        CLAW, EXTENSION, PIVOT, OVER
    }

    WhatToRetract whatToRetract = WhatToRetract.CLAW;

    public void return_to_init_pos() {
        switch (whatToRetract) {
            case CLAW:
                Claw.getInstance().openingServo.setPosition(Claw.CLOSED_POS);
                Claw.getInstance().rotationServo.setPosition(Claw.ROTATION_INIT);
                Claw.getInstance().frontBackServo_left.setPosition(Claw.FRONT_BACK_INIT);
                Claw.getInstance().frontBackServo_right.setPosition(Claw.FRONT_BACK_INIT);
                whatToRetract = WhatToRetract.EXTENSION;
                break;
            case EXTENSION:
                if (Extension.getInstance().extindere_left.getCurrentPosition() != Extension.MIN_TICKS) {
                    Extension.getInstance().run_to_target(Extension.MIN_TICKS);
                } else {
                    whatToRetract = WhatToRetract.PIVOT;
                }
                break;
            case PIVOT:
                if (Pivot.getInstance().motorPivot.getCurrentPosition() != Pivot.MIN_TICKS) {
                    Pivot.getInstance().run_to_target(Pivot.MIN_TICKS);
                } else {
                    whatToRetract = WhatToRetract.OVER;
                }
                break;
            case OVER:
                OutTake.getInstance().returnToInitPos = OutTake.ReturnToInitPos.OFF;
                whatToRetract = WhatToRetract.CLAW;
                break;
        }
    }
}
