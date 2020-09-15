package com.yarmak.rates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class RatesProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final List<RatesAgent> agents;
    private final RunConfigProvider runConfigProvider;
    private AvalRatesAgent ratesConcerned;

    @Autowired
    public RatesProcessor(final List<RatesAgent> agents,
                          final RunConfigProvider runConfigProvider) {
        this.agents = agents;
        this.runConfigProvider = runConfigProvider;
    }

    public void goOverAgents(final RunConfig runConfig) {
        this.agents.forEach(a -> a.getRates(runConfig.getMonitoredCurrencies()));
    }

    //TODO: implement more durable scheduling => if exception is thrown we never continue
    public void scheduleGoOverAgents() throws ExecutionException, InterruptedException {
        final RunConfig runConfig = this.runConfigProvider.getRunConfig();
        Executors.newScheduledThreadPool(1)
                .scheduleWithFixedDelay(() -> goOverAgents(runConfig), 0, runConfig.getIntervalSec(), TimeUnit.SECONDS);
    }

}
