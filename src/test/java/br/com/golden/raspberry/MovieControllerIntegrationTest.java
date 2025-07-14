package br.com.golden.raspberry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class MovieControllerIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    @DisplayName("Deve retornar exatamente os produtores com menor e maior intervalo de prêmios com base no CSV original")
    public void shouldReturnExactProducerIntervalsFromCsvData() throws Exception {
        mockMvc.perform(get("/producers/intervals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.min", hasSize(1)))
                .andExpect(jsonPath("$.min[0].producer", is("Joel Silver")))
                .andExpect(jsonPath("$.min[0].interval", is(1)))
                .andExpect(jsonPath("$.min[0].previousWin", is(1990)))
                .andExpect(jsonPath("$.min[0].followingWin", is(1991)))

                .andExpect(jsonPath("$.max", hasSize(1)))
                .andExpect(jsonPath("$.max[0].producer", is("Matthew Vaughn")))
                .andExpect(jsonPath("$.max[0].interval", is(10)))
                .andExpect(jsonPath("$.max[0].previousWin", is(2002)))
                .andExpect(jsonPath("$.max[0].followingWin", is(2012)));
    }
}
