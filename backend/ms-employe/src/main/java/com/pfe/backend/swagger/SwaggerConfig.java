package com.pfe.backend.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "Welcome to Cumulus",
                version = "1.0",
                description = "cumulus",
                license = @License(name = "Apache 2.0", url = "http://foo.bar"),
                contact = @Contact(url = "http://cumulus.com", name = "Fred", email = "cum@gmail.com")
        )
)
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "bearerAuth", scheme = "bearer", in = SecuritySchemeIn.HEADER ,bearerFormat = "JWT" ,description="connect to get ur JWT :D")

public class SwaggerConfig {
    
   

}
