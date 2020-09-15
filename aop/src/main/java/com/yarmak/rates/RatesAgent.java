package com.yarmak.rates;

import java.util.Currency;
import java.util.List;
import java.util.Map;

public interface RatesAgent {
    Map<Currency, CurrencyPair> getRates(List<Currency> currenciesToParse);
}
