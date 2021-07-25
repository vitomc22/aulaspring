package com.vitao.aulaspring.domain;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Estado  implements Serializable {  //Serializable serve para tranformar os objetos da subclasse Categoria em sequencia de Bytes
    // Uma vez em bytes esses objetos podem ser gravados em arquivo , trafegar em redes e etc

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @JsonBackReference //Proteção contra serialização ciclica, quem vai buscar Estado é a cidade e nao o contrário
    @OneToMany(mappedBy = "estado") //estamos dizendo que a regra de associação está no domínio estado
                                    // não é necessário reescrever associação
                                    //Estado domina várias cidades
    private List<Cidade> cidades = new ArrayList<>(); //relação, um Estado tem várias Cidades

    public Estado (){
    }

    public Estado(Integer id, String nome) {
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

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
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
        Estado other = (Estado) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

   
    
   
    
}
