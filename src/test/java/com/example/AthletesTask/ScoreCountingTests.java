package com.example.AthletesTask;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScoreCountingTests {
    ScoreCounting scoreCounting = new ScoreCounting();

    public ScoreCountingTests() throws ParseException {
    }

    @Test
    @DisplayName("Score should be equal to 1000 when it is equal to target score")
    public void shootingScore_equalsToTarget_finalScore1000() throws ParseException {
        Athlete mockAthlete = new Athlete("Test Athlete", 3, "01:20.00", new Riding(1, 2, 3), 172, "01:20.00");

        assertEquals(1000, scoreCounting.ShootingScoreCounting(mockAthlete));
    }

    @Test
    @DisplayName("Score should be higher by 12 points if its higher than target score by 1 point")
    public void shootingScore_higherThanTarget_finalScore1012() throws ParseException {
        Athlete mockAthlete = new Athlete("Test Athlete", 3, "01:20.00", new Riding(1, 2, 3), 173, "01:20.00");

        assertEquals(1012, scoreCounting.ShootingScoreCounting(mockAthlete));
    }

    @Test
    @DisplayName("Score should be lower by 12 points if its lower than target score by 1 point")
    public void shootingScore_lowerThanTarget_finalScore988() throws ParseException {
        Athlete mockAthlete = new Athlete("Test Athlete", 3, "01:20.00", new Riding(1, 2, 3), 171, "01:20.00");

        assertEquals(988, scoreCounting.ShootingScoreCounting(mockAthlete));
    }

    @Test
    @DisplayName("Fencing score counting test")
    public void fencingScore_equalsTo70Percent_finalScore1000() throws ParseException {
        Athlete mockAthlete = new Athlete("Test Athlete", 7, "01:20.00", new Riding(1, 2, 3), 171, "01:20.00");

        assertEquals(1000, scoreCounting.FencingScoreCounting(mockAthlete, 10));
    }

    @Test
    public void fencingScore_higherThan70Percent_finalScore1040() throws ParseException {
        Athlete mockAthlete = new Athlete("Test Athlete", 8, "01:20.00", new Riding(1, 2, 3), 171, "01:20.00");

        assertEquals(1040, scoreCounting.FencingScoreCounting(mockAthlete, 10));
    }

    @Test
    public void fencingScore_lowerThan70Percent_finalScore1040() throws ParseException {
        Athlete mockAthlete = new Athlete("Test Athlete", 6, "01:20.00", new Riding(1, 2, 3), 171, "01:20.00");

        assertEquals(960, scoreCounting.FencingScoreCounting(mockAthlete, 10));
    }

    @Test
    public void swimmingScore_equalsToTargetTime_finalScore1000() throws ParseException {
        Athlete mockAthlete = new Athlete("Test Athlete", 6, "02:30.00", new Riding(1, 2, 3), 171, "01:20.00");

        assertEquals(1000, scoreCounting.SwimmingScoreCounting(mockAthlete));
    }

    @Test
    public void swimmingScore_higherThanTargetTime_finalScore988() throws ParseException {
        Athlete mockAthlete = new Athlete("Test Athlete", 6, "02:31.00", new Riding(1, 2, 3), 171, "01:20.00");

        assertEquals(988, scoreCounting.SwimmingScoreCounting(mockAthlete));
    }

    @Test
    public void swimmingScore_lowerThanTargetTime_finalScore1012() throws ParseException {
        Athlete mockAthlete = new Athlete("Test Athlete", 6, "02:29.00", new Riding(1, 2, 3), 171, "01:20.00");

        assertEquals(1012, scoreCounting.SwimmingScoreCounting(mockAthlete));
    }

    @Test
    public void ridingScore_withoutFault_finalScore1200() throws ParseException {
        Athlete mockAthlete = new Athlete("Test Athlete", 6, "02:29.00", new Riding(0, 0, 0), 171, "01:20.00");

        assertEquals(1200, scoreCounting.RidingScoreCounting(mockAthlete));
    }

    @Test
    public void ridingScore_withOnlyOneKnockingDownFault_finalScore1172() throws ParseException {
        Athlete mockAthlete = new Athlete("Test Athlete", 6, "02:29.00", new Riding(1, 0, 0), 171, "01:20.00");

        assertEquals(1172, scoreCounting.RidingScoreCounting(mockAthlete));
    }

    @Test
    public void ridingScore_withOnlyOneRefusalFault_finalScore1160() throws ParseException {
        Athlete mockAthlete = new Athlete("Test Athlete", 6, "02:29.00", new Riding(0, 1, 0), 171, "01:20.00");

        assertEquals(1160, scoreCounting.RidingScoreCounting(mockAthlete));
    }

    @Test
    public void ridingScore_withOnlyOneDisobedienceFault_finalScore1140() throws ParseException {
        Athlete mockAthlete = new Athlete("Test Athlete", 6, "02:29.00", new Riding(0, 0, 1), 171, "01:20.00");

        assertEquals(1140, scoreCounting.RidingScoreCounting(mockAthlete));
    }

    @Test
    public void ridingScore_withOnlyOneOfEachFault_finalScore1072() throws ParseException {
        Athlete mockAthlete = new Athlete("Test Athlete", 6, "02:29.00", new Riding(1, 1, 1), 171, "01:20.00");

        assertEquals(1072, scoreCounting.RidingScoreCounting(mockAthlete));
    }

    @Test
    public void finalScore_ShouldCalculateFinalScore_finalScore() throws ParseException {
        Athlete mockAthlete = new Athlete("Test Athlete", 6, "02:29.00", new Riding(1, 1, 1), 171, "01:20.00");
        List<Athlete> athleteList = new ArrayList<>();
        athleteList.add(mockAthlete);
        scoreCounting.CalculateAthleteScore(athleteList, 1);
        assertEquals(4272, mockAthlete.finalScore);
    }

    @Test
    public void athleteTimeIsIncorrectFormat_shouldThrowException() throws ParseException {
        Throwable exception = assertThrows(ParseException.class, () -> new Athlete("Test Athlete", 6, "02:29:00", new Riding(1, 1, 1), 171, "01:20.00"));

        assertEquals("Unparseable date: \"02:29:00\"", exception.getMessage());
    }

    @Test
    public void concludingEventScore_shouldSetConcludingEventTimeAndFinalTime() throws ParseException {
        Athlete mockAthlete1 = new Athlete("Test Athlete", 1, "02:29.00", new Riding(1, 1, 1), 171, "01:20.00");
        Athlete mockAthlete2 = new Athlete("Test Athlete", 0, "02:31.00", new Riding(1, 1, 2), 171, "01:30.00");
        List<Athlete> athleteList = new ArrayList<>();
        athleteList.add(mockAthlete1);
        athleteList.add(mockAthlete2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss.SS");

        scoreCounting.ConcludingEventScoreCounting(athleteList, 2);

        assertEquals("00:00.00", dateFormat.format(mockAthlete1.concludingEventTime));
        assertEquals("01:20.00", dateFormat.format(mockAthlete1.finalTime));
        assertEquals("02:04.00", dateFormat.format(mockAthlete2.concludingEventTime));
        assertEquals("03:34.00", dateFormat.format(mockAthlete2.finalTime));
    }

    @Test
    public void shouldSetPlacesByFinalTime() throws ParseException {
        Athlete mockAthlete1 = new Athlete("Test Athlete", 1, "02:29.00", new Riding(1, 1, 1), 171, "01:20.00");
        Athlete mockAthlete2 = new Athlete("Test Athlete", 0, "02:31.00", new Riding(1, 1, 2), 171, "01:30.00");
        List<Athlete> athleteList = new ArrayList<>();
        athleteList.add(mockAthlete1);
        athleteList.add(mockAthlete2);

        scoreCounting.ConcludingEventScoreCounting(athleteList, 2);

        assertEquals("1", mockAthlete1.place);
        assertEquals("2", mockAthlete2.place);
    }

    @Test
    public void shouldSharePlacesIfFinalTimeEquals() throws ParseException {
        Athlete mockAthlete1 = new Athlete("Test Athlete1", 1, "02:29.00", new Riding(1, 1, 1), 171, "04:20.00");
        Athlete mockAthlete2 = new Athlete("Test Athlete2", 1, "02:29.00", new Riding(1, 1, 1), 171, "04:20.00");
        List<Athlete> athleteList = new ArrayList<>();
        athleteList.add(mockAthlete1);
        athleteList.add(mockAthlete2);

        scoreCounting.calculateFinalResults(athleteList);

        assertEquals("1-2", mockAthlete1.place);
        assertEquals("1-2", mockAthlete2.place);
    }

    @Test
    public void shouldFindAllAthletes_GivenPredicate() throws ParseException {
        Athlete mockAthlete1 = new Athlete("Test Athlete1", 1, "02:29.00", new Riding(1, 1, 1), 171, "01:20.00");
        Athlete mockAthlete2 = new Athlete("Test Athlete2", 0, "02:31.00", new Riding(1, 1, 2), 171, "01:30.00");
        List<Athlete> athleteList = new ArrayList<>();
        athleteList.add(mockAthlete1);
        athleteList.add(mockAthlete2);

        List<Athlete> resultList1 = scoreCounting.findAll(athleteList, a -> a.fencingVictories == 1);
        List<Athlete> resultList2 = scoreCounting.findAll(athleteList, a -> a.ridingScores.refusal == 1);

        assertEquals(1, resultList1.stream().count());
        assertEquals(mockAthlete1, resultList1.get(0));
        assertEquals(2, resultList2.stream().count());
    }
}
