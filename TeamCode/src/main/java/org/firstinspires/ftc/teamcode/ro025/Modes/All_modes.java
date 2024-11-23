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
            //TODO: limelight implementaion & extension
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
