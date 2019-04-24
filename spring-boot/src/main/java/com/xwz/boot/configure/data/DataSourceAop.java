package com.xwz.boot.configure.data;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Order(-1) 保证该AOP在@Transactional之前执行
 * <p>
 * 创建DataSourceAop,切换数据源
 *
 * @author xuweizhi
 * @date 2019/04/24 16:28
 */
@Aspect
@Order(-1)
@Component
public class DataSourceAop {

    private static Logger logger = LoggerFactory.getLogger(DataSourceAop.class);

    @Before("execution(* com.xwz.boot.mapper..*.select*(..)) || execution(* com.xwz.boot.mapper..*.get*(..))|| execution(* com.xwz.boot.mapper..*.query*(..))")
    public void setReadDataSourceType() {
        DataSourceContextHolder.read();
        logger.info("dataSource 切换到：Read");
    }

    @Before("execution(* com.xwz.boot.mapper..*.add*(..)) || execution(* com.xwz.boot.mapper..*.update*(..))")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.write();
        logger.info("dataSource 切换到：Write");
    }

}
