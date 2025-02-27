package br.upe.DesafioItau.controller;

import br.upe.DesafioItau.business.services.StatisticsService;
import br.upe.DesafioItau.controller.dtos.StatisticsResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(
            description = "Endpoint responsible for calculate statistics related to past transactions",
            parameters = {@Parameter(name = "searchInterval", in = ParameterIn.PATH,
                            description = "Interval from which the statistics will be calculate",
                            schema = @Schema(implementation = Integer.class))
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "statistics returned successfully"),
            @ApiResponse(responseCode = "400", description = "Request error"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    public ResponseEntity<StatisticsResponseDTO> getStatistics(
            @RequestParam(value = "searchInterval", required = false, defaultValue = "60") Integer interval){
        StatisticsResponseDTO statistics = statisticsService.getStatistics(interval);

        return ResponseEntity.ok(statistics);
    }
}
