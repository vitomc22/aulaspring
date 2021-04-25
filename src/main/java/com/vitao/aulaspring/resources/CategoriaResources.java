package com.vitao.aulaspring.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController                               //anotação para tranformar a classe em uma classe rest
@RequestMapping(value = "/categorias")         // anotação para informar o endpoint do rest

public class CategoriaResources {

   // Para essa função ser uma função REST =, preciso associar um verbo HTTP
   @RequestMapping(method = RequestMethod.GET) //Estou dizendo que é um metodo GET
    public String listar()
    {
        return "REST ESTA FUNFANDO";
    }
    
    
}
