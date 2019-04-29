package com.java.shiro.mapper;

import com.java.shiro.model.Menu;

import java.util.List;

/**
 * MenuDAO继承基类
 */
public interface MenuMapper extends MyBatisBaseMapper<Menu, Integer> {

    /**
     * 通过角色id获取所有权限信息
     */
    List<Menu> getAllMenuByRoleId(Integer roleId);
}