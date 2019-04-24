package com.xwz.boot.configure.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 创建MyAbstractRoutingDataSource，多数据源切换
 *
 * @author xuweizhi
 * @date 2019/04/24 20:07
 */
@Configuration
@EnableTransactionManagement
public class DataSourceTransactionManager extends DataSourceTransactionManagerAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(DataSourceContextHolder.class);

    /**
     * 自定义事务
     *
     * MyBatis自动参与到spring事务管理中，无需额外配置，只要org.mybatis.spring.SqlSessionFactoryBean引用的
     * 数据源与DataSourceTransactionManager引用的数据源一致即可，否则事务管理会不起作用。
     */
    @Resource(name = "writeDataSource")
    private DataSource dataSource;

    @Bean(name = "transactionManager")
    public org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManagers() {
        logger.info("-------------------- transactionManagers init ---------------------");
        return new org.springframework.jdbc.datasource.DataSourceTransactionManager(dataSource);
    }
}
