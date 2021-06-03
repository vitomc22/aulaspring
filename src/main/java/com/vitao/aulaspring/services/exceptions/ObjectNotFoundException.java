package com.vitao.aulaspring.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException(String  msg) {
        super(msg);

    }
    public ObjectNotFoundException(String msg, Throwable cause) {
        super(msg,cause);

    }
}
//Estamos criando uma classe de Exception aproveitando da classe padrão do java
//O super() serve para chamar o construtor da superclasse. 
//Ele sempre é chamado, mesmo quando não está explícito no código, quando for explicitado deve ser o primeiro item dentro do construtor.