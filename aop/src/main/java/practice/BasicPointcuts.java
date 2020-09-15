package practice;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BasicPointcuts {

    //modifiers restrict access to pointcut in the same manner as it works for methods
    @Pointcut("execution(* getRateForUSD(..))")
    void simpleExecutionPointcut() {
    }

    @Pointcut("within(practice.BestBankRatesProvider)")
    void simpleWithinPointcut() {
    }

    @Pointcut("within(practice.inner.*)")
    void withinPackagePointcut() {}

    @Pointcut("@within(org.springframework.stereotype.Service)")
    void withinAnnotationPointcut() {}
}
