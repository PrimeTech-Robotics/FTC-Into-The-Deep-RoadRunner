package org.firstinspires.ftc.teamcode.ro025.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ro025.Components.Drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.ro025.Components.Gamepad.GamepadClass;
import org.firstinspires.ftc.teamcode.ro025.Components.Hang.Hang;
import org.firstinspires.ftc.teamcode.ro025.Components.Limelight.Limelight;
import org.firstinspires.ftc.teamcode.ro025.Components.Outtake.Outtake;

//ne mai trebuie ceva aici sa porneasca? ionkno
@TeleOp(name = "TeleOp25")
public class TeleOp25 extends OpMode {

    @Override
    public void init() {
        Drivetrain.getInstance().init();
        GamepadClass.getInstance().init();
        Outtake.getInstance().init();
        Hang.getInstance().init();
        Limelight.getInstance().init();
    }

    public void loop() {
        Drivetrain.getInstance().loop();
        GamepadClass.getInstance().loop();
        Outtake.getInstance().loop();
        Hang.getInstance().loop();
        Limelight.getInstance().loop();
    }

}