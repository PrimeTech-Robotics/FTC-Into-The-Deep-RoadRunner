package org.firstinspires.ftc.teamcode.ro025.Modes;

import org.firstinspires.ftc.teamcode.ro025.Outtake.Claw;
import org.firstinspires.ftc.teamcode.ro025.Outtake.Extension;
import org.firstinspires.ftc.teamcode.ro025.Outtake.Pivot;

public class All_modes {
        static double pivotTarget = Pivot.TICKS_FOR_PARALLEL;
        static double extensionTarget = Extension.MIN_TICKS; ///schimbam cu valori reale
        static double increment = 0.0; ///schimbam cu valori reale

        public static void general(){
            Extension.getInstance().loop();
            Pivot.getInstance().loop();
        }

        public static void intake_sample_init(){
            pivotTarget = Pivot.TICKS_FOR_PARALLEL;
            extensionTarget = Extension.MIN_TICKS;

            Claw.getInstance().rotate(0.0);///schimbam cu valori reale
            Claw.getInstance().move_to_angle(0.5);///schimbam cu valori reale

            do {
                Extension.getInstance().run_to_target(extensionTarget);
            } while (Extension.getInstance().extindere_left.getCurrentPosition() != extensionTarget);

            do {
                Pivot.getInstance().run_to_target(pivotTarget);
            } while (Pivot.getInstance().motorPivot.getCurrentPosition() != pivotTarget);
        }

        public static void intake_sample_loop(){

            if(/*!(codu lu tudi)*/) {
                Claw.getInstance().perpendicular_on_the_ground();
                Extension.getInstance().run_to_target(extensionTarget);
                extensionTarget += increment;
            }
            else{
                Claw.getInstance().openingServo.setPosition(Claw.OPEN_POS);

                do {
                    Claw.getInstance().rotate(/*codu lu tudi de */);
                    Pivot.getInstance().run_to_target(pivotTarget);
                    Claw.getInstance().perpendicular_on_the_ground();
                    pivotTarget -= increment;
                } while (/*cod tudi*/);

                Claw.getInstance().openingServo.setPosition(Claw.CLOSED_POS);

                do {
                    Pivot.getInstance().run_to_target(Pivot.TICKS_FOR_PARALLEL);
                    Claw.getInstance().perpendicular_on_the_ground();
                } while (Pivot.getInstance().motorPivot.getCurrentPosition() != Pivot.TICKS_FOR_PARALLEL);

                Claw.getInstance().rotate(0.0);
                FSM_modes.getInstance().modes = FSM_modes.Modes.GENERAL;
            }


        }

        public static void outtake_sample_init(){
            Claw.getInstance().rotate(0.5);
        }

        public static void outtake_sample_loop(){
            Extension.getInstance().loop();
            Pivot.getInstance().loop();
            Claw.getInstance().parallel_to_the_ground_back();
            Claw.getInstance().open_close();
        }

        public static void intake_outtake_specimen_init(){
            Claw.getInstance().rotate(0.0);
        }

        public static void intake_outtake_specimen_loop(){
            Extension.getInstance().loop();
            Pivot.getInstance().loop();
            Claw.getInstance().parallel_to_the_ground_front();
            Claw.getInstance().open_close();
        }


}
