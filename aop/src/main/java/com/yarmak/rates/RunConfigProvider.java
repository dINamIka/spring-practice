package com.yarmak.rates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RunConfigProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    static final Currency USD = Currency.getInstance("USD");
    static final Currency EUR = Currency.getInstance("EUR");
    static final List<Currency> SUPPORTED_CURRENCIES = List.of(USD, EUR);

    private RunConfig runConfig;

    public RunConfigProvider(@Value("${init.monitoring.currencies}") final String[] currencyCodes,
                             @Value("${init.interval.sec}") final int intervalSec) {
        buildRunConfig(currencyCodes, intervalSec);
    }

    private void buildRunConfig(final String[] currencyCodes,
                                final int interval) {
        final List<Currency> monitoredCurrencies = parseCurrencies(currencyCodes);
        synchronized (this) {
            this.runConfig = new RunConfig(interval, monitoredCurrencies);
            LOGGER.info("Current run config: {}", this.runConfig);
        }
    }

    private List<Currency> parseCurrencies(final String[] currencyCodes) {
        return Arrays.stream(currencyCodes)
                .map(Currency::getInstance)
                .peek(c -> {
                    if (!SUPPORTED_CURRENCIES.contains(c)) {
                        throw new IllegalArgumentException(String.format("%s is unsupported!", c));
                    }
                })
                .collect(Collectors.toUnmodifiableList());
    }

    public synchronized RunConfig getRunConfig() {
        return this.runConfig;
    }
}
