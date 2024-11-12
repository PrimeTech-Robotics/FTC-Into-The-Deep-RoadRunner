package org.firstinspires.ftc.teamcode.ro025.Gamepad;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.Gamepad;

public class GamepadClass {
    private static GamepadClass instance = null;
    Gamepad currentGamepad = null;
    Gamepad previousGamepad = null;

    public static synchronized GamepadClass getInstance() {
        if (instance == null) {
            instance = new GamepadClass();
        }
        return instance;
    }

    public void init() {
        currentGamepad = new Gamepad();
        currentGamepad.copy(gamepad1);

        previousGamepad = new Gamepad();
    }

    public void loop() {
        previousGamepad.copy(currentGamepad);
        currentGamepad.copy(gamepad1);
    }

    public boolean triangle() {return (currentGamepad.triangle && !previousGamepad.triangle);} //After square is pressed it opens the Hang

    public boolean square() {return (currentGamepad.square && !previousGamepad.square);} //Acts as a safety for the Hang

    public boolean circle(){ return (currentGamepad.circle && !previousGamepad.circle); } //Returns all the systems to their initial position

    public boolean cross() { return (currentGamepad.cross && !previousGamepad.cross); } //Opens and closes the Claw

    public boolean dpad_up() {
        return currentGamepad.dpad_up;
    } //Moves the Claw to the back

    public boolean dpad_down() {
        return currentGamepad.dpad_down;
    } //Moves the Claw to the front

    public double right_trigger() {
        return currentGamepad.right_trigger;
    }//Extends the sliders

    public double left_trigger() {
        return currentGamepad.left_trigger;
    } //Retracts the sliders

    public boolean left_bumper() { return currentGamepad.left_bumper;} //Rotates the Sliders down

    public boolean right_bumper() { return currentGamepad.right_bumper;} //Rotates the Sliders up

    public boolean dpad_left() {return currentGamepad.dpad_left;} //Rotates the Claw counter clockwise

    public boolean dpad_right() {
        return currentGamepad.dpad_right;
    } //Rotates the Claw clockwise
}
