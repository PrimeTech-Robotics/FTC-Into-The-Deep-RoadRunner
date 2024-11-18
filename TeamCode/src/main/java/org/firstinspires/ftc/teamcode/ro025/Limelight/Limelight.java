package org.firstinspires.ftc.teamcode.ro025.Limelight;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.teamcode.ro025.Outtake.Claw;

public class Limelight {
    private static Limelight instance = null;
    private Limelight3A limelight;

    public static synchronized Limelight getInstance() {
        if (instance == null) {
            instance = new Limelight();
        }
        return instance;
    }
    public void init(){
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        telemetry.setMsTransmissionInterval(11);
        limelight.pipelineSwitch(1);
        limelight.start();

    }
    public void loop(){
        LLResult result = limelight.getLatestResult();
        if (result != null) {
            if (result.isValid()) {
                telemetry.addData("tx", result.getTx());
                telemetry.addData("ty", result.getTy());
                double[] pythonOutputs = result.getPythonOutput();

                if (pythonOutputs != null && pythonOutputs.length > 0) {
                    // Display the Python script outputs
                    for (int i = 0; i < pythonOutputs.length; i++) {
                        telemetry.addData("Python Output " + i, pythonOutputs[i]);
                    }
                } else {
                    telemetry.addData("Python Output", "No data available");
                }
            }
        }
    }
}
