package com.goshine.ptadmui.core.datasource.config;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
import com.goshine.ptadmui.core.datasource.annotation.EnableAutoDynamicDataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
/**
 * 解决mybatis plus引入时自动配置问题,在不需要引入动态数据源时使用
 * @author litao
 */
@Configuration
@EnableAutoConfiguration(exclude = DynamicDataSourceAutoConfiguration.class)
public class RemoveMyBatisPlusDynamicDataSourceConfig {

}
