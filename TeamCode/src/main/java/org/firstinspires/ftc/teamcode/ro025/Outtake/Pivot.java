package org.firstinspires.ftc.teamcode.ro025.Outtake;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.ro025.Gamepad.GamepadClass;

public class Pivot {
    private PIDController controller;

    public static double p = 0, i = 0, d = 0;
    /// schimbam cu valori reale
    public static double f = 0;
    /// schimbam cu valori reale
    public static double target = 0;
    // TODO: Adjust with actual values
    public static final double MAX_TICKS = 0;
    /// schimbam cu valori reale
    public static final double MIN_TICKS = 0;
    public static final double TICKS_FOR_PARALLEL = 0;
    /// schimbam cu valori reale
    public final double increment = 0;

    /// schimbam cu valori reale

    enum LiftState {
        MAX, INRANGE, MIN
    }

    LiftState liftState = LiftState.MIN;

    public DcMotorEx motorPivot = null;

    private static Pivot instance = null;

    public static synchronized Pivot getInstance() {
        if (instance == null) {
            instance = new Pivot();
        }
        return instance;
    }

    public void init() {
        controller = new PIDController(p, i, d);

        motorPivot = hardwareMap.get(DcMotorEx.class, "motorPivot");
        motorPivot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorPivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        //telemetry =new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    //PID stuff
    public void loop() {
        double local_target = fsm();
        run_to_target(local_target);
    }

    public double fsm() {
        switch (liftState) {
            case MIN:
                if (GamepadClass.getInstance().right_bumper()) {
                    target += increment;
                    liftState = LiftState.INRANGE;
                }
                break;
            case MAX:
                if (GamepadClass.getInstance().left_bumper()) {
                    target -= increment;
                    liftState = LiftState.INRANGE;
                }
                break;
            case INRANGE:
                if (GamepadClass.getInstance().right_bumper()) {
                    target += increment;
                }
                if (GamepadClass.getInstance().left_bumper()) {
                    target -= increment;
                }
                if (target > MAX_TICKS) {
                    liftState = LiftState.MAX;
                    target = MAX_TICKS;
                }
                if (target < MIN_TICKS) {
                    liftState = LiftState.MIN;
                    target = MIN_TICKS;
                }
                break;
        }
        return target;
    }

    public void run_to_target(double target) {
        controller.setPID(p, i, d);
        int pivot_pos = motorPivot.getCurrentPosition();
        double pid = controller.calculate(pivot_pos, target);
        double power = pid + f;

        motorPivot.setPower(power);

        // Telemetry
        // telemetry.addData("pos ", pivot_pos);
        // telemetry.addData("target ", target);
        // telemetry.update();
    }

}
