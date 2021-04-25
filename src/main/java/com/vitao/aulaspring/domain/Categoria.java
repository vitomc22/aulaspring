package com.vitao.aulaspring.domain;

import java.io.Serializable;

public class Categoria implements Serializable {  //Serializable serve para tranformar os objetos da subclasse Categoria em sequencia de Bytes
                                                // Uma vez em bytes esses objetos podem ser gravados em arquivo , trafegar em redes e etc

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;

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
