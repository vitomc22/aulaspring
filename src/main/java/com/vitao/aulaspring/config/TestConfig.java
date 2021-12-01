package com.vitao.aulaspring.config;

import java.text.ParseException;

import javax.validation.constraints.Email;

import com.vitao.aulaspring.services.DBService;
import com.vitao.aulaspring.services.EmailService;
import com.vitao.aulaspring.services.MockMailService;

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

    @Bean
    public EmailService emailService(){
        return new MockMailService();
    }

}
