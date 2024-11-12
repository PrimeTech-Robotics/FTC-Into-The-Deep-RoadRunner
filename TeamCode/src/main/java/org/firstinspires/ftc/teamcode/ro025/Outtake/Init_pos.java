package org.firstinspires.ftc.teamcode.ro025.Outtake;

public class Init_pos {
    private static Init_pos instance = null;

    public static synchronized Init_pos getInstance() {
        if (instance == null) {
            instance = new Init_pos();
        }
        return instance;
    }

    public void return_to_init_pos(){
        //get claw to initial position
        Claw.getInstance().openingServo.setPosition(Claw.CLOSED_POS);
        Claw.getInstance().rotationServo.setPosition(Claw.MID_POS);
        Claw.getInstance().frontBackServo_left.setPosition(Claw.BACK_POS);
        Claw.getInstance().frontBackServo_right.setPosition(Claw.BACK_POS);

        //get extension to initial position
        do {
            Extension.getInstance().run_to_target(Extension.MIN_TICKS);
        } while (Extension.getInstance().extindere_left.getPowerFloat());

        //get pivot to initial position
        do {
            Pivot.getInstance().run_to_target(Pivot.MIN_TICKS);
        } while (!Pivot.getInstance().motorPivot.getPowerFloat());
    }
}
