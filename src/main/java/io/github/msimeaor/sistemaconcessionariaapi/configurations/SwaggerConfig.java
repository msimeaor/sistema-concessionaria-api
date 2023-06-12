package io.github.msimeaor.sistemaconcessionariaapi.configurations;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2)
      .useDefaultResponseMessages(false)
      .select()
        .apis(RequestHandlerSelectors.basePackage("io.github.msimeaor.sistemaconcessionariaapi.restcontroller"))
        .paths(PathSelectors.any())
      .build()
      .securityContexts(Arrays.asList(securityContext()))
      .securitySchemes(Arrays.asList(apiKey()))
      .apiInfo(apiInfo());
  }

  private Contact contact() {
    return new Contact(
      "GRUPO 3 - POO em Java",
      "https://github.com/msimeaor/sistema-concessionaria-api",
      ""
    );
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
      .title("SISTEMA CONCESSIONÁRIA")
      .description("SISTEMA DE CONTROLE DE ESTOQUE E VENDAS")
      .version("1.0.0")
      .contact(contact())
      .build();
  }

  private ApiKey apiKey() {
    return new ApiKey("JWT", "Authorization", "header");
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder()
      .securityReferences(defaultAuth())
      .forPaths(PathSelectors.any())
      .build();
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope =
      new AuthorizationScope("global", "acessoGeral");
      AuthorizationScope[] scopes = new AuthorizationScope[1];
      scopes[0] = authorizationScope;
      SecurityReference reference = new SecurityReference("JWT", scopes);
      List<SecurityReference> auths = new ArrayList<>();
      auths.add(reference);
      return auths;
  }

}
