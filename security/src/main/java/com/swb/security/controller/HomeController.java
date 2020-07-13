package com.swb.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>文件  HomeController</p>
 * <p>时间  2020-07-07 15:26:32</p>
 *
 * @author swb
 */
@RestController
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "Hello Spring Security";
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello Spring Security login";
    }

    @GetMapping("/role")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String roleUser(){
        return "Hello Spring Security PreAuthorize hasRole";
    }

}
