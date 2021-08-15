package com.vitao.aulaspring.repositories;

import com.vitao.aulaspring.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Essa anotação significa que essa interface terá poderes de acesso a dados
public interface PedidoRepository extends JpaRepository<Pedido, Integer>  {

    
//aqui usamos interface pois nao precisamos instanciar classe 
//essa interface vai conversar com o domínio categoria
//JpaRepository é a classe com os poderes de acesso a banco 
//Todo repository que utilizaremos é Camada de acesso a dados     
}
