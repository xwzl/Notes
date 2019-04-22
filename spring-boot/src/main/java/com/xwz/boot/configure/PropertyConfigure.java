package com.xwz.boot.configure;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 配置文件注入
 * <p>
 * 必须加@Component 和 @EnableAutoConfiguration注解
 *
 * @author xuweizhi
 * @date 2019/04/22 16:02
 */
@Component
@Configuration
@EnableAutoConfiguration
public class PropertyConfigure {


}
