package com.goshine.ptadmui.security.shiro.realm;

import com.goshine.ptadmui.security.shiro.realm.impl.CustomJwtAuthRealm;
import com.goshine.ptadmui.security.shiro.token.JwtWebToken;
import org.apache.shiro.realm.Realm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * realm管理器
 * @author litao
 * @date 2019-11-14
 */
@Configuration
public class RealmManager {
    /**
     * 登录认证realm
     * @return
     */
    @Bean
    public CustomJwtAuthRealm jwtAuthRealm(){
        CustomJwtAuthRealm jwtAuthRealm = new CustomJwtAuthRealm();
        return jwtAuthRealm;
    }

    public List<Realm> initGetRealm() {
        List<Realm> realmList = new LinkedList<>();
        //JWT设置
        CustomJwtAuthRealm jwtAuthRealm =jwtAuthRealm();
        //jwtAuthRealm.setCredentialsMatcher(credentialsMatcher());
        jwtAuthRealm.setAuthenticationTokenClass(JwtWebToken.class);
        realmList.add(jwtAuthRealm);
        return Collections.unmodifiableList(realmList);
    }
}
