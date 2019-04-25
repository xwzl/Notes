package com.xwz.boot.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xwz.boot.condition.DataSource;
import com.xwz.boot.model.User;
import org.apache.ibatis.annotations.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xwz
 * @since 2019-04-22
 */
public interface UserMapper extends BaseMapper<User> {

    @Override
    int insert(User user);

    @DataSource("slave2")
    @Override
    int deleteById(Serializable serializable);

    @Override
    int deleteByMap(Map<String, Object> map);

    @Override
    int delete(Wrapper<User> wrapper);

    @Override
    int deleteBatchIds(Collection<? extends Serializable> collection);

    @Override
    int updateById(User user);

    @Override
    int update(User user, Wrapper<User> wrapper);

    @DataSource("slave1")
    @Override
    User selectById(Serializable serializable);

    @Override
    List<User> selectBatchIds(Collection<? extends Serializable> collection);

    @Override
    List<User> selectByMap(Map<String, Object> map);

    @Override
    User selectOne(Wrapper<User> wrapper);

    @Override
    Integer selectCount(Wrapper<User> wrapper);

    @Override
    List<User> selectList(Wrapper<User> wrapper);

    @Override
    List<Map<String, Object>> selectMaps(Wrapper<User> wrapper);

    @Override
    List<Object> selectObjs(Wrapper<User> wrapper);

    @Override
    IPage<User> selectPage(IPage<User> iPage, Wrapper<User> wrapper);

    @Override
    IPage<Map<String, Object>> selectMapsPage(IPage<User> iPage, Wrapper<User> wrapper);

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
    @DataSource
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
