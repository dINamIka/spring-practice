package com.yarmak.config;

import com.yarmak.picture.DummyPictureRepository;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class BeanFactoryPostProcessorChecks {

    @Test
    public void initBasicBeanFactoryPostProcessor() {
        final ApplicationContext ac = new ClassPathXmlApplicationContext("test-beans/bean-factory-post-proc.xml");
        System.out.println(String.format("Initialize %s", ac.getApplicationName()));
        final DummyPictureRepository pictureRepo = ac.getBean(DummyPictureRepository.class);
        final DummyPictureRepository anotherPictureRepo = (DummyPictureRepository) ac.getBean("pictureRepo");
        assertThat(pictureRepo).isSameAs(anotherPictureRepo);

        pictureRepo.findAll();
    }
}