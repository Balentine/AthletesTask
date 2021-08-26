package com.example.AthletesTask;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class AthleteTests {

    @Test
    public void correctValues_ShouldSetAthleteObject() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss.SS");
        Athlete mockAthlete = new Athlete("Test Athlete", 6, "02:29.00", new Riding(1, 1, 1), 171, "01:20.00");


        assertEquals("Test Athlete", mockAthlete.name);
        assertEquals(6, mockAthlete.fencingVictories);
        assertEquals("02:29.00", dateFormat.format(mockAthlete.swimmingTime));
        assertEquals(1, mockAthlete.ridingScores.knockingDown);
        assertEquals(1, mockAthlete.ridingScores.refusal);
        assertEquals(1, mockAthlete.ridingScores.disobedience);
        assertEquals(171, mockAthlete.shootingScore);
        assertEquals("01:20.00", dateFormat.format(mockAthlete.runningTime));
    }

    @Test
    public void wrongVariables_ShouldThrowException() throws ParseException {
        Throwable exception = assertThrows(ParseException.class, () -> {
            new Athlete("Test Athlete", 6, "02:29:00", new Riding(1, 1, 1), 171, "01:20.00");
        });

        assertEquals("Unparseable date: \"02:29:00\"", exception.getMessage());
    }
}