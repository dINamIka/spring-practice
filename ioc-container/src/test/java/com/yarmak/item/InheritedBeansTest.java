package com.yarmak.item;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanIsAbstractException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class InheritedBeansTest {

    @Test
    public void whenBeansWithInheritanceSpecified_shouldSucceedAndReturnDifferentValuesForBeans() {
        final BeanFactory beanFactory = new ClassPathXmlApplicationContext("/xml-beans/inherited-beans.xml");
        final ItemManager parentItemManager = beanFactory.getBean("parentBean", ItemManager.class);
        final ItemManager childItemManager = beanFactory.getBean("childBean", ItemManager.class);
        assertThat(parentItemManager.getMaxProcessedItems()).isEqualTo(5);
        assertThat(childItemManager.getMaxProcessedItems()).isEqualTo(10);
    }

    @Test(expected = BeanIsAbstractException.class)
    public void whenGetAbstractBeanCalled_shouldFailAsItsIncomplete() {
        final BeanFactory beanFactory = new ClassPathXmlApplicationContext("/xml-beans/inherited-beans.xml");
        beanFactory.getBean("abstractBean");
    }
}
