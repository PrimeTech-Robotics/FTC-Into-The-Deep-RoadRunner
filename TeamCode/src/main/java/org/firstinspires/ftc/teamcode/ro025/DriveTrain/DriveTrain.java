package org.firstinspires.ftc.teamcode.ro025.DriveTrain;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;

public class DriveTrain {
    DcMotor leftBack = null;
    DcMotor leftFront = null;
    DcMotor rightBack = null;
    DcMotor rightFront = null;
    private static DriveTrain instance=null;
    public static synchronized DriveTrain getInstance(){ //creezi o instanta
        if(instance== null){
            instance = new DriveTrain();
        }
        return instance;
    }
    public void init(){// atribui motoarele de drive
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        leftBack.setZeroPowerBehavior(BRAKE);


        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftFront.setZeroPowerBehavior(BRAKE);


        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        rightBack.setZeroPowerBehavior(BRAKE);


        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightFront.setZeroPowerBehavior(BRAKE);
    }
    public void loop(){//miscare driver train
        double y = -gamepad1.left_stick_y; // Y stick inversat
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        leftFront.setPower(y + x + rx);
        leftBack.setPower(y - x + rx);
        rightFront.setPower(y - x - rx);
        rightBack.setPower(y + x - rx);
    }
}
