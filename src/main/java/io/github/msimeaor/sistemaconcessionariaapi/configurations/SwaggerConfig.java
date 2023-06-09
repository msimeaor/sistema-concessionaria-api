package io.github.msimeaor.sistemaconcessionariaapi.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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

}
