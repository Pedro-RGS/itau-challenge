package br.upe.DesafioItau.controller;

import br.upe.DesafioItau.business.services.TransactionService;
import br.upe.DesafioItau.controller.dtos.TransactionRequestDTO;
import br.upe.DesafioItau.infrastructure.exceptions.UnprocessableEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.coyote.Response;
import org.apache.coyote.http11.upgrade.UpgradeServletOutputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    @InjectMocks
    TransactionController transactionController;

    @Mock
    TransactionService transactionService;

    TransactionRequestDTO transactionRequestDTO;

    MockMvc mockMvc;

    final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
        transactionRequestDTO = new TransactionRequestDTO(100.0, OffsetDateTime
                .of(2026, 2, 27, 19, 6, 12, 92_000_000,
                        ZoneOffset.UTC));
    }

    @Test
    void createTransactionTest() throws Exception {
        doNothing().when(transactionService).addTransaction(transactionRequestDTO);

        mockMvc.perform(post("/transacao")
                    .content(objectMapper.writeValueAsString(transactionRequestDTO))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void exceptionWhenCreateTransactionTest() throws Exception {
        doThrow(new UnprocessableEntity("Requisition Error"))
                .when(transactionService).addTransaction(transactionRequestDTO);

        mockMvc.perform(post("/transacao")
                        .content(objectMapper.writeValueAsString(transactionRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void deleteTransactionsTest() throws Exception {
        doNothing().when(transactionService).deleteAllTransactions();

        mockMvc.perform(delete("/transacao"))
                .andExpect(status().isOk());
    }
}
