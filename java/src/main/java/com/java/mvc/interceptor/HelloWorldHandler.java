package com.java.mvc.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * @author xuweizhi
 * @date 2019/03/31 12:35
 */
@Component
@Slf4j
public class HelloWorldHandler implements HandlerInterceptor {

    /**
     * 前置拦截器，最先处理请求，无论返回true还是false{@link #afterCompletion}一定会被处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle方法在%s被执行", LocalDateTime.now().toString());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle方法在%s被执行", LocalDateTime.now().toString());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion方法在%s被执行", LocalDateTime.now().toString());
    }
}
