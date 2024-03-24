package com.bloominghealth.restapiproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class RestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/product/*"))
				.apis(RequestHandlerSelectors.basePackage("com.bloominghealth.restdemo"))
				.build()
				.apiInfo(apiCustomData());
	}

	private ApiInfo apiCustomData(){
		return new ApiInfo(
				"Product API Application",
				"Product Documentation",
				"1.0",
				"Product Service Terms",
				new Contact("Prashanth R Dundigal", "https://gobloominghealth.com/",
						"prashanth.dundigal@gmail.com"),
				"Blooming Health License",
				"https://gobloominghealth.com/",
				Collections.emptyList()
		);
	}

	@Bean
	public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(
			WebEndpointsSupplier webEndpointsSupplier,
			ServletEndpointsSupplier servletEndpointsSupplier,
			ControllerEndpointsSupplier controllerEndpointsSupplier,
			EndpointMediaTypes endpointMediaTypes,
			CorsEndpointProperties corsProperties,
			WebEndpointProperties webEndpointProperties,
			Environment environment) {
		List<ExposableEndpoint<?>> allEndpoints = new ArrayList();
		Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
		allEndpoints.addAll(webEndpoints);
		allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
		allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
		String basePath = webEndpointProperties.getBasePath();
		EndpointMapping endpointMapping = new EndpointMapping(basePath);
		boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(
				webEndpointProperties, environment, basePath);
		return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints,
				endpointMediaTypes, corsProperties.toCorsConfiguration(),
				new EndpointLinksResolver(allEndpoints, basePath),
				shouldRegisterLinksMapping, null);
	}

	private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties,
											   Environment environment, String basePath) {
		return webEndpointProperties.getDiscovery().isEnabled() &&
				(StringUtils.hasText(basePath) ||
						ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
	}
}

