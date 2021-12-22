package br.com.sicred.controller.v1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;


@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.sicred"))
                .paths(regex("/.*"))
                .build()
                .useDefaultResponseMessages(false)
                .pathMapping("/")
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("vote-service")
                .description("Service to manage vote sistem")
                .version("v1")
                .contact(this.contact())
                .build();
    }

    private Contact contact() {
        return new Contact(
                "William Lima",
                "",
                "william.limagm@hotmail.com");
    }
}
