package com.vitao.aulaspring;

import com.vitao.aulaspring.domain.Categoria;
import com.vitao.aulaspring.repositories.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Arrays;



@SpringBootApplication
public class AulaspringApplication implements CommandLineRunner {

	@Autowired //injeção de depndecia automática
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(AulaspringApplication.class, args);
	}



@Override
public void run(String... args) throws Exception{
	Categoria cat1 = new Categoria(null, "Informática");
	Categoria cat2 = new Categoria(null, "Escritório");

	categoriaRepository.saveAll(Arrays.asList(cat1,cat2));	 //tomar cuidado com a lib usada
                                                             //o método Arrays.asList da lib mathcs nao aceita objetos  como parâmetro
   }                                                         //instanciar pelo import java.util.Arrays
}