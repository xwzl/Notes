package com.xwz.boot.controller;


import com.xwz.boot.model.User;
import com.xwz.boot.service.UserService;
import com.xwz.boot.until.redis.RedisService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
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

    /**
     * 使用实现类 Redis 进行redis 操作
     */
    private final RedisService redisService;

    public UserController(UserService userService, RedisService redisService) {
        this.userService = userService;
        this.redisService = redisService;
    }

    /**
     * http://localhost:8083/spring-boot/swagger-ui.html#/ 访问
     */
    @ApiOperation(value = "第一个接口", notes = "hello接口")
    @ApiParam(value = "api 测试")
    @GetMapping("/getUsers")
    public List<User> getUsers(String api) {
        System.out.println(api);
        User user = new User();
        user.setUId(3);
        User service = userService.getById(user);
        redisService.setBean(service.getUId() + service.getAddress(), service);
        User redisUser = (User) redisService.getBean(service.getUId() + service.getAddress());
        System.out.println(redisUser);
        return userService.getALl();
    }

    @ApiOperation(value = "insertUser", notes = "hello接口")
    @GetMapping("/insertUser")
    public User insertUser() {
        User user = new User(null, "123456", "123456", null, "11", "12323", 1, "2312");
        return userService.insert(user);
    }


    @ApiOperation(value = "update", notes = "更新")
    @GetMapping("/update")
    public User update(String name, Integer id) {
        User byId = userService.getById(id);
        byId.setUsername(name);
        return userService.update(byId);
    }

    @ApiOperation(value = "master", notes = "获取")
    @GetMapping("/getUser")
    public User getUser(Integer id) {
        return userService.findById(id);
    }

    @ApiOperation(value = "slave2", notes = "hello接口")
    @GetMapping("/delete")
    public void delete(Integer id) {
        User user = new User();
        user.setUId(id);
        userService.delete(user);
    }

    @ApiOperation(value = "slave1", notes = "hello接口")
    @GetMapping("/getPlus")
    public User getPlus(Integer id) {
        return userService.getById(id);
    }
}
