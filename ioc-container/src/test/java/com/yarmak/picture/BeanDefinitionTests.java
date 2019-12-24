package com.yarmak.picture;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanDefinitionTests {

    @Test
    public void test() {
        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("xml-beans/context.xml");
        final ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        final BeanDefinition pictureProcessorBeanDefinition = beanFactory.getBeanDefinition("pictureProcessor");
        System.out.println(pictureProcessorBeanDefinition);
    }
}
