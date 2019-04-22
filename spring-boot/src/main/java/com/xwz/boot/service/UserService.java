package com.xwz.boot.service;

import com.xwz.boot.model.User;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xwz
 * @since 2019-04-22
 */
public interface UserService extends BaseService<User> {

    List<User> getALl();

}
