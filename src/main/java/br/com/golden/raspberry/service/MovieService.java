package br.com.golden.raspberry.service;

import br.com.golden.raspberry.dto.AwardIntervalResultDTO;
import br.com.golden.raspberry.dto.ProducerIntervalDTO;
import br.com.golden.raspberry.model.Movie;
import br.com.golden.raspberry.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public AwardIntervalResultDTO calculateIntervals() {
        Map<String, List<Integer>> wins = new HashMap<>();
        List<Movie> byWinnerTrue = repository.findByWinnerTrue();

        for (Movie m : byWinnerTrue) {
            for (String p : m.getProducers().split(" and |, ")) {
                wins.computeIfAbsent(p.trim(), k -> new ArrayList<>()).add(m.getYear());
            }
        }

        List<ProducerIntervalDTO> intervals = new ArrayList<>();
        wins.forEach((producer, years) -> {
            if (years.size() < 2) return;
            Collections.sort(years);
            for (int i = 1; i < years.size(); i++) {
                intervals.add(new ProducerIntervalDTO(
                        producer,
                        years.get(i) - years.get(i - 1),
                        years.get(i - 1),
                        years.get(i)
                ));
            }
        });

        int min = intervals.stream().mapToInt(ProducerIntervalDTO::getInterval).min().orElse(0);
        int max = intervals.stream().mapToInt(ProducerIntervalDTO::getInterval).max().orElse(0);

        List<ProducerIntervalDTO> minList = intervals.stream()
                .filter(i -> i.getInterval() == min)
                .collect(Collectors.toList());

        List<ProducerIntervalDTO> maxList = intervals.stream()
                .filter(i -> i.getInterval() == max)
                .collect(Collectors.toList());

        return new AwardIntervalResultDTO(minList, maxList);
    }
}
