package com.vitao.aulaspring;

import com.vitao.aulaspring.services.S3Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AulaspringApplication implements CommandLineRunner { // essa classe serve para rodar um bloco de codigo
																	// antes da aplicação iniciar
																	// utilizamos o função run pra isso, logo abaixo
	@Autowired
	private S3Service s3Service;

	public static void main(String[] args) {
		SpringApplication.run(AulaspringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		s3Service.uploadFile("/home/victor/Documentos/MANUAL DO ACÓLITO - AASJMV.pdf");

	}

}