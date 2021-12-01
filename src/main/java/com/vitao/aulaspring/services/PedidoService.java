package com.vitao.aulaspring.services;

import com.vitao.aulaspring.domain.ItemPedido;
import com.vitao.aulaspring.domain.PagamentoComBoleto;
import com.vitao.aulaspring.domain.Pedido;

import com.vitao.aulaspring.domain.enums.EstadoPagamento;
import com.vitao.aulaspring.repositories.ItemPedidoRepository;
import com.vitao.aulaspring.repositories.PagamentoRepository;
import com.vitao.aulaspring.repositories.PedidoRepository;

import com.vitao.aulaspring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired // anotaçao para instaciar objetos por injeção de dependencia ou inversão de
               // controle
               // estamos instaciando o objeto repo
    private PedidoRepository repo;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired 
    private ClienteService clienteService;

    public Pedido find(Integer id) {
        Optional<Pedido> obj = repo.findById(id); // esse método findOne veio la do nosso repository ClienteRepository
        return obj.orElseThrow(
                () -> new ObjectNotFoundException("Object not found!! ID: " + id + ", tipo:" + Pedido.class.getName()));
    }

    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.setCliente(clienteService.find(obj.getCliente().getId()));
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
        }
        obj = repo.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for (ItemPedido ip : obj.getItens()) {
            ip.setDesconto(0.0);
            ip.setProduto(produtoService.find(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());
        System.out.println(obj); //apenas um teste
        return obj;
    }
}
