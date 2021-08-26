package com.example.AthletesTask;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Athlete{
    public final String name;
    public final int fencingVictories;
    public final Date swimmingTime;
    public final Riding ridingScores;
    public final int shootingScore;
    public final Date runningTime;
    public int finalScore = 0;
    public long concludingEventTime = 0;
    public long finalTime = 0;
    public String place = "";

    public Athlete(String name, int fencingVictories, String swimmingTime, Riding riding, int shootingScore, String runningTime) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss.SS");
        this.name = name;
        this.fencingVictories = fencingVictories;
        this.swimmingTime = dateFormat.parse(swimmingTime);
        this.ridingScores = riding;
        this.shootingScore = shootingScore;
        this.runningTime = dateFormat.parse(runningTime);
    }
}