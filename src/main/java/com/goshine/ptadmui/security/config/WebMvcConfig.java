package com.goshine.ptadmui.security.config;

import com.goshine.ptadmui.security.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
class WebMvcConfig implements WebMvcConfigurer{

    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
         registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
         registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
         registry.addResourceHandler("/public/**").addResourceLocations("classpath:/public/");
    }
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML);
    }
    /**
     * 此方法把该拦截器实例化成一个bean,否则在拦截器里无法注入其它bean
     * @return
     */
    @Bean
    SessionInterceptor sessionInterceptor(){
        return new SessionInterceptor();
    }
    /**
     * 配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(sessionInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/swagger-ui.html","/swagger-resources/**","/v2/api-docs","/webjars/**","/public/**",
                        "/ie","/login","/loginValidate","/logout","/captcha","/error","/errors/**","/socket","/api/**");
    }
}