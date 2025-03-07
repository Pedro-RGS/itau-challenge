package br.upe.DesafioItau.controller;

import br.upe.DesafioItau.business.services.StatisticsService;
import br.upe.DesafioItau.controller.dtos.StatisticsResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class StatisticsControllerTest {

    @InjectMocks
    StatisticsController statisticsController;

    @Mock
    StatisticsService statisticsService;

    StatisticsResponseDTO statisticsResponseDTO;

    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(statisticsController).build();
        statisticsResponseDTO = new StatisticsResponseDTO(
                1L, 20.0, 20.0, 20.0, 20.);
    }

    @Test
    void searchStatisticsTest() throws Exception {
        when(statisticsService.getStatistics(60)).thenReturn(statisticsResponseDTO);

        mockMvc.perform(get("/estatistica")
                    .param("searchInterval", "60")
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.count").value(statisticsResponseDTO.count()));
    }
}