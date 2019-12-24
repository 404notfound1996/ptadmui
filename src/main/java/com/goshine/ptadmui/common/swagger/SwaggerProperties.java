package com.goshine.ptadmui.common.swagger;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

/**
 * swagger配置信息
 * @author litao
 * @date 2019-11-29
 */
@Configuration
@PropertySource(value = "classpath:config/base/swagger.properties",encoding="utf-8")
@Data
public class SwaggerProperties {
    /**
     * 是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
     */

    @Value(value = "${swagger.config.enabled:true}")
    private Boolean swaggerEnabled;
    /**
     *  指定swagger扫描的默认包路径
     */
    @Value("#{'${swagger.config.base.package:}'.split(',')}")
    private List<String> basePackages;
    /**
     * 指定swagger api标题名称
     */
    @Value(value = "${swagger.config.api.title:}")
    private String apiTitle;
    /**
     * 指定swagger api 版本号
     */
    @Value(value = "${swagger.config.api.version:1.0.0}")
    private String apiVersion;
    /**
     * 指定swagger api 版本号
     */
    @Value(value = "${swagger.config.api.desc:}")
    private String apiDesc;
    @Value("#{${swagger.config.api.author:{'name':'','url':'','email':''}}}")
    private Map<String,String> apiAuthor;
}
