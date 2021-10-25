package com.multiventas.app;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;

import springfox.documentation.service.SecurityReference;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
	public static final String PATH = "/v2/api-docs";
	 public static final String AUTHORIZATION_HEADER = Constants.AUTHORIZATION_HEADER;
	    public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";
	
	 @Bean
	    public Docket api() { 
	        Docket api = new Docket(DocumentationType.SWAGGER_2)  
	        		    .securityContexts(Lists.newArrayList(securityContext()))
	                     .securitySchemes(Lists.newArrayList(apiKey()));
	        		
	        		
	        	   api = api.select()                                  
	  	          .apis(RequestHandlerSelectors.basePackage("com.multiventas.app.controller"))          
		          .paths(PathSelectors.any())  
		          .build(); 
	        	   
	       return api;
	    }
	 



	private ApiKey apiKey() {
	        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	    }

	    private SecurityContext securityContext() {
	        return SecurityContext.builder()
	            .securityReferences(defaultAuth())
	            .forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
	            .build();
	    }

	    List<SecurityReference> defaultAuth() {
	        AuthorizationScope authorizationScope
	            = new AuthorizationScope("global", "accessEverything");
	        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	        authorizationScopes[0] = authorizationScope;
	        return Lists.newArrayList(
	            new SecurityReference("JWT", authorizationScopes));
	    }
}
