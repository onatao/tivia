package com.neidev.tivia.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI ApiConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("Desafio TiviaTI - Nathan Barros")
                        .description("Desafio desenvolvido para vaga de Desenvolvedor Java")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Nathan Barros LinkedIn")
                                .url("https://www.linkedin.com/in/barros-nathan/"))
                        .license(new License()
                                .name("Nathan Barros GitHub")
                                .url("https://github.com/onatao"))
                );
    }

}
