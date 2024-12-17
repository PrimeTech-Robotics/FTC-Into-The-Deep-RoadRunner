package org.firstinspires.ftc.teamcode.ro025.Components.Drivetrain;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Drivetrain {
    private static Drivetrain instance = null;
    DcMotor leftBack = null;
    DcMotor leftFront = null;
    DcMotor rightBack = null;
    DcMotor rightFront = null;

    public static synchronized Drivetrain getInstance() {
        if (instance == null) {
            instance = new Drivetrain();
        }
        return instance;
    }

    public void init() {
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        leftBack.setZeroPowerBehavior(BRAKE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);


        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftFront.setZeroPowerBehavior(BRAKE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);


        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        rightBack.setZeroPowerBehavior(BRAKE);


        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightFront.setZeroPowerBehavior(BRAKE);
    }

    public void loop() {
        // Drivetrain gamepad control
        double y = smoothControl(-gamepad1.left_stick_y); // inverted Y stick
        double x = smoothControl(gamepad1.left_stick_x);
        double rx = smoothControl(gamepad1.right_stick_x);

        leftFront.setPower(y + x + rx);
        leftBack.setPower(y - x + rx);
        rightFront.setPower(y - x - rx);
        rightBack.setPower(y + x - rx);
    }

    private double smoothControl(double val) {
        return 0.5 * Math.tan(1.12 * val);
    }
}
// vezi ca s-ar putea sa trebuiasca sa fie de tip float motoarele, nu brake, din cauza la smooth control. deci daca se comporta ciudat prima chestie pe care o schimbati ii asta (de la sover)
