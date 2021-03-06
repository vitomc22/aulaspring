package com.vitao.aulaspring.resources;


import com.vitao.aulaspring.domain.Produto;
import com.vitao.aulaspring.dto.ProdutoDTO;
import com.vitao.aulaspring.resources.utils.URL;
import com.vitao.aulaspring.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController                               //anotação para tranformar a classe em uma classe rest
@RequestMapping(value = "/produtos")         // anotação para informar o endpoint do rest

public class ProdutoResourses {

    @Autowired
    private ProdutoService service;

    @GetMapping(value = "/{id}") //Estou dizendo que é um metodo GET
    public ResponseEntity<Produto> find(@PathVariable Integer id) { // trocamos o nome da função e colocamos a notação @PathVariable

        Produto obj = service.find(id);
        return ResponseEntity.ok().body(obj);

    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categorias", defaultValue = "") String categorias,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        String nomeDecoded = URL.decodeParam(nome);
        List<Integer> ids = URL.decodeIntList(categorias);
        Page<Produto> list = service.search(nomeDecoded, ids, page , linesPerPage, orderBy, direction);
        Page<ProdutoDTO> listDto = list.map(ProdutoDTO::new);
        return ResponseEntity.ok().body(listDto);
    }
}
