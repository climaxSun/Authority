package com.swb.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>文件  MyUserService</p>
 * <p>时间  2020-07-19 14:45:07</p>
 *
 * @author swb
 */
@Service
@Slf4j
public class MyUserService implements UserDetailsService {
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = encoder.encode("123456");
        log.info("密码为:"+password);
        return new User(username,password, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN,ROLE_USER,ROLE_TOURIST"));
    }
}
