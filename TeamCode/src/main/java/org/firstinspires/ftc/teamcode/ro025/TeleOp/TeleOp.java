package org.firstinspires.ftc.teamcode.ro025.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ro025.DriveTrain.DriveTrain;
import org.firstinspires.ftc.teamcode.ro025.Gamepad.GamepadClass;
import org.firstinspires.ftc.teamcode.ro025.Outtake.OutTake;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp25")
public class TeleOp extends OpMode {

    @Override
    public void init(){
        DriveTrain.getInstance().init();
        GamepadClass.getInstance().init();
        OutTake.getInstance().init();
    }
    public void loop(){
        DriveTrain.getInstance().loop();
        GamepadClass.getInstance().loop();
        OutTake.getInstance().loop();
    }

}