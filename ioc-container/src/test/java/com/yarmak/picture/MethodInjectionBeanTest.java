package com.yarmak.picture;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class MethodInjectionBeanTest {

    @Test
    public void whenGetPrototypeBeanFromAC_shoulAlwaysReturnNewInstance() {
        final BeanFactory beanFactory = new ClassPathXmlApplicationContext("/xml-beans/beans-with-scopes.xml");
        final ContextAwarePictureProcessor pictureProcessor = beanFactory.getBean("pictureProcessor", ContextAwarePictureProcessor.class);
        final DummyPictureRepository firstPictureRepo = beanFactory.getBean("pictureRepo", DummyPictureRepository.class);
        for (int i = 0; i < 5; i++) {
            final DummyPictureRepository nextRepo = pictureProcessor.getDummyPictureRepository();
            assertThat(nextRepo).isNotSameAs(firstPictureRepo);
        }
    }


    @Test
    public void whenAbstractMethodInjectionUsed_shouldAlwaysReturnNewInstanceOfBean() {
        final BeanFactory beanFactory = new ClassPathXmlApplicationContext("/xml-beans/beans-with-scopes.xml");
        final AbstractPictureProcessor pictureProcessor = beanFactory.getBean("abstractPicProcessor", AbstractPictureProcessor.class);
        final DummyPictureRepository firstRepo = pictureProcessor.getRepo();
        for (int i = 0; i < 5; i++) {
            final DummyPictureRepository nextRepo = pictureProcessor.getRepo();
            assertThat(nextRepo).isNotSameAs(firstRepo);
        }
    }

    @Test
    public void whenAnnotatedAbstractMethodWithMethodInjectionUsed_shouldAlwaysReturnNewBeanInstance() {
        final BeanFactory beanFactory = new ClassPathXmlApplicationContext("/xml-beans/beans-with-scopes.xml");
        final AnnotationBasedAbstractPictureProcessor pictureProcessor = beanFactory.getBean(AnnotationBasedAbstractPictureProcessor.class);
        final DummyPictureRepository firstRepo = pictureProcessor.getRepo();
        for (int i = 0; i < 5; i++) {
            final DummyPictureRepository nextRepo = pictureProcessor.getRepo();
            assertThat(nextRepo).isNotSameAs(firstRepo);
        }
    }

    @Test
    public void whenNamedAnnotatedAbstractMethodWithMethodInjectionUsed_shouldAlwaysReturnNewBeanInstance() {
        final BeanFactory beanFactory = new ClassPathXmlApplicationContext("/xml-beans/beans-with-scopes.xml");
        final AnnotationBasedAbstractPictureProcessor pictureProcessor = beanFactory.getBean(AnnotationBasedAbstractPictureProcessor.class);
        final Object firstRepo = pictureProcessor.getRepoByName();
        assertThat(firstRepo).isInstanceOf(DummyPictureRepository.class);
        for (int i = 0; i < 5; i++) {
            final DummyPictureRepository nextRepo = pictureProcessor.getRepo();
            assertThat(nextRepo).isInstanceOf(DummyPictureRepository.class);
            assertThat(nextRepo).isNotSameAs(firstRepo);
        }
    }

    @Test
    public void name() {
        final String str = new String("");
    }
}
