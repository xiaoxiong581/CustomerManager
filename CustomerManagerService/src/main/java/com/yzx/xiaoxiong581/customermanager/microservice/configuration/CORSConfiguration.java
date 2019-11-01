package com.yzx.xiaoxiong581.customermanager.microservice.configuration;

import com.yzx.xiaoxiong581.customermanager.microservice.interceptor.CORSInterceptor;
import com.yzx.xiaoxiong581.customermanager.microservice.interceptor.OptionMethodInterceptor;
import com.yzx.xiaoxiong581.customermanager.microservice.interceptor.UserAuthInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author xiaoxiong581
 */
@Slf4j
@Configuration
public class CORSConfiguration extends WebMvcConfigurationSupport {
    @Value("${version:v1}")
    private String version;

    @Autowired
    private CORSInterceptor corsInterceptor;

    @Autowired
    private OptionMethodInterceptor optionMethodInterceptor;

    @Autowired
    private UserAuthInterceptor userAuthInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        String pathPattern = "/" + version + "/**";
        registry.addInterceptor(corsInterceptor).addPathPatterns(pathPattern);
        log.info("add cors interceptor success");

        registry.addInterceptor(optionMethodInterceptor).addPathPatterns(pathPattern);
        log.info("add option method interceptor success");

        registry.addInterceptor(userAuthInterceptor).addPathPatterns(pathPattern);
        log.info("add user auth interceptor success");

        super.addInterceptors(registry);
    }

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
