package com.java.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java.mybatis.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 * Mapper 接口是没有实现类的，当调用接口方法时，接口全限名 + 方法名拼接字符串作为 key 值，可唯一定位一个对应的 MappedStatement 。
 * 举例：com.java.mybatis.mapper.UserMapper.addUserByXml ，可以唯一找到 "namespace" 为 com.java.mybatis.mapper.UserMapper 下面
 * "id" 为 addUserByXml 的 MappedStatement 。
 * <p>
 * 总结来说，在 Mybatis 中，每一个 select 、insert 、update 、delete  标签，都会被解析为一个 MappedStatement 对象。
 *
 * @author 徐伟智
 * @since 2019-04-03
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取用户信息
     *
     * @param uId 主键
     * @return 获取id
     */
    @Select("select * from user where u_id=#{uId}")
    User getUser(Integer uId);

    /**
     * 新增用户信息
     *
     * @param user 用户
     * @return 是否新增成功
     */
    @Insert("insert into user(address,role) values(#{address},#{role})")
    @Options(keyProperty = "uId", useGeneratedKeys = true, keyColumn = "u_id")
    int addUser(User user);

    /**
     * 更新用户
     *
     * @param user 用户
     */
    @Update("update user set address=#{address} , role =#{role} where u_id=#{uId}")
    void updateUser(User user);

    /**
     * 获取所有用户信息
     *
     * @return 用户
     */
    @Select("select * from user")
    List<User> getALl();

    /**
     * 删除
     *
     * @param user 删除单个用户
     */
    @Delete("delete from user where u_id=#{uId}")
    void deleteUser(User user);

    /**
     * XML 添加用户信息
     *
     * @param user 用户
     * @return 插入是否成功
     */
    int addUserByXml(User user);

    /**
     * 获取用户信息
     *
     * @param uId id
     * @return user
     */
    User getUserByIdXml(Integer uId);

    /**
     * 获取所有用户信息
     *
     * @return ha
     */
    List<User> getAllByXml();

    /**
     * 插入 user
     *
     * @param uesr 用户
     * @return 返回值
     */
    int insertUserOtherTag(User uesr);

    /**
     * 更新用户
     *
     * @param uer 用户
     */
    void updateUserOtherTag(User uer);

    /**
     * Mybatis
     *
     * @param map 入参
     * @return 返回值
     */
    List<User> getUserByMap(Map<String, Object> map);

    /**
     * 哈哈
     *
     * @param address 地址
     * @param role    角色
     * @return 返回值
     */
    List<User> getUserByParam(@Param("address") String address, @Param("role") Integer role);

}
