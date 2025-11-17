package com.system.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Sistema Personal-Proveedor API",
        version = "2.1.0",
        description = "Documentación de la API para gestión de personal y proveedores.",
        license = @License(
            name = "MIT",
            url = "https://opensource.org/licenses/MIT"
        )
    ),
    externalDocs = @ExternalDocumentation(
        description = "Sistema en producción",
        url = "https://sistema-personal-proveedor.vercel.app"
    )
)
public class OpenApiConfig {
}