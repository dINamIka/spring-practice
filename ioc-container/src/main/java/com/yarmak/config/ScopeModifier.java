package com.yarmak.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Arrays;

public class BeansPrinter implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(final ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("Printin all bean definitions:");
        System.out.println(Arrays.toString(beanFactory.getBeanDefinitionNames()));
        BeanDefinition pictureRepoDef = beanFactory.getBeanDefinition("pictureRepo");
//        pictureRepoDef.setScope("singleton");
    }
}
