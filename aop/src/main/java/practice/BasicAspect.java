package practice;

import lombok.Getter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BasicAspect {

    @Getter
    private InvocationTarget actualExecutor;

    public BasicAspect(final InvocationTarget actualExecutor) {
        this.actualExecutor = actualExecutor;
    }

    @DeclareParents(value="practice.inner.*", defaultImpl=MetricsKeeperImpl.class)
    public static MetricsKeeper metricsKeeper;

    @Before("BasicPointcuts.simpleWithinPointcut()")
    public void runBeforeTarget(final JoinPoint jp) {
        actualExecutor.analyzeRates();
    }

    //package is not necessary as pointcut is in the same package
    @After("practice.BasicPointcuts.simpleExecutionPointcut()")
    public void runAfterTarget() {
        actualExecutor.compareRates();
    }

    @AfterReturning(pointcut = "BasicPointcuts.withinPackagePointcut()", returning = "retVal")
    public void runAfterReturning(final Object retVal) {
        System.out.println(retVal.getClass());
        actualExecutor.isValid();
    }

    @AfterThrowing(pointcut = "this(practice.inner.NeoBankAPI)")
    public void runAfterThrowingThis() {
        System.out.println("Invocation of this designator()");
        actualExecutor.verifyRateIsValid();
    }

    @AfterThrowing(pointcut = "target(practice.inner.NeoBankAPI)", throwing = "ex")
    public void runAfterThrowingTarget(JoinPoint jp, RuntimeException ex) {
        System.out.println("Invocation of target designator()");
        System.out.println(ex.getMessage());
        System.out.println("The 'target' is " + jp.getTarget());
        System.out.println("The 'this' is " + jp.getThis());
        actualExecutor.verifyRateIsValid();
    }

    @Around("BasicPointcuts.withinAnnotationPointcut()")
    public Object runBeforeAnnotatedClassCalled(ProceedingJoinPoint pjp) throws Throwable {
        actualExecutor.checkThePrevious();
        System.out.println("Before join point");
        final Object response = pjp.proceed();
        System.out.println("After join point");
        return response;
    }
}
