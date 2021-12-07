package com.vitao.aulaspring.config;

import java.text.ParseException;

import com.vitao.aulaspring.services.DBService;
import com.vitao.aulaspring.services.EmailService;
import com.vitao.aulaspring.services.SmtpEmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {
    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDataase() throws ParseException {
        if (!"create".equals(strategy)){
            return false;
        }
        dbService.instatiaiteTesteDatabase();

        return true;
    }

    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();

    }

}
