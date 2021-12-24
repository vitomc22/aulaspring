package com.vitao.aulaspring.services;

import com.vitao.aulaspring.security.UserSS;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

   private UserService() {
    }

    public static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // retorna o user
                                                                                                   // logado
        } catch (Exception e) {
            return null;
        }
    }

}
