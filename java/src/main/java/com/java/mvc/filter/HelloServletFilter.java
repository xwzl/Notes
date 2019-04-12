package com.java.mvc.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * SpringBoot 兼容Servlet 3.0    Filter 注解版本
 * JDK 1.8 过滤类Filter JDK 11 HttpFilter
 *
 * @author xuweizhi
 * @date 2019/03/31 12:24
 */
@WebFilter("/hello")
@Slf4j
public class HelloServletFilter extends HttpFilter {

    public HelloServletFilter() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        log.info("拦截请求初始化" + LocalDateTime.now().toString());
    }

    @Override
    public void destroy() {
        log.info("拦截请求毁灭" + LocalDateTime.now().toString());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //super.doFilter(request, response, chain);
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //super.doFilter(request, response, chain);
        log.info("拦截请求1-----------------");
        chain.doFilter(request, response);
    }
}
