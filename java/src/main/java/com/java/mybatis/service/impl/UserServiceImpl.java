package com.java.mybatis.service.impl;

import com.java.mybatis.mapper.UserMapper;
import com.java.mybatis.model.User;
import com.java.mybatis.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 徐伟智
 * @since 2019-04-03
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

}
