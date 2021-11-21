package com.vitao.aulaspring.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitao.aulaspring.domain.PagamentoComBoleto;
import com.vitao.aulaspring.domain.PagamentoComCartao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {
// https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-of-interfaceclass-without-hinting-the-pare
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(PagamentoComCartao.class);
                objectMapper.registerSubtypes(PagamentoComBoleto.class);
                super.configure(objectMapper);
            }
        };
        return builder;
    }
}
//Basicamente uma classe de configuração jackson permite que usando a anotação @configuration
//mapeamos subclasses dentro de uma interface (Superclasse), facilitando o uso condicional
//nesse exemplo, dependendo do @type, o pagamento pode ser boleto ou cartao, duas classes diferentes mapeados via jackson