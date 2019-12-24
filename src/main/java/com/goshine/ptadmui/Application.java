package com.goshine.ptadmui;

import com.goshine.ptadmui.core.datasource.annotation.EnableAutoDynamicDataSource;
import com.goshine.ptadmui.security.shiro.annotition.EnableShiro;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *SpringBoot启动入口
 *@author goshine
 */
@EnableCaching
@EnableWebMvc
@SpringBootApplication(scanBasePackages = "com.goshine.ptadmui")
@EnableTransactionManagement
@MapperScan(basePackages={"com.goshine.ptadmui.*.mapper"})
@EnableShiro
@EnableAutoDynamicDataSource

public class Application{
	public static void main(String[] args){

		 SpringApplication.run(Application.class,args);
	}
}
