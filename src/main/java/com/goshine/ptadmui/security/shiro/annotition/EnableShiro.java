package com.goshine.ptadmui.security.shiro.annotition;


import com.goshine.ptadmui.security.shiro.config.ShiroConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启动shiro安全权限控制
 * @author litao
 * @date 2019-11-12
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({ShiroConfig.class})
public @interface EnableShiro {
}
