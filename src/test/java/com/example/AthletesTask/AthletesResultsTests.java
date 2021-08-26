package com.example.AthletesTask;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class AthletesResultsTests {

    @Test
    public void correctValues_ShouldSetAthleteObject() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss.SS");
        Athlete mockAthlete = new Athlete("Test Athlete", 6, "02:29.00", new Riding(1, 1, 1), 171, "01:20.00");
        AthletesResults mockAthleteResults = new AthletesResults(mockAthlete);


        assertEquals("Test Athlete", mockAthleteResults.name);
        assertEquals(6, mockAthleteResults.fencingVictories);
        assertEquals("02:29.00", mockAthleteResults.swimmingTime);
        assertEquals(1, mockAthleteResults.ridingScores.knockingDown);
        assertEquals(1, mockAthleteResults.ridingScores.refusal);
        assertEquals(1, mockAthleteResults.ridingScores.disobedience);
        assertEquals(171, mockAthleteResults.shootingScore);
        assertEquals("01:20.00", mockAthleteResults.runningTime);
    }

}