package org.firstinspires.ftc.teamcode.ro025.Modes;

import org.firstinspires.ftc.teamcode.ro025.Outtake.Claw;
import org.firstinspires.ftc.teamcode.ro025.Outtake.Extension;
import org.firstinspires.ftc.teamcode.ro025.Outtake.Pivot;

public class All_modes {
        private static All_modes instance = null;

        public static synchronized All_modes getInstance() {
            if (instance == null) {
                instance = new All_modes();
            }
            return instance;
        }

        public static void general(){
            Extension.getInstance().loop();
            Pivot.getInstance().loop();
        }
        public static void intake_sample(){

            if(/*!(codu lu tudi)*/) {
                //extindere
            }

            else{
                Claw.getInstance().openingServo.setPosition(Claw.OPEN_POS);

                do {
                    Pivot.getInstance().run_to_target(Pivot.MIN_TICKS);
                    Claw.getInstance().perpendicular_on_the_ground();
                    ///cod tudi rotire
                } while (/*cod tudi*/);

                Claw.getInstance().openingServo.setPosition(Claw.CLOSED_POS);

                do {
                    Pivot.getInstance().run_to_target(Pivot.TICKS_FOR_PARALLEL);
                    Claw.getInstance().perpendicular_on_the_ground();
                } while (Pivot.getInstance().motorPivot.getCurrentPosition() != Pivot.TICKS_FOR_PARALLEL);

                FSM_modes.getInstance().modes = FSM_modes.Modes.GENERAL;
            }


        }
        public static void outtake_sample(){
            Extension.getInstance().loop();
            Pivot.getInstance().loop();
            Claw.getInstance().parallel_to_the_ground_back();
            Claw.getInstance().open_close();
        }
        public static void intake_outtake_specimen(){
            Extension.getInstance().loop();
            Pivot.getInstance().loop();
            Claw.getInstance().parallel_to_the_ground_front();
            Claw.getInstance().open_close();
        }


}
