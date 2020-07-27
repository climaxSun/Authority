package com.swb.shiro.controller;

import com.swb.shiro.model.User;
import com.swb.shiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>文件  UserController</p>
 * <p>时间  2020-07-26 16:14:23</p>
 *
 * @author swb
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable(value = "username")String username){
        log.info(username);
        return userService.findByUserName(username);
    }
}
