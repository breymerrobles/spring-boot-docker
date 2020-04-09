package com.globant.springboot.example.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api").apiInfo(apiInfo()).select()
				.paths(servicesPaths()).build();
	}

	private Predicate<String> servicesPaths() {
//		return regex("/.*");
		return regex("/user/*");
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Globant Test API").description("Globant Test reference for developers")
				.contact(new Contact("Breymer Robles", "www.globant.com", "breymer.robles@globant.com"))
				.license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
				.termsOfServiceUrl("http://www.apache.org").license("Globant License")
				.licenseUrl("breymer.robles@globant.com").version("1.0").build();
	}

}
