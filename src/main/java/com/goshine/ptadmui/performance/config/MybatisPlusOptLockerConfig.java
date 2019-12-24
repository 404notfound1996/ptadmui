package com.goshine.ptadmui.performance.config;

import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * <p>
 * </p>
 *
 * @author yuxiaobin
 * @date 2018/8/24
 */
public class MybatisPlusOptLockerConfig implements EnvironmentAware {
    /**
     * 日志是否格式化
     */
    private boolean format;
    /**
     * 打印耗时超过多少的记录
     */
    private long maxTime;
    @Bean
    public PerformanceInterceptor performanceInterceptor(){
        //启用性能分析插件
        PerformanceInterceptor performanceInterceptor= new PerformanceInterceptor();
        performanceInterceptor.setWriteInLog(true);
        performanceInterceptor.setFormat(format);
        performanceInterceptor.setMaxTime(maxTime);
        return performanceInterceptor;
    }

    @Override
    public void setEnvironment(Environment environment) {
        format=Boolean.valueOf(environment.getProperty("mybatis.plus.performanceInterceptor.format","false"));
        maxTime=Integer.valueOf(environment.getProperty("mybatis.plus.performanceInterceptor.maxTime","300"));
    }
}
