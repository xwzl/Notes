package com.xwz.boot.property;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 获取Redis
 *
 * @author xuweizhi
 */
@EnableConfigurationProperties({RedisProperties.class})
@Component
public class ReadProperties {


    private RedisProperties redisProperties;

    public ReadProperties(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    public void read() {
        System.out.println(redisProperties);
    }

}