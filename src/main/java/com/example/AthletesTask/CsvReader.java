package com.example.AthletesTask;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    public final String pathToCsv;

    public CsvReader(String pathToCsv){
        this.pathToCsv = pathToCsv;
    }
    public List<Athlete> readAthletesFromCsv() throws IOException, ParseException {
        BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
        List<Athlete> athletes = new ArrayList<>();
        String row = "";
        while((row = csvReader.readLine()) != null){
            String[] data = row.split(",");
            athletes.add(new Athlete(data[0],
                    Integer.parseInt(data[1]),
                    data[2],
                    new Riding(Integer.parseInt(data[3]), Integer.parseInt(data[4]), Integer.parseInt(data[5])),
                    Integer.parseInt(data[6]),
                    data[7]
                    ));
        }

        return athletes;
    }
}
