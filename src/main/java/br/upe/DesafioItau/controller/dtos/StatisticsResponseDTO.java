package br.upe.DesafioItau.controller.dtos;

public record StatisticsResponseDTO(
        Long count,
        Double sum,
        Double avg,
        Double min,
        Double max ) {
}
