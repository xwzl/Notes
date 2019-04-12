package com.java.mvc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xuweizhi
 * @date 2019/03/31 13:06
 */
@Configuration
@Slf4j
public class MyServletConfigure {

    //注册三大组件
    @Bean
    public ServletRegistrationBean myServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                super.doGet(req, resp);
                log.info("自定义servlet非注解版");
            }
        }, "/myServlet");
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new HttpFilter() {
            @Override
            protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
                log.info("非注解版拦截器拦截开始------------------------");
                chain.doFilter(request, response);
            }


        });
        //*registrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));*//*
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    //@Bean
    //public ServletListenerRegistrationBean myListener() {
    //    ServletListenerRegistrationBean<HelloWorldListener> registrationBean = new ServletListenerRegistrationBean<>(new HelloWorldListener());
    //    return registrationBean;
    //}
}
