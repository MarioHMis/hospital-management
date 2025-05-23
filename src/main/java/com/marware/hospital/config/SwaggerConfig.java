package com.marware.hospital.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hospital Management API")
                        .version("1.0.0")
                        .description("API documentation for the Hospital Management System")
                        .contact(new Contact()
                                .name("Marware Support")
                                .email("support@marware.com")));
    }
}
