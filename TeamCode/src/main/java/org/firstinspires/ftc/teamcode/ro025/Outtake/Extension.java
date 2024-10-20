package org.firstinspires.ftc.teamcode.ro025.Outtake;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Extension {
    private static Extension instance = null;
    DcMotorEx extindere_left = null;
    DcMotorEx extindere_right = null;

    public static synchronized Extension getInstance() { //creezi o instanta
        if (instance == null) {
            instance = new Extension();
        }
        return instance;
    }

    public void init() {
        extindere_left = hardwareMap.get(DcMotorEx.class, "extindere_left");
        extindere_right.setDirection(DcMotorSimple.Direction.REVERSE);

        extindere_right = hardwareMap.get(DcMotorEx.class, "extindere_right");
    }
    //PID stuff
}
