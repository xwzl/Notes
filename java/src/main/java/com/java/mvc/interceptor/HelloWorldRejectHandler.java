package com.java.mvc.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * @author xuweizhi
 * @date 2019/03/31 12:40
 */
@Slf4j
public class HelloWorldRejectHandler implements HandlerInterceptor {

    /**
     * 前置拦截器，最先处理请求，无论返回true还是false{@link #afterCompletion}一定会被处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle1方法在%s被执行", LocalDateTime.now().toString());
        return false;
    }

    /**
     * {@link #preHandle}返回false不会被执行,
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle1方法在%s被执行", LocalDateTime.now().toString());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion1方法在%s被执行", LocalDateTime.now().toString());
    }
}
