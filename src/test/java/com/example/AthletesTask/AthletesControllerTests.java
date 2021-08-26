package com.example.AthletesTask;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RestController
public class AthletesControllerTests {
    public AthletesController controller = new AthletesController();
    public ScoreCounting scoreCounting = new ScoreCounting();

    @Test
    public void correctPath_shouldReturnAthletes() throws IOException, ParseException {
        List<AthletesResults> athletesResults = controller.getAthletes();

        assertEquals(10, athletesResults.stream().count());
    }

    @Test
    public void wrongPath_shouldThrowException() throws IOException, ParseException {
        controller.csvReader = new CsvReader("");
        Throwable exception = assertThrows(FileNotFoundException.class, () ->  controller.getAthletes());
        Assertions.assertEquals("", exception.getMessage());
    }
}