package com.goshine.ptadmui.core.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *  获取spring上下文
 * @author goshine
 * @since 2018-08-29
 */
@Component
public class SpringContextBean implements ApplicationContextAware {
    private static ApplicationContext context = null;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        context = applicationContext;
    }

    @SuppressWarnings("unchecked")
	public static <T> T getBean(String name)
    {
        return (T)context.getBean(name);
    }

    public static <T> T getBean(Class<T> beanClass){
        return context.getBean(beanClass);
    }
}