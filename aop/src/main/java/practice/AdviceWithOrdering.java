package practice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AdviceWithOrdering {

    @Pointcut("execution(* BankProviderForOrdering.printStatement())")
    void executionPointcutForOrdering() {}

    @Before("executionPointcutForOrdering()")
    public void beforeAdviceForOrderingTest() {
        System.out.println("Before advice call");
    }

    @After("executionPointcutForOrdering()")
    public void afterAdviceForOrderingTest() {
        System.out.println("After advice call");
    }

    @Around("executionPointcutForOrdering()")
    public void aroundAdviceForOrderingTest(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Before around advice call");
        pjp.proceed();
        System.out.println("After around advice call");
    }

    @AfterReturning("executionPointcutForOrdering()")
    public void afterReturningAdviceForOrderingTest() {
        System.out.println("After returning advice call");
    }

}
