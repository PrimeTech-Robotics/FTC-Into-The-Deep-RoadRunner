package org.firstinspires.ftc.teamcode.ro025.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ro025.DriveTrain.DriveTrain;
import org.firstinspires.ftc.teamcode.ro025.Gamepad.GamepadClass;
import org.firstinspires.ftc.teamcode.ro025.Hang.Hang;
import org.firstinspires.ftc.teamcode.ro025.Limelight.Limelight;
import org.firstinspires.ftc.teamcode.ro025.Outtake.OutTake;

//ne mai trebuie ceva aici sa porneasca? ionkno
@TeleOp(name = "TeleOp25")
public class TeleOp25 extends OpMode {

    @Override
    public void init() {
        DriveTrain.getInstance().init();
        GamepadClass.getInstance().init();
        OutTake.getInstance().init();
        Hang.getInstance().init();
        Limelight.getInstance().init();
    }

    public void loop() {
        DriveTrain.getInstance().loop();
        GamepadClass.getInstance().loop();
        OutTake.getInstance().loop();
        Hang.getInstance().loop();
        Limelight.getInstance().loop();
    }

}