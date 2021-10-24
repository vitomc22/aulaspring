package com.vitao.aulaspring.dto;

import com.vitao.aulaspring.domain.Cliente;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ClienteDTO implements Serializable {  //Serializable serve para tranformar os objetos da subclasse Categoria em sequencia de Bytes
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Size(min=5,max=120, message = "Tamanho mínimo de 5 e máximo 120 caracteres!")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Email (message = "Email inválido!")
    private String email;

    public ClienteDTO(){

    }

    public ClienteDTO(Cliente obj){
        id = obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
