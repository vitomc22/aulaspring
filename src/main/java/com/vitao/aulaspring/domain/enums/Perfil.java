package com.vitao.aulaspring.domain.enums;

public enum Perfil {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");

    private int cod;
    private String descricao;
    //Precisa de construtor também
    private Perfil(int cod, String descricao){

        this.cod = cod;
        this.descricao = descricao;
    }
    // precisa de get
    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }
    // aqui é uma verificação pra ter certeza que nao vai registrar null
    public static Perfil toEnum(Integer cod){

        if (cod == null) {
            return null;
        }
        //fazr uma varredura pra ver se o cod que recebemos
        //é correspondente a um que temos disponível
        for (Perfil x : Perfil.values()){
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        //caso tudo isso dê errado, lança exeção
        throw new IllegalArgumentException("Id inválido: " + cod);

    }
}
