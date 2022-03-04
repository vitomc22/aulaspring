package com.vitao.aulaspring.services;


import com.vitao.aulaspring.domain.Categoria;
import com.vitao.aulaspring.dto.CategoriaDTO;
import com.vitao.aulaspring.repositories.CategoriaRepository;
import com.vitao.aulaspring.services.exceptions.DataIntegrityException;
import com.vitao.aulaspring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoriaService {
    @Autowired //anotaçao para instanciar objetos por injeção de dependencia ou inversão de controle
    //estamos instanciando o objeto repo
    private CategoriaRepository repo;

    public Categoria find(Integer id) {
        Optional<Categoria> obj = repo.findById(id);  //esse método findOne veio la do nosso repository CategoriaRepository
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found!! ID: " + id + ", tipo:" + Categoria.class.getName()));


    }

    public Categoria insert(Categoria obj) { //serviço de inserção de dados no banco
        obj.setId(null); //verificação de objeto novo
        return repo.save(obj);
    }

    public Categoria update(Categoria obj) { //serviço de atualização de dados no banco
        Categoria newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(Integer id) { //serviço de exclusão de dados no banco
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria com produtos cadastrados!! Exclua primeiro.");
        }

    }

    public List<Categoria> findAll() {
        return repo.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest); ///função que retorna todas as categorias em forma de paginação

    }

    public Categoria fromDTO(CategoriaDTO objDto) {
        return new Categoria(objDto.getId(), objDto.getNome()); //método auxiliar que faz instância de um obj para obj DTO
    }

    private void updateData(Categoria newObj, Categoria obj) {
        newObj.setNome(obj.getNome());
        //classe auxiliar para buscar os dados do cliente no banco e depois atualizar
    }
}
