package com.java.shiro.service;

import com.java.shiro.mapper.MenuMapper;
import com.java.shiro.model.Menu;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuService {
    @Resource
    private MenuMapper menuMapper;

    public List<Menu> getAllMenuByRoleId(Integer roleId){
        return menuMapper.getAllMenuByRoleId(roleId);
    }
}
