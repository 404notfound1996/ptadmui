package com.goshine.ptadmui.cache.ehcache;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * EhCache配置管理
 * @author litao
 * @date 2019-11-12
 */
@Configuration
public class EhCacheConfig {
    @Value("${cache.ehcache.configFilePath:classpath:config/ehcache-shiro.xml}")
    private String configFilePath;
    /**
     * 注入缓存管理器;
     */
    @Bean
    public EhCacheManager ehCacheManager(){
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile(configFilePath);
        return cacheManager;
    }
}
