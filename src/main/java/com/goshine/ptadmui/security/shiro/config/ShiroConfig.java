package com.goshine.ptadmui.security.shiro.config;

import com.goshine.ptadmui.security.shiro.filter.ShiroFilterChainManager;
import com.goshine.ptadmui.security.shiro.realm.RealmManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.apache.shiro.mgt.SecurityManager;

/**
 * @author goshine
 * @since 2018-08-28
 */

public class ShiroConfig{
     /**
      * shiro安全管理配置
      * @param securityManager 安全管理对象
      * @param filterChainManager 过滤管理器
      */
    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager, ShiroFilterChainManager filterChainManager) {
    	ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setFilters(filterChainManager.initGetFilters());
        bean.setSecurityManager(securityManager);
       //设置过滤链
        filterChainManager.initFilterChain(bean);
        return bean;
    }

    /**
     * 配置核心安全事务管理器
     * @param realmManager realm管理器
     * @return
     */
    @Bean(name="securityManager")
    public SecurityManager securityManager(@Autowired RealmManager realmManager, @Autowired EhCacheManager ehCacheManager) {
        System.out.println("--------------shiro已经加载----------------");
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealms(realmManager.initGetRealm());
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(ehCacheManager);
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }
    /**
     * cookie管理对象;
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("5AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }
    /**
     * cookie对象;
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie=new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }
    /**
     * 添加注解支持
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        // https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(-1);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setSessionIdCookieEnabled(true);
        SimpleCookie cookie = new SimpleCookie("WEBJSESSIONID");
        cookie.setMaxAge(1800000);//30分钟失效
        sessionManager.setSessionIdCookie(cookie);
        return sessionManager;
    }
}