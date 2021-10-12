package com.gualberto.ronei.rmgschoolapi.application.rest.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {


    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("RMG School API")
                        .description("RMG School Application")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("https://github.com/roneigualberto/rmg-school-webapi/LICENSE.md")))
                .externalDocs(new ExternalDocumentation()
                        .description("RMG School Documentation")
                        .url("https://github.com/roneigualberto/rmg-school-webapi"));
    }
}
