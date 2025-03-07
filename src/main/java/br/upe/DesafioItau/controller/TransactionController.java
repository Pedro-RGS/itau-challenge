package br.upe.DesafioItau.controller;

import br.upe.DesafioItau.business.services.TransactionService;
import br.upe.DesafioItau.controller.dtos.TransactionRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.service.GenericResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transacao")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @Operation(description = "Endpoit responsible for add transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction added successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid fields in transaction"),
            @ApiResponse(responseCode = "400", description = "Request error"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    public ResponseEntity<Void> createTransaction(
            @RequestBody TransactionRequestDTO obj){
        transactionService.addTransaction(obj);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Endpoit responsible for delete all transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Request error"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    public ResponseEntity<Void> deleteTransactions(){
        transactionService.deleteAllTransactions();

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}