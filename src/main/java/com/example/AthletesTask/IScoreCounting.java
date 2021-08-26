package com.example.AthletesTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

// Using adapter design pattern for score counting by creating an interface for the methods
// I am providing only the return types and input requirements, but depending on a class itself it would implement
// based on requirements

public interface IScoreCounting {

    int ShootingScoreCounting(Athlete athlete);

    int FencingScoreCounting(Athlete athlete, long athleteCount);

    int SwimmingScoreCounting(Athlete athlete) throws ParseException;

    int RidingScoreCounting(Athlete athlete);

    void CalculateAthleteScore(List<Athlete> athletes, long athletesCount);

    void ConcludingEventScoreCounting(List<Athlete> athletes, long athletesCount);

    void calculateFinalResults(List<Athlete> athletes);

    void setAthletePlaces(List<Athlete> athletes, long athletesCount);
}
