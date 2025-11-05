package com.gruposalinas.prueba_armando.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.UUID;

@Configuration
public class OpenApiConfig {

    static {
        // Configurar UUID como string en OpenAPI
        SpringDocUtils.getConfig().replaceWithClass(UUID.class, String.class);
        // Configurar LocalDate como string en OpenAPI
        SpringDocUtils.getConfig().replaceWithClass(LocalDate.class, String.class);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API de Gestión de Prospectos de Empleo")
                .version("1.0.0")
                .description("API REST para gestionar prospectos de empleo en la consultora de Capital Humano. " +
                    "Permite crear, actualizar, buscar, listar y eliminar prospectos con sus datos personales, " +
                    "domicilio e historial de empleos."));
    }
}

