package com.example.AthletesTask;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AthletesController {
    public CsvReader csvReader = new CsvReader("src/main/resources/static/Athlete_Results.csv");
    public ScoreCounting scoreCounting = new ScoreCounting();

    @GetMapping("/athletes")
    public List<AthletesResults> getAthletes() throws IOException, ParseException {
        List<Athlete> athletes = csvReader.readAthletesFromCsv();
        scoreCounting.calculateFinalResults(athletes);
        List<AthletesResults> athletesResults = new ArrayList<>();

        athletes.forEach(athlete -> {
            athletesResults.add(new AthletesResults(athlete));
        });

        return athletesResults;
    }
}