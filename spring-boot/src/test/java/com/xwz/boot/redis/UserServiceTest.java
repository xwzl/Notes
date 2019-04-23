package com.xwz.boot.redis;

import com.xwz.boot.SpringBootsTest;
import com.xwz.boot.model.User;
import com.xwz.boot.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xuweizhi
 * @date 2019/04/23 16:28
 */
public class UserServiceTest extends SpringBootsTest {

    @Autowired
    private UserService userService;

    @Test
    public void getUser() {
        User byId = userService.findById(5);
        System.out.println(byId);
    }
}
