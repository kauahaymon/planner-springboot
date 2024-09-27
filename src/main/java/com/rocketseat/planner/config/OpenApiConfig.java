package com.rocketseat.planner.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Planner Rest API",
                version = "1.0.0",
                description = "Here you can find some endpoints to interact with the travel organizer application.",
                license = @License(name = "Apache License 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0")
        )
        // ,servers = @Server(url = "http://localhost:8080", description = "Servidor Local")
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenApiConfig {
}