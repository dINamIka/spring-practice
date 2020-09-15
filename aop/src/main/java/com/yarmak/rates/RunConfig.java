package com.yarmak.rates;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Currency;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class RunConfig {

    private final int intervalSec;
    private final List<Currency> monitoredCurrencies;

    @Override
    public String toString() {
        return String.format("Currencies to monitor: %s with interval: %s seconds.",
                this.monitoredCurrencies, this.intervalSec);
    }
}
