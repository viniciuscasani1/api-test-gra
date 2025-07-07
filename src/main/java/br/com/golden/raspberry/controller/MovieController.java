package br.com.golden.raspberry.controller;

import br.com.golden.raspberry.dto.AwardIntervalResultDTO;
import br.com.golden.raspberry.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producers")
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping("/intervals")
    public AwardIntervalResultDTO getIntervals() {
        return service.calculateIntervals();
    }
}
