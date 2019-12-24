package com.yarmak.config;

import com.yarmak.picture.DummyPictureRepository;
import com.yarmak.picture.PictureProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.yarmak.picture")
public class JavaOnlyConfig {

    @Bean("pictureRepo")
    DummyPictureRepository dummyPictureRepository() {
        return new DummyPictureRepository("java-config-repository-id-001");
    }

    @Bean
    @Autowired
    PictureProcessor pictureProcessor(final DummyPictureRepository pictureRepository) {
        return new PictureProcessor(pictureRepository);
    }
}
