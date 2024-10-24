package org.firstinspires.ftc.teamcode.ro025.Outtake;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.ro025.Gamepad.GamepadClass;

public class Pivot {
    private PIDController controller;

    public static double p =0 , i = 0 ,d = 0;
    public static double f =0;
    public static double target = 0;
    public final double MAX_TICKS = 0; //setam mai incolo
    public final double MIN_TICKS = 0; //setam mai incolo
    public final double increment = 0; //setam mai incolo
    enum LiftState{
        MAX, INRANGE,  MIN
    }

    LiftState liftState = LiftState.MIN;

    DcMotorEx motorPivot = null;

    private static Pivot instance = null;

    public static synchronized Pivot getInstance() { //creezi o instanta
        if (instance == null) {
            instance = new Pivot();
        }
        return instance;
    }

    public void init() {
        controller = new PIDController(p, i, d);

        motorPivot = hardwareMap.get(DcMotorEx.class, "motorPivot");
        motorPivot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //telemetry =new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }
    //PID stuff
    public void loop() {
        switch(liftState){
            case MIN:
                if(GamepadClass.getInstance().dpad_up()) {
                    target = target + increment;
                    liftState = LiftState.INRANGE;
                }
                break;
            case MAX:
                if(GamepadClass.getInstance().dpad_down()) {
                    target = target - increment;
                    liftState = LiftState.INRANGE;
                }
                break;
            case INRANGE:
                if(GamepadClass.getInstance().dpad_up()) {
                    target = target + increment;
                }
                if(GamepadClass.getInstance().dpad_down()) {
                    target = target - increment;
                }
                if(target > MAX_TICKS){
                    liftState = LiftState.MAX;
                    target = MAX_TICKS;
                }
                if(target < MIN_TICKS){
                    liftState = LiftState.MIN;
                    target = MIN_TICKS;
                }
                break;
        }

        controller.setPID(p, i, d);
        int pivot_pos = motorPivot.getCurrentPosition();
        double pid = controller.calculate(pivot_pos, target);
        //mai trebuie sa vorbesc cu sover de feedforward
        double power = pid;

        motorPivot.setPower(power);

        /*telemetry.addData("pos ", pivot_pos);
        telemetry.addData("target ", target);
        telemetry.update();*/
    }

}
