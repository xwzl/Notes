package com.xwz.boot.service;

import com.xwz.boot.model.People;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xwz
 * @since 2019-04-22
 */
public interface PeopleService extends BaseService<People> {

    List<People> getALl();

}
