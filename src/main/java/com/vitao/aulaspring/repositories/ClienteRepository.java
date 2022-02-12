package com.vitao.aulaspring.repositories;

import com.vitao.aulaspring.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository // Essa anotação significa que essa interface terá poderes de acesso a dados
public interface ClienteRepository extends JpaRepository<Cliente, Integer>  {

    @Transactional(readOnly = true)
    Cliente findByEmail(String email);
//aqui usamos interface pois nao precisamos instanciar classe 
//essa interface vai conversar com o domínio categoria
//JpaRepository é a classe com os poderes de acesso a banco 
//Todo repository que utilizaremos é Camada de acesso a dados    
}
