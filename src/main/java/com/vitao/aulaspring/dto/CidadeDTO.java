package com.vitao.aulaspring.dto;

import com.vitao.aulaspring.domain.Cidade;

public class CidadeDTO {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;

    public CidadeDTO() {
    }

    public CidadeDTO(Cidade obj) {
        id = obj.getId();
        nome = obj.getNome();
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
}
