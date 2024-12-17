package org.firstinspires.ftc.teamcode.ro025.Components.Outtake;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.ro025.Components.Gamepad.GamepadClass;

public class Extension {

    // TODO: Adjust with actual values
    public static final double MAX_TICKS = 0.0;
    /// schimbam cu valori reale
    public static final double MIN_TICKS = 0.0;
    public static double p = 0, i = 0, d = 0;
    /// schimbam cu valori reale
    public static double f = 0;
    /// schimbam cu valori reale
    public static double target = 0;
    private static Extension instance = null;
    public final double increment = 0;
    public DcMotorEx extindere_left = null;
    public DcMotorEx extindere_right = null;
    LiftState liftState = LiftState.MIN;
    private PIDController controller;

    public static synchronized Extension getInstance() {
        if (instance == null) {
            instance = new Extension();
        }
        return instance;
    }

    public void init() {
        controller = new PIDController(p, i, d);

        extindere_left = hardwareMap.get(DcMotorEx.class, "extindere_left");
        extindere_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        extindere_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extindere_left.setDirection(DcMotorSimple.Direction.REVERSE);

        extindere_right = hardwareMap.get(DcMotorEx.class, "extindere_right");
        extindere_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        extindere_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    //PID stuff
    public void loop() {
        double local_target = fsm();
        run_to_target(local_target);
    }

    public double fsm() {
        double last_target = target;

        switch (liftState) {
            case MIN:
                target += increment * GamepadClass.getInstance().right_trigger();
                if (last_target != target) {
                    liftState = LiftState.INRANGE;
                }
                break;
            case MAX:
                target -= increment * GamepadClass.getInstance().left_trigger();
                if (last_target != target) {
                    liftState = LiftState.INRANGE;
                }
                break;
            case INRANGE:
                target += increment * GamepadClass.getInstance().right_trigger() - increment * GamepadClass.getInstance().left_trigger();
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
        int lift_pos = extindere_left.getCurrentPosition();
        double pid = controller.calculate(lift_pos, target);
        double power = pid + f;

        extindere_right.setPower(power);
        extindere_left.setPower(power);

        // Telemetry
        // telemetry.addData("pos ", lift_pos);
        // telemetry.addData("target ", target);
        // telemetry.update();
    }

    /// schimbam cu valori reale

    enum LiftState {
        MAX, INRANGE, MIN
    }
}
