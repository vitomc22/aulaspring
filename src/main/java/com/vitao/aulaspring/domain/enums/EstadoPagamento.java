package com.vitao.aulaspring.domain.enums;

public enum EstadoPagamento {

    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private int cod;
    private String descricao;
    //Precisa de construtor também
    private EstadoPagamento(int cod, String descricao){

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
    public static EstadoPagamento toEnum(Integer cod){

        if (cod == null) {
            return null;
        }
        //fazr uma varredura pra ver se o cod que recebemos
        //é correspondente a um que temos disponível
        for (EstadoPagamento x : EstadoPagamento.values()){
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        //caso tudo isso dê errado, lança exeção
        throw new IllegalArgumentException("Id inválido: " + cod);

    }
}
