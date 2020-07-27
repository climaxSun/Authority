package com.swb.shiro.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * <p>文件  ShiroConfiguration</p>
 * <p>时间  2020-07-27 22:34:40</p>
 *
 * @author swb
 */
@Configuration
public class ShiroConfiguration {

    @Bean("credentialMatcher")
    public CredentialMatcher credentialMatcher() {
        return new CredentialMatcher();
    }

    @Bean("authRealm")
    public AuthRealm authRealm(@Qualifier(value = "credentialMatcher") CredentialMatcher credentialMatcher) {
        AuthRealm realm = new AuthRealm();
        realm.setCredentialsMatcher(credentialMatcher);
        return realm;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier(value = "authRealm") AuthRealm authRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        return manager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier(value = "securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);

        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/index");
        bean.setUnauthorizedUrl("/403");

        LinkedHashMap<String, String> filterChain = new LinkedHashMap<>();
        filterChain.put("/index", "authc");
        filterChain.put("/login", "anon");
        bean.setFilterChainDefinitionMap(filterChain);

        return bean;
    }

    @Bean
    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier(value = "securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
}