package com.vitao.aulaspring.repositories;
import com.vitao.aulaspring.domain.Cidade;
//importamos a classe categoria pra ser usada no repository

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository // Essa anotação significa que essa interface terá poderes de acesso a dados
public interface CidadeRepository extends JpaRepository<Cidade, Integer>  {

    @Transactional(readOnly = true)
    @Query(" SELECT obj FROM Cidade obj WHERE obj.estado.id = :estadoId ORDER BY obj.nome")
    public List<Cidade> findCidades(@Param("estadoId") Integer estado_id);

    
//aqui usamos interface pois nao precisamos instanciar classe 
//essa interface vai conversar com o domínio Cidade
//JpaRepository é a classe com os poderes de acesso a banco 
//Todo repository que utilizaremos é Camada de acesso a dados     
}
