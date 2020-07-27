package com.swb.shiro.shiro;

import cn.hutool.core.collection.CollUtil;
import com.swb.shiro.model.Permission;
import com.swb.shiro.model.Role;
import com.swb.shiro.model.User;
import com.swb.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>文件  AuthRealm</p>
 * <p>时间  2020-07-27 21:59:30</p>
 *
 * @author swb
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user= (User) principals.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissions;
        Set<Role> roles=user.getRoles();
        if(CollUtil.isNotEmpty(roles)){
            permissions = roles.stream().map(Role::getPermissions)
                    .filter(CollUtil::isNotEmpty).flatMap(Set::stream).map(Permission::getName)
                    .distinct().collect(Collectors.toList());
        }else{
            permissions=new ArrayList<>();
        }
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);
        return info;
    }

    /**
     * 验证登录
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken= (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        User user=userService.findByUserName(username);
        return new SimpleAuthenticationInfo(user,user.getPassword(),this.getClass().getName());
    }
}
