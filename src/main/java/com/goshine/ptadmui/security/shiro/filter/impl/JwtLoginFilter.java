package com.goshine.ptadmui.security.shiro.filter.impl;

import com.goshine.ptadmui.core.consts.ResultCodeConstants;
import com.goshine.ptadmui.core.model.Response;
import com.goshine.ptadmui.security.config.AccessConfig;
import com.goshine.ptadmui.security.shiro.token.JwtWebToken;
import com.goshine.ptadmui.security.utils.RequestResponseUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JWT Token登录认证
 * @author litao
 * @date 2019-11-12
 */
public class JwtLoginFilter extends BasicHttpAuthenticationFilter {

    /**
     * 如果带有 token，则对 token 进行检查，否则直接通过
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws UnauthorizedException {
        try {
            executeLogin(request, response);
            return true;
        } catch (Exception e) {
            RequestResponseUtils.responseWrite((HttpServletResponse)response,HttpServletResponse.SC_UNAUTHORIZED, Response.error(ResultCodeConstants.ATHORITION_FAILED_CODE,e.getMessage()));
        }
        return false;
    }
    /**
     * 执行登陆操作
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(AccessConfig.REQUEST_TOKEN_LABEL);
        JwtWebToken jwtToken = new JwtWebToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(jwtToken);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }
}