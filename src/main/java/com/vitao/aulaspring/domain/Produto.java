package com.vitao.aulaspring.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity //Mapeando os atributos para jpa

public class Produto implements Serializable {  //Serializable serve para tranformar os objetos da subclasse Categoria em sequencia de Bytes
    // Uma vez em bytes esses objetos podem ser gravados em arquivo , trafegar em redes e etc

private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double preco;


    @JsonBackReference // Aqui nao queremos carregar todos os objetos, por isso Back reference
    @ManyToMany //estamos dizendo que o relacionamento de produto com categoria é de muitos para muitos
    @JoinTable(  // e aqui vamos dizer qual será a tabela que irá relacionar as duas classes
      name = "PRODUTO_CATEGORIA",
         joinColumns = @JoinColumn(name = "produto_id"), //aqui estou dizendo qual atributo da tabela será a minha chave estrangeira para Categoria
         inverseJoinColumns = @JoinColumn(name = "categoria_id") //aqui estou dizendo qual atributo da tabela será a minha chave estrangeira para Produto 
    )                                                    //OU seja , Exatamente o contrário "Inverse"
    
    private List<Categoria> categorias = new ArrayList<>(); //Associação de produtos com categoria

    public Produto(){
    }

    public Produto(Integer id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Produto other = (Produto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    

}
