package com.yarmak;

import practice.SpringConfig;
import com.yarmak.rates.RatesProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RatesApp {

    public static void main(String[] args) throws Exception {
    new AnnotationConfigApplicationContext(SpringConfig.class)
            .getBean(RatesProcessor.class).scheduleGoOverAgents();
    }

}
