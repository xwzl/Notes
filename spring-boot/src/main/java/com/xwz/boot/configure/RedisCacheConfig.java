package com.xwz.boot.configure;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;

/**
 * Redis缓存配置
 *
 * @author xuweizhi
 * @date 2019/04/23 15:24
 */
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {

    private Duration timeToLive = Duration.ZERO;

    public void setTimeToLive(Duration timeToLive) {
        this.timeToLive = timeToLive;
    }

    //RedisCacheManager的基本属性
    //@SuppressWarnings("rawtypes") //
    //配置redisTemplate 通过构造函数
    //private final RedisOperations redisOperations;
    ////是否使用前缀
    //private boolean usePrefix = false;
    ////默认前缀 为":"。使用前缀可以对缓存进行分组，避免缓存的冲突
    //private RedisCachePrefix cachePrefix = new DefaultRedisCachePrefix();
    ////远程加载缓存
    //private boolean loadRemoteCachesOnStartup = false;
    ////是否动态生成缓存。默认为true。这个就是上面如果缓存不存在，则创建
    ////是通过这个属性进行配置。配置为false则不会去创建缓存
    //private boolean dynamic = true;
    //
    //// 过期时间 0为永不过期
    //private long defaultExpiration = 0;
    ////可以配置指定key的过期时间 可以通过定制化配置过期时间
    //private Map<String, Long> expires = null;
    ////配置缓存名称集合
    //private Set<String> configuredCacheNames;
    ////缓存是否可以为null
    //private final boolean cacheNullValues;

    /**
     * Spring Cache提供的@Cacheable注解不支持配置过期时间，还有缓存的自动刷新。
     * 我们可以通过配置CacheManneg来配置默认的过期时间和针对每个缓存容器（value）单独配置过期时间，但是总是感觉不太灵活。
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        //Redis加锁的写入器
        //RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(connectionFactory);
        //启动Redis默认设置
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                //.entryTtl(this.timeToLive)
                .entryTtl(Duration.ofHours(1))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()))
                .disableCachingNullValues();

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .transactionAware()
                .build();
    }

    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    private RedisSerializer<Object> valueSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }


    /**
     * 设置RedisTemplate序列化器
     *
     * @param rcf 注入Redis工厂
     * @return 返回Redis模板
     */
    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory rcf) {

        RedisTemplate<String, Object> rt = new RedisTemplate<>();
        // 配置连接工厂
        rt.setConnectionFactory(rcf);

        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 设置value的序列化规则和 key的序列化规则
        rt.setValueSerializer(jackson2JsonRedisSerializer);
        rt.setKeySerializer(new StringRedisSerializer());

        rt.setHashKeySerializer(jackson2JsonRedisSerializer);
        rt.setHashValueSerializer(jackson2JsonRedisSerializer);

        rt.setDefaultSerializer(jackson2JsonRedisSerializer);
        rt.setEnableDefaultSerializer(true);
        rt.afterPropertiesSet();
        return rt;
    }

    /**
     * 根据方法名注入对象
     */
    @Bean
    public ValueOperations opsForValue(RedisTemplate redisTemplate) {
        return redisTemplate.opsForValue();
    }

    @Bean
    public ListOperations opsForList(RedisTemplate redisTemplate) {
        return redisTemplate.opsForList();
    }

    @Bean
    public HashOperations opsForHash(RedisTemplate redisTemplate) {
        return redisTemplate.opsForHash();
    }

    @Bean
    public SetOperations opsForSet(RedisTemplate redisTemplate) {
        return redisTemplate.opsForSet();
    }

    @Bean
    public ZSetOperations opsForZSet(RedisTemplate redisTemplate) {
        return redisTemplate.opsForZSet();
    }

    /**
     * 自定义redis key值生成策略
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    /**
     * 自定义CacheManager
     */
    //@Bean
    //public CacheManager cacheManager(RedisTemplate redisTemplate) {
    //    //全局redis缓存过期时间
    //    RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMillis(10));
    //    RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(Objects.requireNonNull(redisTemplate.getConnectionFactory()));
    //    return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
    //}

    //@Bean
    //public CacheManager cacheManager(RedisTemplate redisTemplate) {
    //    RedisCacheManager cacheManager= new RedisCacheManager(redisTemplate);
    //    cacheManager.setDefaultExpiration(60);
    //    Map<String,Long> expiresMap=new HashMap<>();
    //    expiresMap.put("Product",5L);
    //    cacheManager.setExpires(expiresMap);
    //    return cacheManager;
    //}


}