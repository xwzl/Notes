package com.java.boot.system.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author xuweizhi
 * @date 2018/11/15 8:59
 */
@Component
@Aspect
@Order(2)
@Slf4j
public class ServiceAop {

    /**
     * 保证每个线程都有一个单独的实例
     */
    private ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    private boolean flag = false;

    /**
     * 横切所有的cont方法
     */
    @Pointcut("execution(* com.graduation.design.service.impl.*.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) throws NoSuchMethodException {
        threadLocal.set(System.currentTimeMillis());
         //UrlControllerAop.otherLogPrint(joinPoint);
    }

    @AfterReturning("pointcut()")
    public void afterReturning(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getInterfaces()[0].getSimpleName();
        String methodName =  joinPoint.getSignature().getName() + "  耗时" + ((System.currentTimeMillis() - threadLocal.get())) + "ms";
        log.info("Service接口名称:{} 执行方法 :{}", className,methodName);
        threadLocal.remove();
    }


}
