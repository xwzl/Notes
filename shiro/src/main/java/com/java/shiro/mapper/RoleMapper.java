package com.java.shiro.mapper;

import com.java.shiro.model.Role;

import java.util.List;

/**
 * RoleDAO继承基类
 */
public interface RoleMapper extends MyBatisBaseMapper<Role, Integer> {

    /**
     * 通过用户id获取用户拥有的角色
     * @param userId
     * @return
     */
    List<Role> findByUserId(Integer userId);
}