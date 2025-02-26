package br.upe.DesafioItau;

import br.upe.DesafioItau.business.services.StatisticsService;
import br.upe.DesafioItau.business.services.TransactionService;
import br.upe.DesafioItau.controller.dtos.TransactionRequestDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.OffsetDateTime;

@SpringBootApplication
public class DesafioItauApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioItauApplication.class, args);
	}
}
