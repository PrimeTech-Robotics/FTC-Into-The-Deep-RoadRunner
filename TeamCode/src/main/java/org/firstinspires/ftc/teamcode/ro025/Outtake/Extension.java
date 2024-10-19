package org.firstinspires.ftc.teamcode.ro025.Outtake;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Extension {
    private static Extension instance = null;
    DcMotorEx m1 = null;
    DcMotorEx m2 = null;

    public static synchronized Extension getInstance() { //creezi o instanta
        if (instance == null) {
            instance = new Extension();
        }
        return instance;
    }

    public void init() {
        m1 = hardwareMap.get(DcMotorEx.class, "m1_left");
        m1.setDirection(DcMotorSimple.Direction.REVERSE);
        m2 = hardwareMap.get(DcMotorEx.class, "m2_right");
    }
}
