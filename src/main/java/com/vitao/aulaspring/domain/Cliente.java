package com.vitao.aulaspring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vitao.aulaspring.domain.enums.Perfil;
import com.vitao.aulaspring.domain.enums.TipoCliente;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Cliente implements Serializable { // Serializable serve para tranformar os objetos da subclasse Categoria
    // em sequencia de Bytes
    // Uma vez em bytes esses objetos podem ser gravados em arquivo , trafegar em
    // redes e etc

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @Column(unique = true) // valor único nesse campo
    private String email;
    private String cpfOuCnpj;
    private Integer tipo;

    @JsonIgnore
    private String senha;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL) /* Mapped By Client Class */
    private List<Endereco> enderecos = new ArrayList<>();

    @ElementCollection // essa notação permite o mapeamento de uma entidade fraca no banco
    @CollectionTable(name = "TELEFONE") // aqui marcamos a tabela correspondente no banco
    private Set<String> telefones = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER) // essa notação permite o mapeamento de uma entidade fraca no banco
    @CollectionTable(name = "PERFIS") // aqui marcamos a tabela correspondente no banco
    private final Set<Integer> perfis = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();


    public Cliente() {
        addPerfil(Perfil.CLIENTE);

    }


    public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = (tipo == null) ? null : tipo.getCod();// ternario para verificar se vem valor null ou nao
        this.senha = senha;
        addPerfil(Perfil.CLIENTE);
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

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());

    }

    public void addPerfil(Perfil perfil) {
        perfis.add(perfil.getCod());
    }

    public TipoCliente getTipo() {
        return TipoCliente.toEnum(tipo);
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo.getCod();
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
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
        Cliente other = (Cliente) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }

}
