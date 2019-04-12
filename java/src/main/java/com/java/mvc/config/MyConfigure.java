package com.java.mvc.config;

import com.java.mvc.interceptor.HelloWorldHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xuweizhi
 * @date 2019/03/31 13:01
 */
@Configuration
public class MyConfigure implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
         registry.addInterceptor(new HelloWorldHandler()).addPathPatterns("/*");
         //registry.addInterceptor(new HelloWorldRejectHandler()).addPathPatterns("/*");
         WebMvcConfigurer.super.addInterceptors(registry);
    }
}
