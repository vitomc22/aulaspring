package com.vitao.aulaspring.services;

import com.vitao.aulaspring.domain.Cliente;
import com.vitao.aulaspring.repositories.ClienteRepository;
import com.vitao.aulaspring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ClienteService {
   @Autowired //anotaçao para instaciar objetos por injeção de dependencia ou inversão de controle
              //estamos instaciando o objeto repo
   private ClienteRepository repo;
   
   public Cliente find(Integer id) {
       Optional<Cliente> obj = repo.findById(id);  //esse método findOne veio la do nosso repository ClienteRepository
       return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found!! ID: "+id+", tipo:"+Cliente.class.getName()));
                                         

   }
}
