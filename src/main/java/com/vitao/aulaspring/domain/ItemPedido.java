package com.vitao.aulaspring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ItemPedido implements Serializable {  //Serializable serve para tranformar os objetos da subclasse Categoria em sequencia de Bytes
    // Uma vez em bytes esses objetos podem ser gravados em arquivo , trafegar em redes e etc
    private static final long serialVersionUID = 1L;
    //aqui que mora a mágica, usamos a classe auxiliar composta por pedido e produto
    //atribuimos nossa chave primaria como ItemPedidoPK();
    @EmbeddedId // notação usada para ID composto, ou seja um ID embutido em tipo auxiliar
    private ItemPedidoPK id = new ItemPedidoPK(); // esse id vira um atributo composto, como se fosse tipo primitivo

    private Double desconto;
    private Integer quantidade;
    private Double preco;

    public  ItemPedido(){

    }

    public ItemPedido(Pedido pedido,Produto produto, Double desconto, Integer quantidade, Double preco) {
        id.setPedido(pedido);
        id.setProduto(produto);       // aqui nao faz sentido a implementação de ItemPedidoPK no construtor, entao usamos separadamente produto e pedido ID
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public double getSubTotal(){
        return (preco - desconto) * quantidade;
    }

    @JsonIgnore
    public Pedido getPedido(){
        return id.getPedido();
    }                                  //declaramos separadamente para acessar separadamente os ID sem usar o ItemPedidoPK

    public void setPedido(Pedido pedido) {
     id.setPedido(pedido);
    }

    public Produto getProduto(){
        return id.getProduto();
    }

    public void setProduto(Produto produto){
        id.setProduto(produto);
    }

    public ItemPedidoPK getId() {
        return id;
    }

    public void setId(ItemPedidoPK id) {
        this.id = id;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemPedido)) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
