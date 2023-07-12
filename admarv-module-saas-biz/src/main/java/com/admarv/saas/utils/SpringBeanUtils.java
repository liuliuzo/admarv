package com.admarv.saas.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 * @author liuliu
 *
 */
@Component
public class SpringBeanUtils implements ApplicationContextAware {
    
    private static ApplicationContext applicationContext;
    
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    // 获取bean实例
    public static <T> T getBean(Class<T> clazz) {
        if (applicationContext == null) {
            throw new IllegalStateException("ApplicationContext is not set.");
        }
        return applicationContext.getBean(clazz);
    }

    // 根据名称获取bean实例
    public static <T> T getBean(String name, Class<T> clazz) {
        if (applicationContext == null) {
            throw new IllegalStateException("ApplicationContext is not set.");
        }
        return applicationContext.getBean(name, clazz);
    }
}
