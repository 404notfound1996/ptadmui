package com.goshine.ptadmui.security.shiro.realm.impl;

import com.goshine.ptadmui.common.utils.lang.StringUtils;
import com.goshine.ptadmui.core.model.vo.ContextVo;
import com.goshine.ptadmui.security.config.AccessConfig;
import com.goshine.ptadmui.security.shiro.token.JwtWebToken;
import com.goshine.ptadmui.security.utils.JwtUtil;
import com.goshine.ptadmui.sys.entity.Permission;
import com.goshine.ptadmui.sys.entity.Role;
import com.goshine.ptadmui.sys.service.UserService;
import com.goshine.ptadmui.sys.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 使用jwt安全认证授权校验类
 * @author litao
 * @date 2019-11-12
 */
@Slf4j
public class CustomJwtAuthRealm extends AuthorizingRealm{
    @Autowired
    private UserService userService;
    @Autowired
    private AccessConfig accessConfig;
    /**
     * 重写此方法
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtWebToken;
    }
    /**
     * 认证.登录
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException{
        String token = (String) auth.getCredentials();
        if(StringUtils.isBlank(token)) {
            throw new  AuthenticationException("未获取到token信息");
        }
        String userName = null;
        try {
            userName = JwtUtil.getUsername(token);
        } catch (Exception e) {
            throw new AuthenticationException("header中token拼写错误或者值为空");
        }
        if (userName == null) {
            throw new AuthenticationException("token无效");
        }
        if (!JwtUtil.verify(token, userName, accessConfig.getTokenSecretKey())) {
            throw new AuthenticationException("用户名或密码错误(token无效或者与登录者不匹配)!");
        }
        Session session = SecurityUtils.getSubject().getSession(true);
        try{
            ContextVo context=userService.loginValidate(userName,session.getHost());
            session.setAttribute("context",context);
        }catch (Exception e){
            throw e;
        }

        if(SecurityUtils.getSubject().isRemembered()){
            session.setTimeout(-1000L);
        }
        return new SimpleAuthenticationInfo(token, token, this.getName());
    }


    /**
     * 授权
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        String username = JwtUtil.getUsername(principal.toString());
        UserVo user=userService.queryUserByUserName(username);
        List<String> permissionList=new ArrayList<String>();
        List<String> roleList=new ArrayList<String>();
        Set<Role> roles =new HashSet<>(user.getRoles());
        if(roles.size()>0) {
            for(Role role:roles){
                roleList.add(role.getRoleName());
                Set<Permission> permissions=new HashSet<Permission>(role.getPermissions());
                if(permissions.size()>0){
                    for(Permission permission:permissions){
                        permissionList.add(permission.getName());
                    }
                }
            }
        }
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        //将权限放入shiro中.
        authorizationInfo.addStringPermissions(permissionList);
        //将角色放入shiro中
        authorizationInfo.addRoles(roleList);
        return authorizationInfo;
    }
}
