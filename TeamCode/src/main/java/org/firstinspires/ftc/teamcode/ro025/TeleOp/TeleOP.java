package org.firstinspires.ftc.teamcode.ro025.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ro025.DriveTrain.DriveTrain;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp25")
public class TeleOP extends OpMode {

    @Override
    public void init(){
        DriveTrain.getInstance().init();
    }
    public void loop(){
        DriveTrain.getInstance().loop();
    }

}