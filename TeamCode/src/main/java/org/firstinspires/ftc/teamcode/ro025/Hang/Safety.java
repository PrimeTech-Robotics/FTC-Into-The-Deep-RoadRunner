package org.firstinspires.ftc.teamcode.ro025.Hang;

import org.firstinspires.ftc.teamcode.ro025.Gamepad.GamepadClass;

public class Safety {

    enum Square{
        PRESSED , UNPRESSED
    }
    Square square = Square.UNPRESSED;

    private static Safety instance = null;

    public static synchronized Safety getInstance() {
        if (instance == null) {
            instance = new Safety();
        }
        return instance;
    }

    public void loop(){
        switch (square){
            case UNPRESSED:
                if(GamepadClass.getInstance().square())
                    square = Square.PRESSED;
                break;
            case PRESSED:
                if(GamepadClass.getInstance().square())
                    square = Square.UNPRESSED;
                Hang.getInstance().loop();
                break;
        }
    }
}
