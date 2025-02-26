package com.NetWorth.Transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing

public class ExtractTransactionApplication {

	public static void main(String[] args) {

		SpringApplication.run(ExtractTransactionApplication.class, args);
	}



}
