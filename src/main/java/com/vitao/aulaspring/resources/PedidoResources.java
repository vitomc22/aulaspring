package com.vitao.aulaspring.resources;

import com.vitao.aulaspring.domain.Pedido;

import com.vitao.aulaspring.services.PedidoService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController // anotação para tranformar a classe em uma classe rest
@RequestMapping(value = "/pedidos") // anotação para informar o endpoint do rest

public class PedidoResources {

    @Autowired // auto instaciar service
    private PedidoService service; // estamos acessado o serviço criado em Pedido Service

    // Para essa função ser uma função REST =, preciso associar um verbo HTTP "GET"
    // adicionamos no construtor de requestmapping o parametro value = "id" para
    // passar como argumento no end point
    @GetMapping(value = "/{id}") // Estou dizendo que é um metodo GET
    public ResponseEntity<Pedido> find(@PathVariable Integer id) { // trocamos o nome da função e colocamos a notação
                                                                   // @PathVariable
        Pedido obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
        // usamos URI para o java retornar do banco a mensagem de sucesso ou erro
    }

    @GetMapping
    public ResponseEntity<Page<Pedido>> findPage(
         @RequestParam(value = "page", defaultValue = "0") Integer page,
         @RequestParam(value = "linesPerPage", defaultValue = "24")   Integer linesPerPage,
         @RequestParam(value = "orderBy", defaultValue = "instante")   String orderBy,
         @RequestParam(value = "direction", defaultValue = "DESC")   String direction) {
        Page<Pedido> list = service.findPage(page,linesPerPage,orderBy,direction);
            return ResponseEntity.ok().body(list);
        // Aqui estamos usando os parâmetros de paginação criados em CategoriaService
    }

}
