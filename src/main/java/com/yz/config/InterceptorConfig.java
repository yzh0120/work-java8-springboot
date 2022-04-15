package com.yz.config;

import com.yz.utils.web.interceptor.HandlerInterceptorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Bean
    public HandlerInterceptorImpl handlerInterceptorImpl(){
        return new HandlerInterceptorImpl();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handlerInterceptorImpl())
                .addPathPatterns("/**");
    }
}
