package com.korit.springboot_study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
// http://localhost:8080/swagger-ui/index.html 로 들어가면 Swagger 홈페이지가 열리고 프론트 적용가능
// http://localhost:8080/v2/api-docs 들어가서 모두 복사해서 postman 에 import 넣으면 따로 입력없이 사용 가능

    @Bean
    // @Bean : Docket 객체를 생성하고, Swagger에서 사용할 API의 설정을 지정
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                // apis -> 해당 패키지 안에 있는 모든 컨트롤러만 Swagger를 적용
                .apis(RequestHandlerSelectors.basePackage("com.korit.springboot_study.controller"))
                // 모든 URL 스웨어 적용
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                .securitySchemes(Arrays.asList(getApiKey()))
                .securityContexts(Arrays.asList(getSecurityContext()));
    }


    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("API 문서 제목")
                .description("API 문서 설명")
                .version("1.0")
                .contact(new Contact("관리자", "주소", "이메일"))
                .build();
    }


    private ApiKey getApiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext getSecurityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }
}