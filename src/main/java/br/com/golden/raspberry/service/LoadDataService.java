package br.com.golden.raspberry.service;

import br.com.golden.raspberry.model.Movie;
import br.com.golden.raspberry.repository.MovieRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

@Service
public class LoadDataService {

    @Autowired
    private MovieRepository movieRepository;

    public void loadData() throws IOException {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        InputStream in = cl.getResourceAsStream("movielist.csv");
        Reader reader = new InputStreamReader(in);
        Iterable<CSVRecord> records = CSVFormat.RFC4180
                .withDelimiter(';')
                .withHeader("year", "title", "studios", "producers", "winner")
                .parse(reader);

        for (CSVRecord record : records) {
            if (record.getRecordNumber() == 1) {
                continue;
            }

            Movie movie = new Movie();
            movie.setYear(Integer.parseInt(record.get("year")));
            movie.setTitle(record.get("title"));
            movie.setStudios(record.get("studios"));
            movie.setProducers(record.get("producers"));
            movie.setWinner("yes".equalsIgnoreCase(record.get("winner")));
            movieRepository.save(movie);
        }
    }
}
