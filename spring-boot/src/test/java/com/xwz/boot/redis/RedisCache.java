package com.xwz.boot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author xuweizhi
 * @date 2018/11/14 13:16
 */
public class RedisCache {

    @Autowired
    private RedisTemplate<String, Object> redis;

}
