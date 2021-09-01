package com.projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class SouthSystApplication {

	public static void main(String[] args) {
		SpringApplication.run(SouthSystApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.projeto"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(this.metaInfo());
	}

	private ApiInfo metaInfo() {
		return new ApiInfoBuilder()
				.title("Desafio tecnico")
				.description("ApiREST para tomadas de decisões de uma assembleia")
				.contact(new Contact("Rafael Souza", "https://github.com/RafaelSoec", "rafaelsoec@gmail.com"))
				.version("1.0.0")
				.build();

	}
}
