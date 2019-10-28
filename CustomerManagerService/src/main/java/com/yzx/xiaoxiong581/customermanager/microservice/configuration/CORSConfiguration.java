package com.yzx.xiaoxiong581.customermanager.microservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author xiaoxiong581
 */
@Configuration
public class CORSConfiguration extends WebMvcConfigurationSupport {
    @Value("${version:v1}")
    private String version;

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/" + version + "/**")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowCredentials(true);
        super.addCorsMappings(registry);
    }
}
