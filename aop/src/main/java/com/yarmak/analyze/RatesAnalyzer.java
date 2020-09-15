package com.yarmak.analyze;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RatesAnalyzer {

    @Pointcut("execution(* getRates(..))")
    private void analyze() {}

    @After("analyze()")
    public void printTestMessage() {
        System.out.println("Pointcut works!");
    }
}
