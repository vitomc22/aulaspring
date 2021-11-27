package com.vitao.aulaspring.config;

import java.text.ParseException;

import com.vitao.aulaspring.services.DBService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {
    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDataase() throws ParseException {
        dbService.instatiaiteTesteDatabase();

        return true;
    }

}
