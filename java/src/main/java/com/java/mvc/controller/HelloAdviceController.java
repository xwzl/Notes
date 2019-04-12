package com.java.mvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ControllerAdvice，是Spring3.2提供的新注解，从名字上可以看出大体意思是控制器增强。
 * <p>
 * 没什么特别之处，该注解使用@Component注解，这样的话当我们使用<context:component-scan>扫描时也能扫描到。
 * <p>
 * 大致意思是：
 * <ul>
 * <li>@ControllerAdvice是一个@Component，用于定义@ExceptionHandler，@InitBinder和@ModelAttribute方法，
 * 适用于所有使用@RequestMapping方法。</li>
 * <li>Spring4之前，@ControllerAdvice在同一调度的Servlet中协助所有控制器。Spring4已经改变：@ControllerAdvice
 * 支持配置控制器的子集，而默认的行为仍然可以利用。</li>
 * <li>在Spring4中， @ControllerAdvice通过annotations(), basePackageClasses(), basePackages()方法定制
 * 用于选择控制器子集。</li>
 * </ul>
 * <p>
 * 不过据经验之谈，只有配合@ExceptionHandler最有用，其它两个不常用。
 * <h2> @ControllerAdvice用来处理异常</h2>
 * 在@ExceptionHandler和@ResponseStatus我们提到，如果单使用@ExceptionHandler，只能在当前Controller中处理异常。
 * 但当配合@ControllerAdvice一起使用的时候，就可以摆脱那个限制了。
 *
 * @author xuweizhi
 * @date 2019/04/01 14:48
 */
@RestControllerAdvice
public class HelloAdviceController {


    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "#{e.printStackTrace()}")
    public String handleArrayIndexOutOfBoundsException(Exception e) {
        e.printStackTrace();
        return "testArrayIndexOutOfBoundsException";
    }

}
