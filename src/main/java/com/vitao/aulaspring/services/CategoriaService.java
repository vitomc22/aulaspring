package com.vitao.aulaspring.services;

import java.util.Optional;

import com.vitao.aulaspring.domain.Categoria;
import com.vitao.aulaspring.repositories.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
   @Autowired //anotaçao para instaciar objetos por injeção de dependencia ou inversão de controle
              //estamos instaciando o objeto repo
   private CategoriaRepository repo;
   
   public Categoria buscar(Integer id) {
       Optional<Categoria> obj = repo.findById(id);   //esse método findByid veio la do nosso repository CategoriaRepository
       //usamos o container Optional
       //um container pode armazenar varios objetos do tipo especificado                                               // usando a anotação @Autowired 
        
       return obj.orElse(null);                                    



   }
}
