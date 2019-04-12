package com.java.mvc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * ResponseBodyAdvice是spring4.1的新特性，其作用是在响应体写出之前做一些处理；比如，修改返回值、加密等。
 * <p>
 * 我在项目中的用到@ControllerAdvice,ResponseBodyAdvice的目的，是为每个请求的返回json中修改一个属性值。
 *
 * @author xuweizhi
 * @date 2019/04/01 22:02
 */
@ControllerAdvice
@Slf4j
public class MyResponseBodyAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // 获取当前处理请求的controller的方法
        String methodName = returnType.getMethod().getName();
        // 不拦截/不需要处理返回值 的方法
        // 如登录
        String method = "index";
        //不拦截
        return !method.equals(methodName);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        //通过 ServerHttpRequest的实现类ServletServerHttpRequest 获得HttpServletRequest
        //ServletServerHttpRequest sshr=(ServletServerHttpRequest) serverHttpRequest;
        //此处获取到request 是为了取到在拦截器里面设置的一个对象 是我项目需要,可以忽略
        //HttpServletRequest request=   sshr.getServletRequest();

        //将返回值returnValue转成我需要的类型Message<?>  方便统一修改其中的某个属性
        // Messages是我自定义的一个类
        //Messages<?> msg=(Messages<?>) returnValue;
        ////统一修改返回值/响应体
        //msg.setXXX("测试修改返回值");
        ////返回修改后的值
        //return msg;
        if (body instanceof String) {
            String body1 = (String) body;
            log.info("转换已经执行了");
            return body1+"haha";
        }
        return body;
    }
}
