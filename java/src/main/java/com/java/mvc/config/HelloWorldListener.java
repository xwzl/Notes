package com.java.mvc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

/**
 * @author xuweizhi
 * @date 2019/03/31 12:32
 */
@Slf4j
public class HelloWorldListener extends ContextLoaderListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        log.info("Web contextInitialized Initialized ...................");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        super.contextDestroyed(event);
        log.info("Web contextInitialized Initialized ...................");
    }
}
