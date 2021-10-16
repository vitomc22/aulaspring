package com.vitao.aulaspring.resources;


import com.vitao.aulaspring.domain.Cliente;
import com.vitao.aulaspring.dto.ClienteDTO;
import com.vitao.aulaspring.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController                               //anotação para tranformar a classe em uma classe rest
@RequestMapping(value = "/clientes")         // anotação para informar o endpoint do rest

public class ClienteResources {

   @Autowired //auto instaciar service
    private ClienteService service; //estamos acessado o serviço criado em Cliente Service

   // Para essa função ser uma função REST =, preciso associar um verbo HTTP "GET"
   //adicionamos no construtor de requestmapping o parametro value = "id" para passar como argumento no end point
   @RequestMapping(value="/{id}" , method = RequestMethod.GET) //Estou dizendo que é um metodo GET
    public ResponseEntity<Cliente> find(@PathVariable Integer id){ // trocamos o nome da função e colocamos a notação @PathVariable
    Cliente obj = service.find(id);
    return ResponseEntity.ok().body(obj);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id ){
        Cliente obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }
    @RequestMapping(value="/{id}" , method = RequestMethod.DELETE) //Estou dizendo que é um metodo DELETE
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();

    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> list = service.findAll();
        List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value="/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24")   Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome")   String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC")   String direction) {
        Page<Cliente> list = service.findPage(page,linesPerPage,orderBy,direction);
        Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
        return ResponseEntity.ok().body(listDto);
        // Aqui estamos usando os parâmetros de paginação criados em ClienteService
    }
    
    
}
