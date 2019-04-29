package com.java.shiro.service;

import com.java.shiro.mapper.UserMapper;
import com.java.shiro.model.User;
import com.java.shiro.system.vo.Grid;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserMapper userDAO;

    public Grid findList(){
        Grid grid = new Grid();

        Page page = PageHelper.offsetPage(0, 10, true);
        userDAO.findList(null);

        grid.setRows(page.getResult());
        grid.setTotal(page.getTotal());

        return grid;
    }

    public User getUserByName(String name){
        return userDAO.getByName(name);
    }


    public User getByOpenid(String openid){
        //这里假装是通过openid从数据库中获取到用户信息
        User user = new User();
        user.setName("张三");
        user.setLoginName("zhangsan");
        user.setPhone("18888888888");
        user.setId(1);
        user.setLoginFlag("1");

        return user;
    }

    public User getByPhone(String phone){
        return userDAO.getByPhone(phone);
    }
}
