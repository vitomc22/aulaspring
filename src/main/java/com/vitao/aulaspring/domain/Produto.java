package com.vitao.aulaspring.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


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

    @JsonIgnore
    @OneToMany(mappedBy = "id.produto")
    private Set<ItemPedido> itens = new HashSet<>(); //implementação hashSet para nao permitir valores duplicados

    public Produto(){
    }

    public Produto(Integer id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    @JsonIgnore
    public List<Pedido> getPedidos() {
        List<Pedido> lista = new ArrayList<>();
        for (ItemPedido x : itens) {
            lista.add(x.getPedido());
        //percorre a lista de itens dos pedidos e atribui a cada elemento da lista o id pedido associado
        }
        return lista;
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

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
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
