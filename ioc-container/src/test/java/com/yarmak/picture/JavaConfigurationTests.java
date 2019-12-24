package com.yarmak.picture;

import com.yarmak.config.JavaOnlyConfig;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigurationTests {

    @Test
    public void whenOnlyJavaConfigUsed_shouldReturnBeanDefinitionBasedOnFactoryMethodNameInConfig() {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaOnlyConfig.class);
        final ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        BeanDefinition pictureProcessorBD = beanFactory.getBeanDefinition("pictureProcessor");

    }
}
