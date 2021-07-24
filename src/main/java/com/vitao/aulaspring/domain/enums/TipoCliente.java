package com.vitao.aulaspring.domain.enums;
//Tipo ENUM Serve para atribuir um valor numerico para cada valor de nome 
public enum TipoCliente {
    
    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "Pessoa Jurídica");
    
    private int cod;
    private String descricao;
    //Precisa de construtor também
    private TipoCliente(int cod, String descricao){

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
    public static TipoCliente toEnum(Integer cod){

        if (cod == null) {
            return null;
        }
        //fazr uma varredura pra ver se o cod que recebemos
        //é correspondente a um que temos disponível 
        for (TipoCliente x : TipoCliente.values()){
            if (cod.equals(x.getCod())) {
                return x;                
            }
        }
        //caso tudo isso dê errado, lança exeção
        throw new IllegalArgumentException("Id inválido: " + cod);

    }    
}
