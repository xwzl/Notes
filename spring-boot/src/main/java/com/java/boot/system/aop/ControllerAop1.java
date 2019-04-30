package com.java.boot.system.aop;

/**
 * @author xuweizhi
 * @since  2018/11/13 16:38
 */
//@Aspect
//@Component
//@Order(2)
//@Slf4j
public class ControllerAop1 {

    //专业术语
    //1.连接点（JoinPoint）:对用具体的拦截对象，Spring只支持拦截方法，拦截对象是指特定的对象
    //2.切点  （point cut）:有时候需要拦截的方法，不止一个方法而是很多类的方法，这时候需要定义一系列的规则适配
    //3.通知  （advice）   :
    // 前置通知（before advice）
    // 后置通知（after advice）
    // 环绕通知（around advice）
    // 事后返回通知（afterReturning advice）
    // 异常通知（afterThrowing advice）
    //4.切面（aspect）: 是一个可以定义切点，各类通知和引入的内容。

   /* //@Pointcut("execution(public * com.web.pro.controller.*.*(..))")
    public void pointCut() {
    }

    //@Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        log.info("Before Advice={}", "前置通知1");
    }

    //@After("pointCut()")
    public void after() {
        log.info("After Advice={}", "1");
    }
*/

}
