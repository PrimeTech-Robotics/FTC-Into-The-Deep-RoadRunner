package org.firstinspires.ftc.teamcode.ro025.Outtake;

public class OutTake {
    private static OutTake instance = null;

    public static synchronized OutTake getInstance() { //creezi o instanta
        if (instance == null) {
            instance = new OutTake();
        }
        return instance;
    }

    public void init() {
        Claw.getInstance().init();
        Extension.getInstance().init();
        Pivot.getInstance().init();
    }

    public void loop() {
        Claw.getInstance().loop();
        Extension.getInstance().loop();
        Pivot.getInstance().loop();
    }
}
