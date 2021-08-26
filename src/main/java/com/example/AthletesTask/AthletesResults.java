package com.example.AthletesTask;


import java.text.SimpleDateFormat;

public class AthletesResults{
    public final String name;
    public final int fencingVictories;
    public final String swimmingTime;
    public final Riding ridingScores;
    public final int shootingScore;
    public final String runningTime;
    public int finalScore = 0;
    public String concludingEventTime = "";
    public String finalTime = "";
    public String place = "";

    public AthletesResults(Athlete athlete){
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss.SS");
        this.name = athlete.name;
        this.fencingVictories = athlete.fencingVictories;
        this.swimmingTime = dateFormat.format(athlete.swimmingTime.getTime());
        this.ridingScores = athlete.ridingScores;
        this.shootingScore = athlete.shootingScore;
        this.runningTime = dateFormat.format(athlete.runningTime.getTime());
        this.finalScore = athlete.finalScore;
        this.concludingEventTime = dateFormat.format(athlete.concludingEventTime);
        this.finalTime = dateFormat.format(athlete.finalTime);
        this.place = athlete.place;
    }
}