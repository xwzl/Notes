package com.xwz.boot.controller;


import com.xwz.boot.model.User;
import com.xwz.boot.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xwz
 * @since 2019-04-22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/getUser")
    public List<User> getUsers() {
        return userService.getALl();
    }
}
