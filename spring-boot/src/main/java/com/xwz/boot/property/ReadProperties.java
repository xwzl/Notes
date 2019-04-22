package com.xwz.boot.property;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 获取Redis
 *
 * @author xuweizhi
 */
@EnableConfigurationProperties({Redis.class})
@Component
public class ReadProperties {


    private Redis redis;

    public ReadProperties(Redis redis) {
        this.redis = redis;
    }

    public void read() {
        System.out.println(redis);
    }

}
