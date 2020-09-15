package com.yarmak.picture;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ContextAwarePictureProcessor implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public DummyPictureRepository getDummyPictureRepository() {
        return this.applicationContext.getBean(DummyPictureRepository.class);
    }

}
