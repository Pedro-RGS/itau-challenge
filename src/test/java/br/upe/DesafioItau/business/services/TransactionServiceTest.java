package br.upe.DesafioItau.business.services;

import br.upe.DesafioItau.controller.dtos.StatisticsResponseDTO;
import br.upe.DesafioItau.controller.dtos.TransactionRequestDTO;
import br.upe.DesafioItau.infrastructure.exceptions.UnprocessableEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    TransactionService transactionService;

    TransactionRequestDTO transaction;

    StatisticsResponseDTO statistics;

    @BeforeEach
    void setUp() {
        transaction = new TransactionRequestDTO(20.0, OffsetDateTime.now());
        statistics = new StatisticsResponseDTO(1L, 20.0, 20.0, 20.0, 20.);
    }

    @Test
    void addTransactionTest() {
        transactionService.addTransaction(transaction);

        List<TransactionRequestDTO> transactions = transactionService.searchTransactions(5000);

        Assertions.assertTrue(transactions.contains(transaction));
    }

    @Test
    void exceptionThrownWhenNegativeTransactionTest() {
        UnprocessableEntity exception = Assertions.assertThrows(UnprocessableEntity.class,
                () -> transactionService.addTransaction(new TransactionRequestDTO(-20.0, OffsetDateTime.now())));

        assertEquals("Value cannot be negative", exception.getMessage());
    }

    @Test
    void exceptionThrownWhenFutureTransactionTest() {
        UnprocessableEntity exception = Assertions.assertThrows(UnprocessableEntity.class,
                () -> transactionService.addTransaction(new TransactionRequestDTO(20.0, OffsetDateTime
                        .of(2026, 2, 27, 19, 6, 12, 92_000_000,
                                ZoneOffset.UTC))));
        assertEquals("Data time cannot be in the future", exception.getMessage());
    }

    @Test
    void deleteAllTransactionTest() {
        transactionService.deleteAllTransactions();

        List<TransactionRequestDTO> transactions = transactionService.searchTransactions(5000);

        Assertions.assertTrue(transactions.isEmpty());
    }

    @Test
    void searchTransactionsTest() {
        TransactionRequestDTO newTransaction = new TransactionRequestDTO(300.0,
                OffsetDateTime.now().minusHours(2));

        transactionService.addTransaction(transaction);
        transactionService.addTransaction(newTransaction);

        List<TransactionRequestDTO> transactions = transactionService.searchTransactions(60);

        assertTrue(transactions.contains(transaction));
        assertFalse(transactions.contains(newTransaction));
    }
}