package com.kaiyu.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
/**
 * @author zh
 * @ClassName cn.saytime.config.CORSConfiguration
 * @Description
 */
@Configuration
public class CORSConfiguration implements WebMvcConfigurer{


	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
				.allowedMethods("GET", "POST", "DELETE", "PUT")
				.allowCredentials(false)
				.maxAge(3600);
	}

}