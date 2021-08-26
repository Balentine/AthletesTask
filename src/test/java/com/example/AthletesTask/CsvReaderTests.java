package com.example.AthletesTask;

import org.junit.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.Csv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CsvReaderTests {
    @Test
    public void givenPath_shouldSetPathToCsv(){
        CsvReader csvReader = new CsvReader("path1");
        assertEquals("path1", csvReader.pathToCsv);
    }

    @Test
    public void emptyPath_shouldThrowFileNotFoundException() throws IOException, ParseException {
        CsvReader csvReader = new CsvReader("");
        Throwable exception = assertThrows(FileNotFoundException.class, () -> csvReader.readAthletesFromCsv());
        assertEquals("", exception.getMessage());
    }

    @Test
    public void correctPath_shouldGetAthletes() throws IOException, ParseException {
        CsvReader csvReader = new CsvReader("src/main/resources/static/Athlete_Results.csv");
        List<Athlete> athletes = csvReader.readAthletesFromCsv();

        assertEquals(10, athletes.stream().count());
    }
}
