package com.vitao.aulaspring.services;

import com.vitao.aulaspring.services.exceptions.DataIntegrityException;
import com.vitao.aulaspring.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

import com.vitao.aulaspring.domain.Categoria;
import com.vitao.aulaspring.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;



@Service
public class CategoriaService {
   @Autowired //anotaçao para instaciar objetos por injeção de dependencia ou inversão de controle
              //estamos instaciando o objeto repo
   private CategoriaRepository repo;
   
   public Categoria find(Integer id) {
       Optional<Categoria> obj = repo.findById(id);  //esse método findOne veio la do nosso repository CategoriaRepository
          return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found!! ID: "+id+", tipo:"+Categoria.class.getName()));
                                         

   }

   public Categoria insert (Categoria obj){ //serviço de inserção de dados no banco
       obj.setId(null); //verificação de objeto novo
            return repo.save(obj);
   }

    public Categoria update (Categoria obj){ //serviço de atualização de dados no banco
           find(obj.getId());
           return repo.save(obj);
    }

    public void delete(Integer id){ //serviço de exclusão de dados no banco
       find(id);
       try {
           repo.deleteById(id);
       }
       catch (DataIntegrityViolationException e){
           throw new DataIntegrityException("Não é possível excluir uma categoria com produtos cadastrados!! Exclua primeiro.");
       }

    }

    public List<Categoria> findAll(){
       return  repo.findAll();
    }
}
