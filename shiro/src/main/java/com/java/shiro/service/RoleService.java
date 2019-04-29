package com.java.shiro.service;

import com.java.shiro.mapper.RoleMapper;
import com.java.shiro.model.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService {
    @Resource
    private RoleMapper roleDAO;

    public List<Role> findByUserid(Integer userId){
        return roleDAO.findByUserId(userId);
    }
}
