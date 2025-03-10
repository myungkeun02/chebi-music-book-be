package com.example.chebimusicbookbe.infra.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        // 인증 관련 부분 주석 처리!
        // String jwt = "JWT";
        // SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwt);
        Server server = new Server();
        server.setUrl("http://localhost:8080");

        return new OpenAPI()
                // 인증 관련 부분 주석 처리!
                //.components(new Components().addSecuritySchemes(jwt, new SecurityScheme()
                //        .name(jwt)
                //        .type(SecurityScheme.Type.HTTP)
                //        .scheme("Bearer")
                //        .bearerFormat("JWT")))
                .info(apiInfo())
                // .addSecurityItem(securityRequirement)
                .servers(List.of(server));
    }

    private Info apiInfo() {
        return new Info()
                .title("Chebi MusicBook API")
                .description("Chebi MusicBook API")
                .version("1.0");
    }

}

