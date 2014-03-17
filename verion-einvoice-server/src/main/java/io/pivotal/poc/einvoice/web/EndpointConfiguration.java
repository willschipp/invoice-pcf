package io.pivotal.poc.einvoice.web;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.context.embedded.MultiPartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointConfiguration {

	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultiPartConfigFactory factory = new MultiPartConfigFactory();
		factory.setMaxFileSize("1MB");
		factory.setMaxRequestSize("1MB");
		//return
		return factory.createMultipartConfig();
	}
	
}
