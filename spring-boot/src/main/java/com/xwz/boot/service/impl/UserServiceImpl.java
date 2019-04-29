package com.xwz.boot.service.impl;

import com.xwz.boot.model.User;
import com.xwz.boot.mapper.UserMapper;
import com.xwz.boot.service.UserService;
import com.xwz.boot.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xwz
 * @since 2019-04-29
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

}
