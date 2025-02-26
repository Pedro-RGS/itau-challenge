package br.upe.DesafioItau.business.services;

import br.upe.DesafioItau.controller.dtos.StatisticsResponseDTO;
import br.upe.DesafioItau.controller.dtos.TransactionRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsService {

    private final TransactionService transactionService;

    public StatisticsResponseDTO getStatistics(Integer interval){
        log.info("The process of getting statistics has been initiated");
        List<TransactionRequestDTO> transactions = transactionService.searchTransactions(interval);

        DoubleSummaryStatistics statisticsCalculator = transactions.stream()
                .mapToDouble(TransactionRequestDTO::value).summaryStatistics();

        if (transactions.isEmpty()) {
            log.info("Transactions are empty");
            return new StatisticsResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        log.info("Statistics for the interval{}were returned successfully!", interval);
        return new StatisticsResponseDTO(
                statisticsCalculator.getCount(),
                statisticsCalculator.getSum(),
                statisticsCalculator.getAverage(),
                statisticsCalculator.getMin(),
                statisticsCalculator.getMax());
    }
}
