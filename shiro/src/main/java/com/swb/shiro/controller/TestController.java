package com.swb.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>文件  TestController</p>
 * <p>时间  2020-08-04 15:15:10</p>
 *
 * @author swb
 */
@Controller
public class TestController {

    @RequestMapping("login")
    public String login(){
        SecurityUtils.getSubject().getPrincipal();
        return "login";
    }
}
