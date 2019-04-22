package com.xwz.boot.service.impl;

import com.xwz.boot.mapper.UserMapper;
import com.xwz.boot.model.User;
import com.xwz.boot.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xwz
 * @since 2019-04-22
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getALl() {
        return userMapper.getALl();
    }
}
