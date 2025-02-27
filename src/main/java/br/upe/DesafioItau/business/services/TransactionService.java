package br.upe.DesafioItau.business.services;

import br.upe.DesafioItau.controller.dtos.TransactionRequestDTO;
import br.upe.DesafioItau.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {
    private final ArrayList<TransactionRequestDTO> transactions = new ArrayList<>();

    public void addTransaction(TransactionRequestDTO dto) {
        log.info("The process of adding transaction has been initiated");

        if(dto.dateTime().isAfter(OffsetDateTime.now())){
            log.error("Data time cannot be in the future");
            throw new UnprocessableEntity("Data time cannot be in the future");
        }

        if(dto.value() < 0){
            log.error("Value cannot be negative");
            throw new UnprocessableEntity("Value cannot be negative");
        }

        log.info("Transaction was added successfully! " + dto);
        transactions.add(dto);
    }

    public void deleteAllTransactions() {
        log.info("The process of deleting all transactions has been initiated");
        log.info("Transactions were deleted successfully!");
        transactions.clear();
    }

    public List<TransactionRequestDTO> searchTransactions(Integer interval){
        log.info("The process of searching transactions has been initiated");
        OffsetDateTime dateTimeInterval = OffsetDateTime.now().minusSeconds(interval);

        log.info("Transactions for the interval{}were searched successfully!", interval);
        return transactions
                .stream()
                .filter(transaction -> transaction
                        .dateTime()
                        .isAfter(dateTimeInterval))
                .toList();
    }
}
