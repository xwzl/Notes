package com.xwz.boot.configure;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xwz.boot.model.Permission;
import com.xwz.boot.model.Role;
import com.xwz.boot.model.User;
import com.xwz.boot.service.PermissionService;
import com.xwz.boot.service.RoleService;
import com.xwz.boot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 功能描述：ShiroRealm域
 *
 * @author xuweizhi
 * @date 2019/04/29 1:38
 */
public class ShiroRealm extends AuthorizingRealm {

    /**
     * 描述：userService对象
     *
     * @date 2018/12/9 20:54
     */
    @Autowired
    private UserService userService;

    /**
     * 描述：roleService对象
     *
     * @date 2018/12/9 20:54
     */
    @Autowired
    private RoleService roleService;

    /**
     * 描述：permissionService对象
     *
     * @date 2018/12/9 20:54
     */
    @Autowired
    private PermissionService permissionService;


    /**
     * 功能描述：shiro认证
     *
     * @param token
     * @return org.apache.shiro.authc.AuthenticationInfo
     * @author https://blog.csdn.net/chen_2890
     * @date 2018/12/5 19:42
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户输入的用户名和密码
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        //获取用户名
        String username = upToken.getUsername();
        //根据用户名去数据库查询
        Wrapper<User> userWrapper = new QueryWrapper<>();
        userWrapper.getEntity().setUsername(username);
        User user = userService.getOne(userWrapper);
        //用户名不存在
        if (user == null) {
            return null;
        }
        //用户名存在，进去密码比较器比较密码
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());

        return authenticationInfo;
    }

    /**
     * 功能描述：shiro授权
     *
     * @param principal
     * @return org.apache.shiro.authz.AuthorizationInfo
     * @author https://blog.csdn.net/chen_2890
     * @date 2018/12/9 20:55
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        //创建授权对象
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //获取已经认证通过的用户信息
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        //根据用户信息查找对应的角色
        List<Role> roleList = roleService.getMap();
        for (Role role : roleList) {
            authorizationInfo.addRole(role.getKeyword());
        }
        //根据用户信息查找对应的权限
        List<Permission> permissionList = permissionService.findPermissionByUser(user);
        for (Permission permission : permissionList) {
            authorizationInfo.addStringPermission(permission.getKeyword());
        }
        return authorizationInfo;
    }


}
