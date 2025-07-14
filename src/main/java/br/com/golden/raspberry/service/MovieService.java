package br.com.golden.raspberry.service;

import br.com.golden.raspberry.dto.AwardIntervalResultDTO;
import br.com.golden.raspberry.dto.ProducerIntervalDTO;
import br.com.golden.raspberry.model.Movie;
import br.com.golden.raspberry.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {

    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public AwardIntervalResultDTO calculateIntervals() {
        List<Movie> winners = repository.findByWinnerTrueOrderByYearAsc();

        Map<String, Integer> lastWin = new HashMap<>();
        List<ProducerIntervalDTO> minList = new ArrayList<>();
        List<ProducerIntervalDTO> maxList = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (Movie movie : winners) {
            int year = movie.getYear();

            for (String raw : movie.getProducers().split(",| and ")) {
                String producer = raw.trim();
                Integer previous = lastWin.put(producer, year);
                if (previous == null) continue;

                int interval = year - previous;
                ProducerIntervalDTO dto = new ProducerIntervalDTO(producer, interval, previous, year);

                if (interval < min) {
                    min = interval;
                    minList.clear();
                    minList.add(dto);
                } else if (interval == min) {
                    minList.add(dto);
                }

                if (interval > max) {
                    max = interval;
                    maxList.clear();
                    maxList.add(dto);
                } else if (interval == max) {
                    maxList.add(dto);
                }
            }
        }

        return new AwardIntervalResultDTO(minList, maxList);
    }
}
