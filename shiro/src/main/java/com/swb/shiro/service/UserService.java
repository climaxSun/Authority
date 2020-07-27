package com.swb.shiro.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.swb.shiro.mapper.UserMapper;
import com.swb.shiro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>文件  UserService</p>
 * <p>时间  2020-07-26 15:14:41</p>
 *
 * @author swb
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findByUserName(String username){
        return userMapper.selectOne(new QueryWrapper<User>().eq("username",username).last(" limit 1"));
    }
}
