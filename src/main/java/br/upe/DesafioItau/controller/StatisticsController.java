package br.upe.DesafioItau.controller;

import br.upe.DesafioItau.business.services.StatisticsService;
import br.upe.DesafioItau.controller.dtos.StatisticsResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estatistica")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping
    public ResponseEntity<StatisticsResponseDTO> getStatistics(
            @RequestParam(value = "searchInterval", required = false, defaultValue = "60") Integer interval){
        StatisticsResponseDTO statistics = statisticsService.getStatistics(interval);

        return ResponseEntity.ok(statistics);
    }
}
