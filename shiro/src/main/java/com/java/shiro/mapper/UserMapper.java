package com.java.shiro.mapper;

import com.java.shiro.model.User;

/**
 * UserDAO继承基类
 */
public interface UserMapper extends MyBatisBaseMapper<User, Integer> {

    /**
     * 通过登录名获取帐号信息
     * @param name
     * @return
     */
    User getByName(String name);

    /**
     * 通过手机号获取帐号信息
     * @param phone
     * @return
     */
    User getByPhone(String phone);
}