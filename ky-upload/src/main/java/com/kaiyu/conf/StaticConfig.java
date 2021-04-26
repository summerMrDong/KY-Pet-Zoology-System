package com.kaiyu.conf;

import com.kaiyu.function.UploadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticConfig implements WebMvcConfigurer {

    private UploadFactory uploadFactory;

    @Autowired
    public StaticConfig(UploadFactory uploadFactory) {
        this.uploadFactory = uploadFactory;
    }

    @Value("${preview.prefix}")
    private String preview_prefix;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/".concat(preview_prefix).concat("/**"))
                .addResourceLocations("file:".concat(uploadFactory.getUploadDirectory()));
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").maxAge(3600);
    }


}