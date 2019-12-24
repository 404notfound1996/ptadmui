package com.goshine.ptadmui.core.datasource.annotation;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
import com.goshine.ptadmui.core.datasource.config.RemoveMyBatisPlusDynamicDataSourceConfig;
import com.goshine.ptadmui.core.datasource.register.DynamicDataSourceRegister;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


/**
 * @author litao
 * @date 2019-11-27
 * 集成mybatis plus多数据源
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
@Import(DynamicDataSourceAutoConfiguration.class)
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, RemoveMyBatisPlusDynamicDataSourceConfig.class})
public @interface EnableAutoDynamicDataSource {

}
