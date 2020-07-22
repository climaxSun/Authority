package com.swb.security.controller;

import cn.hutool.core.util.RandomUtil;
import com.swb.security.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>文件  HomeController</p>
 * <p>时间  2020-07-07 15:26:32</p>
 *
 * @author swb
 */
@RestController
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "Hello Spring Security";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Security login";
    }

    @GetMapping("/role")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String roleUser() {
        return "Hello Spring Security PreAuthorize hasRole";
    }

    @PreAuthorize("#id<10")
    @GetMapping("/test/param")
    public String test(Integer id ) {
        return "test param";
    }

    @PreAuthorize("principal.username.equals(#username)")
    @GetMapping("/test/principal")
    public String testPrincipal(String username) {
        return "test principal";
    }

    @PreAuthorize("#user.username.equals('abc')")
    @PostMapping("/test/object")
    public String testUser(@RequestBody User user) {
        return "test object";
    }

    @PostAuthorize("returnObject%2==0")
    @GetMapping("/test/postAu")
    public Integer testPostAuthorize(){
        int i = RandomUtil.randomInt(1, 10);
        log.info("随机数:"+i);
        return i;
    }

    @PreFilter("filterObject%2==0")
    @PostFilter("filterObject%4==0")
    @PostMapping("/test/postAuObject")
    public List<Integer> testPostAuthObj(@RequestBody List<Integer> ids){
        log.info("=================================");
        ids.stream().map(String::valueOf).forEach(log::info);
        log.info("=================================");
        return ids;
    }
}
