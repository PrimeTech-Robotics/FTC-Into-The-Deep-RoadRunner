package com.primetechrobotics.visualizer;

import com.acmerobotics.roadrunner.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class Visualizer {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity robot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(160, 160, Math.toRadians(360), Math.toRadians(360), 15)
                .build();

        robot.runAction(robot.getDrive().actionBuilder(new Pose2d(60, -30, 0))
                .lineToX(50)
                .turn(Math.toRadians(90))
                .lineToY(50)
                .turn(Math.toRadians(90))
                .lineToX(-55)
                .turn(Math.toRadians(90))
                .lineToY(-55)
                .turn(Math.toRadians(90))
                .lineToX(60)
                .turn(Math.toRadians(90))
                .lineToY(-30)
                .turn(Math.toRadians(-90))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(robot)
                .start();
    }
}