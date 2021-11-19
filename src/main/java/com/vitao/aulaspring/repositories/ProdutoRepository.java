package com.vitao.aulaspring.repositories;



import com.vitao.aulaspring.domain.Categoria;
import com.vitao.aulaspring.domain.Produto;
//importamos a classe categoria pra ser usada no repository

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository // Essa anotação significa que essa interface terá poderes de acesso a dados
public interface ProdutoRepository extends JpaRepository<Produto, Integer>  {
//aqui usamos interface pois nao precisamos instanciar classe 
//essa interface vai conversar com o domínio categoria
//JpaRepository é a classe com os poderes de acesso a banco 
//Todo repository que utilizaremos é Camada de acesso a dados     

    //anotação para uso da JPQL personalizada
    //@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias" )
    @Transactional(readOnly = true)
    Page<Produto> findDistinctByNomeContainingAndCategoriasIn( String nome,  List<Categoria> categorias, Pageable pageRequest);
}
