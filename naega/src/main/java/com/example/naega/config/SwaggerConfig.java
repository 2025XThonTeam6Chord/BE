package com.example.naega.config;

import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("내 프로젝트 API 문서")
                        .description("API 명세서입니다.")
                        .version("1.0.0"));
    }

    @Bean
    public OpenApiCustomizer globalHeaderAdder() {
        return openApi -> openApi.getPaths().values().forEach(pathItem ->
                pathItem.readOperations().forEach(operation ->
                        operation.addParametersItem(
                                new Parameter()
                                        .in("header")
                                        .schema(new StringSchema())
                                        .name("X-USER-ID")
                                        .description("유저 ID 헤더")
                        )
                )
        );
    }
}
