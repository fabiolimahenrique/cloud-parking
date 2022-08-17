package com.fabiolima.parking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// url - http://localhost:8080/swagger-ui/index.html

@EnableWebMvc
@EnableSwagger2
@Component
public class SwaggerConfig {

    @Bean
    public Docket getDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fabiolima.parking"))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
       return new ApiInfoBuilder()
               .title("Parking REST API")
               .description("Spring Boot REST Api for Parking")
               .version("1.0.0")
               .license("Apache License version 2.0")
               .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
               .build();
    }

}
