package com.vitao.aulaspring.resources;

import java.util.ArrayList;
import java.util.List;

import com.vitao.aulaspring.domain.Categoria;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController                               //anotação para tranformar a classe em uma classe rest
@RequestMapping(value = "/categorias")         // anotação para informar o endpoint do rest

public class CategoriaResources {

   // Para essa função ser uma função REST =, preciso associar um verbo HTTP
   @RequestMapping(method = RequestMethod.GET) //Estou dizendo que é um metodo GET
    public List<Categoria> listar()
    {
        
       Categoria cat1 = new Categoria(1,"Informática");
       Categoria cat2 =new Categoria(1,"Escritório");
       
       //List é uma interface então nao pode ser instanciada, para isso instaciamos um objeto de Arraylist
       List<Categoria> lista = new ArrayList<>();
       lista.add(cat1);
       lista.add(cat2);

       //List ainda retorna os dados em formato JSON na web, MASSA!!!
        return lista;
    }
    
    
}
