package com.vitao.aulaspring.services;

import com.vitao.aulaspring.domain.Cliente;
import com.vitao.aulaspring.dto.ClienteDTO;
import com.vitao.aulaspring.repositories.ClienteRepository;
import com.vitao.aulaspring.services.exceptions.DataIntegrityException;
import com.vitao.aulaspring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ClienteService {
   @Autowired //anotaçao para instaciar objetos por injeção de dependencia ou inversão de controle
              //estamos instaciando o objeto repo
   private ClienteRepository repo;
   
   public Cliente find(Integer id) {
       Optional<Cliente> obj = repo.findById(id);  //esse método findOne veio la do nosso repository ClienteRepository
       return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found!! ID: "+id+", tipo:"+Cliente.class.getName()));
                                         

   }

    public Cliente update (Cliente obj){ //serviço de atualização de dados no banco
        Cliente  newObj = find(obj.getId()); //newObj recebe os dados de Cliente do JPA do banco
        updateData(newObj, obj);
        return repo.save(newObj);// novo objeto atualizado com os dados do banco e do update vindo de obj
        //com esse novo objeto newObj em conjunto com obj, atualizamos os dados novos e os campos que nao alteramos, ele mantem oque esta no banco
        //evitando atualizar os dados enviados e nullando os outros dados
    }

    public void delete(Integer id){ //serviço de exclusão de dados no banco
        find(id);
        try {
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir um Cliente que possui dependencies!! Exclua primeiro.");
        }

    }

    public List<Cliente> findAll(){
        return  repo.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        return  repo.findAll(pageRequest); ///função que retorna todas as Clientes em forma de paginação

    }
    public Cliente fromDTO(ClienteDTO objDto){
       return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null,null);
    }

    private void updateData(Cliente newObj, Cliente obj){
       newObj.setNome(obj.getNome());
       newObj.setEmail(obj.getEmail());  //classe auxiliar para buscar os dados do cliente no banco e depois atualizar
    }
}
