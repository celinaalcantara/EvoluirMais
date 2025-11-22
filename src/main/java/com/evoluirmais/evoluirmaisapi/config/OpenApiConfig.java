package com.evoluirmais.evoluirmaisapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class OpenApiConfig {

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme()
                .name("bearerAuth")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");
    }

    @Bean
    public OpenAPI customOpenAPI() {
        SecurityRequirement securityRequirement = new SecurityRequirement();

        securityRequirement.addList("bearerAuth", new ArrayList<>());

        return new OpenAPI()
                .info(new Info()
                        .title("EvoluirMais API - Trilha de Conhecimento")
                        .version("v0.0.1")
                        .description("API RESTful para gestão da Trilha de Conhecimento e usuários.")
                )
                .components(new io.swagger.v3.oas.models.Components().addSecuritySchemes("bearerAuth", createSecurityScheme()))

                .addSecurityItem(securityRequirement);
    }
}

