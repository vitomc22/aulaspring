package com.vitao.aulaspring;

import com.vitao.aulaspring.domain.Categoria;
import com.vitao.aulaspring.domain.Produto;
import com.vitao.aulaspring.repositories.CategoriaRepository;
import com.vitao.aulaspring.repositories.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Arrays;



@SpringBootApplication
public class AulaspringApplication implements CommandLineRunner {   //essa classe serve para rodar um bloco de codigo antes da aplicação iniciar
	                                                                //utilizamos o função run pra isso, logo abaixo

	@Autowired //injeção de depndecia automática
	private CategoriaRepository categoriaRepository;

	@Autowired //injeção de depndecia automática
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(AulaspringApplication.class, args);
	}



@Override
public void run(String... args) throws Exception{
	Categoria cat1 = new Categoria(null, "Informática");
	Categoria cat2 = new Categoria(null, "Escritório");

	Produto p1 = new Produto(null, "Computador", 2000.00);
	Produto p2 = new Produto(null, "impressora", 800.00);
	Produto p3 = new Produto(null, "Mouse", 80.00);

	cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
	cat2.getProdutos().addAll(Arrays.asList(p2));

	p1.getCategorias().addAll(Arrays.asList(cat1));
	p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
	p3.getCategorias().addAll(Arrays.asList(cat1));
 

	categoriaRepository.saveAll(Arrays.asList(cat1,cat2));	 //tomar cuidado com a lib usada
                                                             //o método Arrays.asList da lib mathcs nao aceita objetos  como parâmetro
                                                             //instanciar pelo import java.util.Arrays
    produtoRepository.saveAll(Arrays.asList(p1,p2,p3));															 
	
	}                                                         
}