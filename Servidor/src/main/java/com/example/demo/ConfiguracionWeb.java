package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfiguracionWeb implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    String uploadDir = System.getProperty("user.dir") + "/uploads/img/";
	    
	    registry.addResourceHandler("/img/**")
	            .addResourceLocations("file:" + uploadDir);
	}

}