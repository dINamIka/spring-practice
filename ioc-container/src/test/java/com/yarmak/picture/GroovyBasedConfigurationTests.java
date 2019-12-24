package com.yarmak.picture;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.GenericGroovyApplicationContext;

import static com.yarmak.picture.DummyPictureRepository.WINTER_SHOES_PIC;
import static com.yarmak.picture.TestConstants.PICTURE_REPO_BEAN_ID;
import static org.assertj.core.api.Assertions.assertThat;

public class GroovyBasedConfigurationTests {

    @Test
    public void whenGroovyBasedConfigUsed_shouldReturnCorrectBean() {
        final BeanFactory beanFactory = new GenericGroovyApplicationContext("/groovy-beans/repos.groovy");
        final DummyPictureRepository pictureRepo = beanFactory.getBean(PICTURE_REPO_BEAN_ID, DummyPictureRepository.class);
        assertThat(pictureRepo.findBy(WINTER_SHOES_PIC.getId())).isEqualTo(WINTER_SHOES_PIC);
    }
}
