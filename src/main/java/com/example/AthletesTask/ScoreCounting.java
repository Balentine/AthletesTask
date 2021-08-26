package com.example.AthletesTask;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Predicate;

public class ScoreCounting implements IScoreCounting {

    public int ShootingScoreCounting(Athlete athlete) {
        int targetScore = 172;
        int finalScore = 1000;
        int difference = athlete.shootingScore - targetScore;

        return finalScore + (difference * 12);
    }

    public int FencingScoreCounting(Athlete athlete, long athleteCount) {
        double targetScore = 0.7;
        double targetVictories = Math.ceil(athleteCount * targetScore);
        int totalScore = 1000;

        if (athlete.fencingVictories != targetVictories) {
            totalScore += (athlete.fencingVictories - targetVictories) * 40;
        }

        return totalScore;
    }

    public int SwimmingScoreCounting(Athlete athlete) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("mm:ss.SS");
        int result = 1000;
        Date targetSwimmingTime = parser.parse("02:30.00");
        long differenceMilliseconds = Math.abs(targetSwimmingTime.getTime() - athlete.swimmingTime.getTime());
        double timesToMultiply = Math.floor(differenceMilliseconds / 333);

        if (athlete.swimmingTime.getTime() < targetSwimmingTime.getTime()) {
            result += (4 * (int) timesToMultiply);
        } else if (athlete.swimmingTime.getTime() > targetSwimmingTime.getTime()) {
            result -= (4 * (int) timesToMultiply);
        }

        return result;
    }

    public int RidingScoreCounting(Athlete athlete) {
        int result = 1200;
        int knockingDownFault = athlete.ridingScores.knockingDown * 28;
        int refusalFault = athlete.ridingScores.refusal * 40;
        int disobedienceFault = athlete.ridingScores.disobedience * 60;

        result -= knockingDownFault + refusalFault + disobedienceFault;

        return result;
    }

    public void CalculateAthleteScore(List<Athlete> athletes, long athletesCount) {
        athletes.forEach(athlete -> {
            try {
                athlete.finalScore += ShootingScoreCounting(athlete)
                        + SwimmingScoreCounting(athlete)
                        + FencingScoreCounting(athlete, athletesCount)
                        + RidingScoreCounting(athlete);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        });
    }

    public void ConcludingEventScoreCounting(List<Athlete> athletes, long athletesCount) {
        CalculateAthleteScore(athletes, athletesCount);

        athletes.sort((a, b) -> b.finalScore - a.finalScore);

        for (int i = 1; i < athletesCount; i++) {
            athletes.get(i).concludingEventTime = (athletes.get(0).finalScore - athletes.get(i).finalScore) * 1000;
        }

        athletes.forEach(athlete -> {
            athlete.finalTime = athlete.concludingEventTime + athlete.runningTime.getTime();
        });


    }

    public void calculateFinalResults(List<Athlete> athletes) {
        long athletesCount = athletes.stream().count();

        ConcludingEventScoreCounting(athletes, athletesCount);
        athletes.sort(Comparator.comparingLong(a -> a.finalTime));

        setAthletePlaces(athletes, athletesCount);
//        return athletes;
    }

    public List<Athlete> findAll(List<Athlete> athletes, Predicate<Athlete> predicate) {
        List<Athlete> accumulator = new ArrayList<>();
        for (Athlete athlete : athletes) {
            if (predicate.test(athlete)) {
                accumulator.add(athlete);
            }
        }
        return accumulator;
    }

    public void setAthletePlaces(List<Athlete> athletes, long athletesCount) {
        for (int i = 0; i < athletesCount; i++) {
            int place = i + 1;
            final int index = i;

            List<Athlete> samePlaces = findAll(athletes, a -> a.finalTime == athletes.get(index).finalTime);

            long sameCount = samePlaces.stream().count();

            if (sameCount > 1) {
                for (int j = 0; j < sameCount; j++) {
                    athletes.get(index + j).place = place + "-" + (place + sameCount - 1);
                }
                i += sameCount - 1;
            } else {
                athletes.get(i).place = "" + place;
            }
        }
    }
}
