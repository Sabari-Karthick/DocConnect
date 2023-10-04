package com.Batman.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	@Bean
	public Docket createDocket() {
		
		return new Docket(DocumentationType.SWAGGER_2).
	                  select().
	                  apis(RequestHandlerSelectors.basePackage("com.Batman.restcontroller")).
	                  paths(PathSelectors.regex("/api/.*")).
	                  build().
	                  useDefaultResponseMessages(true).apiInfo(getAPIInfo());
	
	}
	
	
	private ApiInfo getAPIInfo() {
		return new ApiInfo("Doctor", "DocConnect- A platform to connect patients and doctors", "1.0 Release", "http:", new Contact("Karthick", "http://Zack/DocConnect", "sabriks2063@gmail.com"), "Wayne Enterprise", "license.com", Collections.emptyList());
	}
	

}
