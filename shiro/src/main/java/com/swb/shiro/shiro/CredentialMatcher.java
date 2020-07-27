package com.swb.shiro.shiro;

import cn.hutool.core.util.StrUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * <p>文件  CredentialMatcher</p>
 * <p>时间  2020-07-27 22:17:21</p>
 *
 * @author swb
 */
public class CredentialMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken= (UsernamePasswordToken) token;
        String password = new String(usernamePasswordToken.getPassword());
        String dbPassword= (String) info.getCredentials();
        return StrUtil.equals(dbPassword,password);
    }
}
