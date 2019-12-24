package com.yarmak.picture;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import static com.yarmak.picture.DummyPictureRepository.SNEAKERS_PIC;
import static com.yarmak.picture.DummyPictureRepository.WINTER_SHOES_PIC;
import static com.yarmak.picture.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;


public class XmlBasedConfigurationTests {

    @Test
    public void whenRepositoriesXmlConfigWithClassPathAppContext_shouldReturnCorrectBean() {
        final BeanFactory beanFactory = new ClassPathXmlApplicationContext("/xml-beans/repos.xml");
        final DummyPictureRepository pictureRepo = beanFactory.getBean(PICTURE_REPO_BEAN_ID, DummyPictureRepository.class);
        assertThat(pictureRepo.findBy(WINTER_SHOES_PIC.getId())).isEqualTo(WINTER_SHOES_PIC);
    }

    @Test
    public void whenRepositoriesXmlConfigWithClassPathAppContext_shouldReturnAllAvailableAliases() {
        final BeanFactory beanFactory = new ClassPathXmlApplicationContext("/xml-beans/repos.xml");
        assertThat(beanFactory.getAliases(PICTURE_REPO_BEAN_ID)).containsOnly(PICTURE_REPO_BEAN_NAME, PICTURE_REPO_BEAN_ALIAS);
        assertThat(beanFactory.getAliases(PICTURE_REPO_BEAN_NAME)).containsOnly(PICTURE_REPO_BEAN_ID, PICTURE_REPO_BEAN_ALIAS);
        assertThat(beanFactory.getAliases(PICTURE_REPO_BEAN_ALIAS)).containsOnly(PICTURE_REPO_BEAN_ID, PICTURE_REPO_BEAN_NAME);
    }

    @Test
    public void whenFileSystemXmlAppContextWithSpecifiedScope_shouldReturnBeanWithPrototypeScope() {
        //'classpath:' adds full path to relative one specified for xml config
        final BeanFactory beanFactory = new FileSystemXmlApplicationContext("classpath:/xml-beans/repos.xml");
        assertThat(beanFactory.isPrototype(PICTURE_REPO_BEAN_ID)).isTrue();
        assertThat(beanFactory.isSingleton(PICTURE_REPO_BEAN_ID)).isFalse();
    }

    @Test
    public void whenMultipleXmlConfigUsedAndFileSystemXmlAppContext_shouldReturnCorrectBean() {
        //'classpath:' adds full path to relative one specified for xml config
        final BeanFactory beanFactory = new FileSystemXmlApplicationContext("classpath:/xml-beans/context.xml");
        final PictureProcessor actualBean = beanFactory.getBean("pictureProcessor", PictureProcessor.class);
        assertThat(actualBean.findPictureBy(SNEAKERS_PIC.getId())).isEqualTo(SNEAKERS_PIC);
    }

    @Test(expected = IllegalStateException.class)
    public void whenGenericAppContextUsedAndMultipleContextRefreshUsed_shouldFail() {
        final GenericApplicationContext context = new GenericApplicationContext();
        new XmlBeanDefinitionReader(context).loadBeanDefinitions("xml-beans/repos.xml");
        context.refresh();
        assertThat(context.containsBean("pictureProcessor")).isFalse();
        new XmlBeanDefinitionReader(context).loadBeanDefinitions("xml-beans/context.xml");
        context.refresh();
        //should fail as multiple refresh attempts are not supported
    }

    @Test
    public void whenPrototypeScopedBeansCalled_shouldReturnNewObjectEveryTime() {
        final BeanFactory beanFactory = new ClassPathXmlApplicationContext("xml-beans/repos.xml");
        final DummyPictureRepository firstInstance = beanFactory.getBean(PICTURE_REPO_BEAN_ID, DummyPictureRepository.class);
        final DummyPictureRepository secondInstance = beanFactory.getBean(PICTURE_REPO_BEAN_ID, DummyPictureRepository.class);
        final DummyPictureRepository thirdInstance = beanFactory.getBean(PICTURE_REPO_BEAN_ID, DummyPictureRepository.class);

        assertThat(firstInstance)
                .isNotSameAs(secondInstance)
                .isNotSameAs(thirdInstance);
    }
}