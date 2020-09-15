package com.yarmak.rates;

import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;


public class MonitoredCurrencies {

    static final Currency USD = Currency.getInstance("USD");
    static final Currency EUR = Currency.getInstance("EUR");

    static final List<Currency> SUPPORTED_CURRENCIES = List.of(USD, EUR);

    private final List<Currency> observedCurrencies;

    public MonitoredCurrencies(final String[] currencyCodes) {
        this.observedCurrencies = Arrays.stream(currencyCodes)
                .map(Currency::getInstance)
                .peek(c -> {
                    if (!SUPPORTED_CURRENCIES.contains(c)) {
                        throw new IllegalArgumentException(String.format("%s is unsupported!", c));
                    }
                })
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Currency> getObservedCurrencies() {
        return this.observedCurrencies;
    }
}
