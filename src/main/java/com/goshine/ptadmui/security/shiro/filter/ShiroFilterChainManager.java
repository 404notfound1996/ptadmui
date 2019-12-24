package com.goshine.ptadmui.security.shiro.filter;


import com.goshine.ptadmui.security.config.AccessConfig;
import com.goshine.ptadmui.security.shiro.filter.impl.JwtLoginFilter;
import lombok.Data;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 *  Filter 管理器
 * @author litao
 * @date 2019-11-14
 */
@Component
@Data
public class ShiroFilterChainManager {
    @Autowired
    private  AccessConfig accessConfig;
    /**
     * 初始化获取过滤链
     * @return
     */
    public Map<String, Filter> initGetFilters() {
        Map<String, Filter> filters = new LinkedHashMap<>();
        //添加token过滤器
        JwtLoginFilter jwtLoginFilter=new JwtLoginFilter();
        filters.put("jwt", jwtLoginFilter);
        return filters;
    }
    /**
     * 初始化获取过滤链规则
     */
    public void initFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean) { ;
        //配置登录的url和登录成功的url
        shiroFilterFactoryBean.setSuccessUrl(accessConfig.getSuccessUrl());
        shiroFilterFactoryBean.setUnauthorizedUrl(accessConfig.getUnauthorizedUrl());
        //配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<>();
        //不需要认证可以访问路径
        Set<String> anonAccessChainDefinitions=accessConfig.getAnonAccessChainDefinitions();
        for(String anonAccessChainDefinition:anonAccessChainDefinitions){
            filterChainDefinitionMap.put(anonAccessChainDefinition,"anon");
        }
        //需要认证才可以访问
        Set<String>  authenticationChainDefinitions=accessConfig.getAuthenticationChainDefinitions();
        for(String authenticationChainDefinition:authenticationChainDefinitions){
            filterChainDefinitionMap.put(authenticationChainDefinition, "jwt");
        }
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }

}
