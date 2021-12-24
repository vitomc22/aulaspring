package com.vitao.aulaspring.resources;


import com.vitao.aulaspring.domain.Categoria;
import com.vitao.aulaspring.dto.CategoriaDTO;
import com.vitao.aulaspring.services.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController                               //anotação para tranformar a classe em uma classe rest
@RequestMapping(value = "/categorias")         // anotação para informar o endpoint do rest

public class CategoriaResources {

   @Autowired //auto instaciar service
    private CategoriaService service; //estamos acessado o serviço criado em Categoria Service

   // Para essa função ser uma função REST =, preciso associar um verbo HTTP "GET"
   //adicionamos no contrutor de requestmapping o parametro value = "id" para passar como argumento no end point
   @GetMapping(value="/{id}") //Estou dizendo que é um metodo GET
    public ResponseEntity<Categoria> find(@PathVariable Integer id){ // trocamos o nome da função e colocamos a notação @PathVariable
    //trocamos o tipo de retorno assim o spring sabe que o caminho que colocamos de id no end point virá como parametro para a função find
    //response entity é preparado para respostas REST
    //o ? siginifica qualquer tipo de dado como resposta   
    Categoria obj = service.find(id);
    //aqui ja instaciamos o obj e chamamos a função buscar la de CategoriaService e com id de parametro
    
    return ResponseEntity.ok().body(obj);
    //aqui a resposta é recebida com a classe ResponseEntity igual em find
    //essa classe lida com as requisiçoes complexas de http
    //chamamos o metodo ok se der tudo certo e apresentamos o objeto buscado

    
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto){ //adicionado valid para verificar os dados do POST
        Categoria obj = service.fromDTO(objDto);//aqui buscamos o metodo auxiliar de DTO la do service
        obj = service.insert(obj);       //RequestBody serve pra transformar o JSON em objeto JAVA
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
        //usamos URI para o java retornar do banco a mensagem de sucesso ou erro
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id ){
       Categoria obj = service.fromDTO(objDto);
       obj.setId(id);
       obj = service.update(obj);
       return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value="/{id}") //Estou dizendo que é um metodo DELETE
    public ResponseEntity<Void> delete(@PathVariable Integer id){
       service.delete(id);
       return ResponseEntity.noContent().build();

    }
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<Categoria> list = service.findAll();
        List<CategoriaDTO> listDto = list.stream().map(CategoriaDTO::new).collect(Collectors.toList());
        //estamos usando stream e map para percorrer e tranformar uma lista de objetos em outra lista
        //a nova lista porem é o DTO de objeto que só pussui id e nome, sem os produtos
        //PS estamos utilizando arrow function para isso
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value="/page")
    public ResponseEntity<Page<CategoriaDTO>> findPage(
         @RequestParam(value = "page", defaultValue = "0") Integer page,
         @RequestParam(value = "linesPerPage", defaultValue = "24")   Integer linesPerPage,
         @RequestParam(value = "orderBy", defaultValue = "nome")   String orderBy,
         @RequestParam(value = "direction", defaultValue = "ASC")   String direction) {
        Page<Categoria> list = service.findPage(page,linesPerPage,orderBy,direction);
        Page<CategoriaDTO> listDto = list.map(CategoriaDTO::new);
        return ResponseEntity.ok().body(listDto);
        // Aqui estamos usando os parâmetros de paginação criados em CategoriaService
    }
}

