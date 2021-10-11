package com.vitao.aulaspring.domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
public class Categoria implements Serializable {  //Serializable serve para tranformar os objetos da subclasse Categoria em sequencia de Bytes
                                                // Uma vez em bytes esses objetos podem ser gravados em arquivo , trafegar em redes e etc

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório!") //anotação para validar dados de entrada
    @Size(min = 5, max = 80, message= "o tamanho deve ser entre 5 e 80 caracteres")
    private String nome;



    @ManyToMany(mappedBy = "categorias") //como ja mapeamos o relacionamento de tabelas dentro da classe Categoria
                                         //utilizamos o mappedBy para informar a regra que estamos utilizando 
                                         //assim reaproveitando código sem rescrever a relação toda novamente
    //private List<Categoria> categorias = new ArrayList<>(); //Associação de produtos com categoria                                   
    
    
    private List<Produto> produtos = new ArrayList<>(); //criamos uma associação de Categoria com produtos
                                                        //Uma categoria pode ter vários produtos

    
    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Categoria (){

    }

    public Categoria(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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
            //gerado via botao direito source action Hashcode and equals
    @Override //nessa pedaço de code estou gerando um Hashcode and equals que serve para comparar o conteúdo dos objetos, nesse caso somente para atributo ID
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
        Categoria other = (Categoria) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    


    
}
