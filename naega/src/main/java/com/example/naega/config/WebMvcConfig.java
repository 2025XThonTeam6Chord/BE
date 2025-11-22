package com.example.naega.config;

import com.example.naega.common.GlobalHeaderInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final GlobalHeaderInterceptor globalHeaderInterceptor;

    public WebMvcConfig(GlobalHeaderInterceptor globalHeaderInterceptor) {
        this.globalHeaderInterceptor = globalHeaderInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globalHeaderInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/swagger-ui/**",
                        "/v3/api-docs/**"
                );
    }

}
