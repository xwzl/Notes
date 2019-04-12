package com.java.mybatis.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.java.mybatis.mapper.UserMapper;
import com.java.mybatis.model.User;
import com.java.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 徐伟智
 * @since 2019-04-03
 */
@RestController
@RequestMapping("/mybatis")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/get/{id}")
    public User getUser(@PathVariable("id") String id) {
        return userService.getById(id);
    }

    @RequestMapping("/get2/{id}")
    public User getUser2(@PathVariable("id") String id) {
        return userMapper.getUser(Integer.valueOf(id));
    }

    @RequestMapping("/get4")
    public List<User> getUser4() {
        System.out.println("haha");
        return userMapper.getAllByXml();
    }

    @RequestMapping("/get5/{id}")
    public User getUser5(@PathVariable("id") Integer id) {
        return userMapper.getUserByIdXml(id);
    }

    @RequestMapping("/get3")
    public List<User> getUser3() {
        return userMapper.getALl();
    }

    @RequestMapping("add/{address}/{role}")
    public int addUser(@PathVariable("address") String address, @PathVariable("role") Integer role) {
        User user = new User();
        user.setAddress(address);
        user.setUId(role);
        return userMapper.addUser(user);
    }

    @RequestMapping("update/{address}/{role}/{id}")
    public void updateUser(@PathVariable("address") String address, @PathVariable("role") Integer role, @PathVariable("id") Integer id) {
        User user = new User();
        user.setAddress(address);
        user.setUId(role);
        user.setUId(id);
        userMapper.updateUser(user);
    }

    @RequestMapping("delete/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        User user = new User();
        user.setUId(id);
        userMapper.deleteUser(user);
    }

    @RequestMapping("add1/{address}/{role}")
    public int addUser1(@PathVariable("address") String address, @PathVariable("role") Integer role) {
        User user = new User();
        user.setAddress(address);
        user.setRole(role);
        return userMapper.addUserByXml(user);
    }

    @RequestMapping("add2/{address}/{role}")
    public int addUser2(@PathVariable("address") String address, @PathVariable("role") Integer role, String id) {
        User user = new User();
        if (StringUtils.isNotEmpty(id)) {
            user.setUId(Integer.valueOf(id));
        }
        user.setAddress(address);
        user.setCreateTime(LocalDateTime.now());
        user.setRole(role);
        return userMapper.insertUserOtherTag(user);
    }

    @RequestMapping("update1/{address}/{role}/{id}")
    public void updateUser1(@PathVariable("address") String address, @PathVariable("role") Integer role, @PathVariable("id") Integer id) {
        User user = new User();
        user.setAddress(address);
        user.setRole(role);
        user.setCreateTime(LocalDateTime.now());
        user.setUId(id);
        userMapper.updateUserOtherTag(user);
    }

    /**
     * 数组负载类型家
     */
    @PostMapping("getUser")
    public List<User> getUser(String address, String role, @RequestParam(value = "id", required = false) List<String> id) {
        Map<String, Object> map = new HashMap<>();
        if (id.size() > 0) {
            map.put("id", id);
        }
        if (StringUtils.isNotEmpty(role))
            map.put("role", role);
        if (StringUtils.isNotEmpty(address))
            map.put("address", address);
        return userMapper.getUserByMap(map);
    }


    /**
     * Mybatis 多个参数测试
     *
     * @return
     */
    @RequestMapping("haha")
    public List<User> getHaha() {
        return userMapper.getUserByParam("山东", 1);
    }

}
