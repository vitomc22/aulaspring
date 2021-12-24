package com.vitao.aulaspring.resources;



import com.vitao.aulaspring.domain.Cliente;
import com.vitao.aulaspring.dto.ClienteDTO;
import com.vitao.aulaspring.dto.ClienteNewDTO;
import com.vitao.aulaspring.services.ClienteService;

import org.apache.maven.wagon.authorization.AuthorizationException;
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
@RequestMapping(value = "/clientes")         // anotação para informar o endpoint do rest

public class ClienteResources {

   @Autowired //auto instaciar service
    private ClienteService service; //estamos acessado o serviço criado em Cliente Service

   // Para essa função ser uma função REST =, preciso associar um verbo HTTP "GET"
   //adicionamos no construtor de requestmapping o parametro value = "id" para passar como argumento no end point
   @GetMapping(value="/{id}") //Estou dizendo que é um metodo GET
    public ResponseEntity<Cliente> find(@PathVariable Integer id) throws AuthorizationException{ // trocamos o nome da função e colocamos a notação @PathVariable
    Cliente obj = service.find(id);
    return ResponseEntity.ok().body(obj);
    }

    
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto){ //adicionado valid para verificar os dados do POST
        Cliente obj = service.fromDTO(objDto);//aqui buscamos o metodo auxiliar de DTO la do service
        obj = service.insert(obj);       //RequestBody serve pra transformar o JSON em objeto JAVA
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
        //usamos URI para o java retornar do banco a mensagem de sucesso ou erro
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id ) throws AuthorizationException{
        Cliente obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value="/{id}") //Estou dizendo que é um metodo DELETE
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws AuthorizationException{
        service.delete(id);
        return ResponseEntity.noContent().build();

    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> list = service.findAll();
        List<ClienteDTO> listDto = list.stream().map(ClienteDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value="/page")
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24")   Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome")   String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC")   String direction) {
        Page<Cliente> list = service.findPage(page,linesPerPage,orderBy,direction);
        Page<ClienteDTO> listDto = list.map(ClienteDTO::new);
        return ResponseEntity.ok().body(listDto);
        // Aqui estamos usando os parâmetros de paginação criados em ClienteService
    }
    
    
}
