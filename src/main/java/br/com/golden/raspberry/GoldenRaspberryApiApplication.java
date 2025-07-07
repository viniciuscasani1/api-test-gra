package br.com.golden.raspberry;

import br.com.golden.raspberry.service.LoadDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GoldenRaspberryApiApplication {

    @Autowired
    private LoadDataService loadDataService;

    public static void main(String[] args) {
        SpringApplication.run(GoldenRaspberryApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext appContext) {
        return args -> loadDataService.loadData();
    }
}
