package com.yarmak;

import com.yarmak.entries.CurrencyPair;
import com.yarmak.processor.CurrencyParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Map;

@Component
public class RatesGrabber {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final CurrencyParser currencyParser;

    @Autowired
    public RatesGrabber(final CurrencyParser currencyParser) {
        this.currencyParser = currencyParser;
    }

    @Scheduled(fixedDelay = 30 * 1000)
    public void runRatesGrabber() {
        final Map<Currency, CurrencyPair> rates = this.currencyParser.parseCurrency("https://www.aval.ua", new ArrayList<>());
        LOGGER.info("Rates are: {}", rates);
    }

}
