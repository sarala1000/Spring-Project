package com.handson.basic.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String DEFAULT_INCLUDE_PATTERN = "/.*"; // נשמר רק לשמירה על הצורה

    @Bean
    public OpenAPI swaggerOpenApi() {
        return new OpenAPI()
                .info(apiInfo())
                .components(new Components().addSecuritySchemes("Authorization", apiKey()))
                .addSecurityItem(new SecurityRequirement().addList("Authorization", List.of("global")));
    }

    private Info apiInfo() {
        Contact contact = new Contact()
                .name("handson")
                .url("https://hansdon-academy.com")
                .email("admin@handson-academy.com");

        return new Info()
                .title("Handson API")
                .version("1.0")
                .description("API documentation")
                .contact(contact);
    }

    private SecurityScheme apiKey() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name(AUTHORIZATION_HEADER);
    }
}