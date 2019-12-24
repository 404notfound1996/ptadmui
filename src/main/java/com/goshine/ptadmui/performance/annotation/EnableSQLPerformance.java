package com.goshine.ptadmui.performance.annotation;

import com.goshine.ptadmui.performance.config.MybatisPlusOptLockerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启动sql耗时打印能力
 * @author litao
 * @date 2019-11-15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(MybatisPlusOptLockerConfig.class)
public @interface EnableSQLPerformance {

}
