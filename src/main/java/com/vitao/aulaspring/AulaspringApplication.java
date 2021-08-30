package com.vitao.aulaspring;

import com.fasterxml.jackson.databind.ser.std.ArraySerializerBase;
import com.vitao.aulaspring.domain.*;
import com.vitao.aulaspring.domain.enums.EstadoPagamento;
import com.vitao.aulaspring.domain.enums.TipoCliente;
import com.vitao.aulaspring.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;



@SpringBootApplication
public class AulaspringApplication implements CommandLineRunner {   //essa classe serve para rodar um bloco de codigo antes da aplicação iniciar
	                                                                //utilizamos o função run pra isso, logo abaixo

	@Autowired //injeção de dependecia automática
	private CategoriaRepository categoriaRepository;

	@Autowired //injeção de dependecia automática
	private ProdutoRepository produtoRepository;

	@Autowired //injeção de dependecia automática
	private EstadoRepository estadoRepository;

	@Autowired //injeção de dependecia automática
	private CidadeRepository cidadeRepository;

	@Autowired //injeção de dependecia automática
	private ClienteRepository clienteRepository;

	@Autowired //injeção de dependecia automática
	private EnderecoRepository enderecoRepository;

	@Autowired //injeção de dependecia automática
	private PedidoRepository pedidoRepository;

	@Autowired //injeção de dependecia automática
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;



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

	Estado est1 = new Estado(null,"Minais Gerais");
	Estado est2 = new Estado(null,"São Paulo");

	Cidade c1 = new Cidade(null,"Belo Horizonte",est1);
	Cidade c2 = new Cidade(null,"Rozeiras",est2);
	Cidade c3 = new Cidade(null,"Campinas",est2);

	Cliente cli1 = new Cliente(null,"victor oliveira","vimatozin@hotmail.com","17322024788", TipoCliente.PESSOAFISICA);

	cli1.getTelefones().addAll(Arrays.asList("24999121205","2433227791"));

	Endereco e1 = new Endereco(null,"Rua Francisco Rodrigues Leite","24","ao 101","Vila Coringa","27321380",cli1,c1);
	Endereco e2 = new Endereco(null,"Rua Francisco Rodrigues Leite","25",null,"Vila Coringa","27321380",cli1,c2);

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	Pedido ped1 = new Pedido(null,sdf.parse("30/09/2017 10:32"),cli1,e1);
	Pedido ped2 = new Pedido(null,sdf.parse("10/10/2017 10:32"),cli1,e2);

    Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
    ped1.setPagamento(pagto1);

	Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE,ped2, sdf.parse("20/10/2017 00:00"),null);

	ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00 , 1 , 2000.0);
	ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.0);
	ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.0);

	ped1.getItens().addAll(Arrays.asList(ip1, ip2));
	ped2.getItens().addAll(Arrays.asList(ip3));

	p1.getItens().addAll(Arrays.asList(ip1));
	p2.getItens().addAll(Arrays.asList(ip3));
	p3.getItens().addAll(Arrays.asList(ip2));



	ped2.setPagamento(pagto2);

	cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

	cli1.getEnderecos().addAll(Arrays.asList(e1,e2));

	cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
	cat2.getProdutos().addAll(Arrays.asList(p2));

	p1.getCategorias().addAll(Arrays.asList(cat1));
	p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
	p3.getCategorias().addAll(Arrays.asList(cat1));

	est1.getCidades().addAll(Arrays.asList(c1));
	est2.getCidades().addAll(Arrays.asList(c2,c3));







	categoriaRepository.saveAll(Arrays.asList(cat1,cat2));	 //tomar cuidado com a lib usada
    produtoRepository.saveAll(Arrays.asList(p1,p2,p3));      //o método Arrays.asList da lib mathcs nao aceita objetos  como parâmetro
    estadoRepository.saveAll(Arrays.asList(est1,est2));
	cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));     //instanciar pelo import java.util.Arrays
	clienteRepository.saveAll(Arrays.asList(cli1));
	enderecoRepository.saveAll(Arrays.asList(e1,e2));
	pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
	pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
	itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));

	
	}                                                         
}