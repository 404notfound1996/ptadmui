package com.goshine.ptadmui.security.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.HashSet;
import java.util.Set;

/**
 * 访问路径配置信息
 * @author litao
 * @date 2019-11-12
 */
@Configuration
public class AccessConfig implements InitializingBean, EnvironmentAware {
    /**
     * 系统环境变量
     */
    private Environment environment;
    /**
     * token秘钥
     */
    private String tokenSecretKey;
    /**
     * 请求头token名称
     */
    public static final String REQUEST_TOKEN_LABEL="X-Token";
    /**
     * 请求登录url
     */
    private String loginUrl;
    /**
     * 账号注册接口
     */
    private String registerUrl;
    /**
     * 登录成功URL
     */
    private String successUrl;
    /**
     * 设置未授权URL
     */
    private String unauthorizedUrl;
    /**
     * 可以匿名访问的请求地址
     */
    private Set<String> anonAccessChainDefinitions=new HashSet<>();
    /**
     * 需要认证访问
     */
    private Set<String> authenticationChainDefinitions=new HashSet<>();

    public String getLoginUrl() {
        return loginUrl;
    }

    public String getRegisterUrl() {
        return registerUrl;
    }

    public String getSuccessUrl() {
        return successUrl;
    }
    public String getUnauthorizedUrl() {
        return unauthorizedUrl;
    }

    public Set<String> getAnonAccessChainDefinitions() {
        return anonAccessChainDefinitions;
    }


    public Set<String> getAuthenticationChainDefinitions() {
        return authenticationChainDefinitions;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
       // 设置token默认秘钥
        tokenSecretKey="P@ssw02d";
        loginUrl="/private/api/login";
        successUrl="/";
        unauthorizedUrl="/errors/noauth";
        addAnnonAccessChainDefinition();
        addAuthenChainDefinition();
    }

    /**
     * 添加可以匿名访问的请求地址
     */
    private void addAnnonAccessChainDefinition(){
        anonAccessChainDefinitions.add("/public/**");
        anonAccessChainDefinitions.add("/login");
        anonAccessChainDefinitions.add("/ie");
        anonAccessChainDefinitions.add("/loginValidate");
        anonAccessChainDefinitions.add("/private/api/login");
        anonAccessChainDefinitions.add("/logout");
        anonAccessChainDefinitions.add("/private/api/logout");
        anonAccessChainDefinitions.add("/captcha**");
        anonAccessChainDefinitions.add("/errors/**");
        anonAccessChainDefinitions.add("/api/**");
        addSwaggerAnnonAccessChainDefinition();
    }
    /**
     * 添加可以匿名访问的请求地址
     */
    private void addSwaggerAnnonAccessChainDefinition(){
        String isCanViewSwagger=environment.getProperty("swagger.config.isCanViewSwagger");
        if(!StringUtils.isBlank(isCanViewSwagger) && !Boolean.valueOf(isCanViewSwagger)){
            return;
        }
        anonAccessChainDefinitions.add("/swagger-ui.html");
        anonAccessChainDefinitions.add("/swagger-resources/**");
        anonAccessChainDefinitions.add("/v2/api-docs");
        anonAccessChainDefinitions.add("/webjars/**");
    }
    /**
     * 添加需要认证访问地址
     */
    private void addAuthenChainDefinition(){
        authenticationChainDefinitions.add("/**");
    }

    public String getTokenSecretKey() {
        return tokenSecretKey;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment=environment;
    }
}
