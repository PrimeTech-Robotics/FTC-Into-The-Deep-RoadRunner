package org.firstinspires.ftc.teamcode.ro025.Outtake;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Pivot {
    DcMotorEx motorPivot = null;

    private static Pivot instance = null;

    public static synchronized Pivot getInstance() { //creezi o instanta
        if (instance == null) {
            instance = new Pivot();
        }
        return instance;
    }

    public void init() {
        motorPivot = hardwareMap.get(DcMotorEx.class , "motorPivot");

    }
    //PID stuff
}
