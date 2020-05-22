package com.yarmak;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(SpringConfig.class);
    }

}
