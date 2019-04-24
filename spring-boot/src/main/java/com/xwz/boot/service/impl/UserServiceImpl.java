package com.xwz.boot.service.impl;

import com.xwz.boot.mapper.UserMapper;
import com.xwz.boot.model.User;
import com.xwz.boot.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xwz
 * @since 2019-04-22
 */
@Service
@CacheConfig(cacheNames = "user")
@Transactional
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getALl() {
        return userMapper.getALl();
    }

    /**
     * 条件成立之前清楚缓存
     *
     * @param user 入参
     */
    @Override
    @CacheEvict(key = "'user'+#user.UId", beforeInvocation = false)
    public void delete(@NotNull User user) {
        userMapper.deleteById(user.getUId());
    }


    /**
     * condition和unless 只满足特定条件才进行缓存：
     * condition: 在执行方法前，condition的值为true，则缓存数据
     * unless ：在执行方法后，判断unless ，如果值为true，则不缓存数据
     * condition和unless:可以同时使用，则此时只缓存同时满足两者的记录
     * 条件缓存：
     * 只有满足condition的请求才可以进行缓存，如果不满足条件，则跟方法没有@Cacheable注解的方法一样
     * 如下面只有id < 3才进行缓存
     */
    @Override
    @CachePut(condition = "#result != 'null'", key = "'user'+#user.UId")
    public User update(@NotNull User user) {
        User checkResult = userMapper.selectById(user.getUId());
        if (checkResult == null) {
            return null;
        }
        userMapper.updateById(user);
        System.out.println(user);
        return user;
    }

    /**
     * value 或 cacheNames 属性做键，key 属性则可以看作为 value 的子键， 一个 value 可以有多个 key 组成不同值存在 Redis 服务器。
     * <p>
     * 验证了下，value 和 cacheNames 的作用是一样的，都是标识主键。两个属性不能同时定义，只能定义一个，否则会报错。
     * <p>
     * 如果设置sync=true，
     * <p>
     * 如果缓存中没有数据，多个线程同时访问这个方法，则只有一个方法会执行到方法，其它方法需要等待
     * <p>
     * 如果缓存中已经有数据，则多个线程可以同时从缓存中获取数据
     * <p>
     * {@link User#getCreateTime()} 解决 LocalDateTime 序列化出错问题
     */
    @Override
    @Cacheable(key = "'user'+#id", sync = true)
    //@Cacheable(value = "findById", keyGenerator = "keyGenerator",sync = true)
    public User findById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    @CachePut(key = "'user'+#result.UId")
    public User insert(User user) {
        userMapper.insert(user);
        return user;
    }

}
