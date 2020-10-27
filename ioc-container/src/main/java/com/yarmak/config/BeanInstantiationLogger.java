package com.yarmak.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

public class BeanInstantiationLogger implements BeanPostProcessor, Ordered {

    @Override
    public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
        System.out.println(String.format("Bean \'%s\' of type \'%s\' is about to be instantiated!", beanName, bean.getClass()));
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
        System.out.println(String.format("Bean \'%s\' of type \'%s\' has been instantiated!", beanName, bean.getClass()));
        return bean;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
}
