package com.vitao.aulaspring.services;


import com.vitao.aulaspring.domain.Categoria;
import com.vitao.aulaspring.domain.Produto;
import com.vitao.aulaspring.repositories.CategoriaRepository;
import com.vitao.aulaspring.repositories.ProdutoRepository;
import com.vitao.aulaspring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProdutoService {
    @Autowired //anotaçao para instaciar objetos por injeção de dependencia ou inversão de controle
    //estamos instaciando o objeto repo
    private ProdutoRepository repo;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto find(Integer id) {
        Optional<Produto> obj = repo.findById(id);  //esse método findOne veio la do nosso repository ClienteRepository
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found!! ID: " + id + ", tipo:" + Produto.class.getName()));
    }

    public Page<Produto> search(String nome, List<Integer> ids , Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);

    }



    }

