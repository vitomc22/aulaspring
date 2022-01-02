package com.vitao.aulaspring.services;

import javax.validation.constraints.Null;

import com.vitao.aulaspring.domain.Cliente;
import com.vitao.aulaspring.repositories.ClienteRepository;
import com.vitao.aulaspring.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private ClienteRepository clienteRepository;
    
    public void sendNewPassword(String email){

        Cliente cliente = new clienteRepository.findByEmail(email);
        if (cliente == null){
            
            throw new ObjectNotFoundException("Emal nãi encontrado");
        }


    }
    
}
