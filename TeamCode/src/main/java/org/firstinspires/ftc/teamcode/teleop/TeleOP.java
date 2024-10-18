package org.firstinspires.ftc.teamcode.teleop;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.DriveTrain.DriveTrain;

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