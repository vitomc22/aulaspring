package com.vitao.aulaspring.resources;


import com.vitao.aulaspring.domain.Categoria;
import com.vitao.aulaspring.services.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController                               //anotação para tranformar a classe em uma classe rest
@RequestMapping(value = "/categorias")         // anotação para informar o endpoint do rest

public class CategoriaResources {

   @Autowired //auto instaciar service
    private CategoriaService service; //estamos acessado o serviço criado em Categoria Service

   // Para essa função ser uma função REST =, preciso associar um verbo HTTP "GET"
   //adicionamos no contrutor de requestmapping o parametro value = "id" para passar como argumento no end point
   @RequestMapping(value="/{id}" , method = RequestMethod.GET) //Estou dizendo que é um metodo GET
    public ResponseEntity<?> find(@PathVariable Integer id){ // trocamos o nome da função e colocamos a notação @PathVariable
    //trocamos o tipo de retorno                         // assim o spring sabe que o caminho que colocamos de id no end point virá como parametro para a função find
    //response entity é preparado para respostas REST
    //o ? siginifica qualquer tipo de dado como resposta   
    Categoria obj = service.find(id);
    //aqui ja instaciamos o obj e chamamos a função buscar la de CategoriaService e com id de parametro
    
    return ResponseEntity.ok().body(obj);
    //aqui a resposta é recebida com a classe ResponseEntity igual em find
    //essa classe lida com as requisiçoes complexas de http
    //chamamos o metodo ok se der tudo certo e apresentamos o objeto buscado

    
    }       
    
    
}
