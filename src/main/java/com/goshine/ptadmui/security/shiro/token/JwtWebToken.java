package com.goshine.ptadmui.security.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;


/**
 * JwtToken:实现shiro的AuthenticationToken接口的类JwtToken
 *
 * @author litao
 * @date: 2019-11-12
 */
public class JwtWebToken implements AuthenticationToken {

    private String token;
    public JwtWebToken(String token) {
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}