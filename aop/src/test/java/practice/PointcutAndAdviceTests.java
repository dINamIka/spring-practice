package practice;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import practice.inner.NeoBankAPI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, TestConfiguration.class})
public class PointcutAndAdviceTests {

    @Autowired
    private RatesProvider ratesProvider;
    @Autowired
    private NeoBankAPI neoBankAPI;
    @Autowired
    private BankProviderForOrdering orderingBankProvider;
    @Autowired
    private InvocationTarget delegatedExecutorMock;
    
    @Autowired
    private ApplicationContext context;

    @Before
    public void setUp() throws Exception {
        Mockito.reset(delegatedExecutorMock);
    }

    @Test
    public synchronized void whenExecutionDesignatorWithExplicitNameUsed_shouldCallCompareRatesMethodOnce() {
        ratesProvider.getRateForUSD();
        Mockito.verify(delegatedExecutorMock, Mockito.times(1)).compareRates();
    }

    @Test
    public synchronized void whenWithinDesignatorWithSpecifiedClassUsed_shouldCallAnalyzeRatesMethodOnce() {
        ratesProvider.getRateForUSD();
        Mockito.verify(delegatedExecutorMock, Mockito.times(1)).analyzeRates();
    }

    @Test
    public void whenWithinPackageDesignatorUsed_shouldCallIsValidMethodOnce() {
        neoBankAPI.retrieveTotalAmountForAccount();
        Mockito.verify(delegatedExecutorMock, Mockito.times(1)).isValid();
    }

    @Test
    public void whenThisDesignatorUsed_withAfterThrowingAdvice_shouldCallVerifyRateIsValid() {
        try {
            neoBankAPI.getStatement();
        } catch (RuntimeException ex) {
        }
        Mockito.verify(delegatedExecutorMock, Mockito.times(2)).verifyRateIsValid();
    }

    @Test
    public void whenWithinAnnotatedDesignatorUsed_withAroundAdvice_shouldCallCheckThePreviousOnce() {
        neoBankAPI.retrieveTotalAmountForAccount();
        ratesProvider.getRateForUSD();
        Mockito.verify(delegatedExecutorMock, Mockito.times(1)).checkThePrevious();
    }

    @Test
    public void whenSameExecutionDesignatorUsedForMultipleAdvices() {
        orderingBankProvider.printStatement();
    }

    @Test
    public void whenDeclareParentsUsed_shouldApplyOnlyForOneBean() {
        Assertions.assertThat(context.getBean(MetricsKeeper.class)).isInstanceOf(NeoBankAPI.class);
    }
}