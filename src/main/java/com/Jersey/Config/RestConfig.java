package com.Jersey.Config;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.Jersey.Resource.EmployeeResource;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import springfox.documentation.swagger.web.InMemorySwaggerResourcesProvider;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//It declares one or more @Bean methods and may be processed by the Spring container
//to generate bean
@Configuration
// It generate the REST API documents for RESTful web services.
@EnableSwagger2
//Identifies the application path that serves as the base URI for all resource URIs
@ApplicationPath("employeeInfo/v2")
@Primary
public class RestConfig extends ResourceConfig implements SwaggerResourcesProvider {
	
	@Resource
    private InMemorySwaggerResourcesProvider inMemorySwaggerResourcesProvider;
	
	public RestConfig() {
		this.registerEndpoints();
	}

	// in a constructor, the injection of the dependencies has not yet occurred
	// it gets executed after the spring bean is initialized.
	@PostConstruct
	public void init() {
		// Register components where DI is needed
		this.SwaggerConfig();
	}

	private void registerEndpoints() {
		this.register(EmployeeResource.class);
		
	}

	private void SwaggerConfig() {
		// getting the resource of Path value in the form of swagger type json/yaml
		register(ApiListingResource.class);
		// provide the produces value in application/json ,application/xml
		register(SwaggerSerializers.class);

		// BeanConfig to auto-scan all the REST resources so the documentation
		// got generated automatically
		BeanConfig swaggerConfigBean = new BeanConfig();
		swaggerConfigBean.setTitle("Using Swagger ,Jersey And Spring Boot ");
		// Sets the version of the API.
		swaggerConfigBean.setVersion("v2");
		// Sets the contact information for the application.
		swaggerConfigBean.setContact("JerseyAdmin");
		// Sets the schemes for the API URLs (http, https).
		swaggerConfigBean.setSchemes(new String[] { "http", "https" });
		swaggerConfigBean.setBasePath("employeeInfo/v2");
		swaggerConfigBean.setResourcePackage("com.Jersey.Resource");
		// When set to true, Swagger will build the documentation.
		swaggerConfigBean.setPrettyPrint(true);
		// Sets whether the swagger.json will be pretty printed.
		swaggerConfigBean.setScan(true);

	}

	@Override
	public List<SwaggerResource> get() {
		   SwaggerResource jerseySwaggerResource = new SwaggerResource();
	        jerseySwaggerResource.setLocation("/employeeInfo/v2/swagger.json");
	        jerseySwaggerResource.setSwaggerVersion("2.0");
	        jerseySwaggerResource.setName("Jersey");

	        return Stream.concat(Stream.of(jerseySwaggerResource),
	                inMemorySwaggerResourcesProvider.get().stream()).collect(Collectors.toList());
	    }
	}




