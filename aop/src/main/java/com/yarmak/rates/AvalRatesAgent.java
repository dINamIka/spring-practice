package com.yarmak.rates;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yarmak.rates.MonitoredCurrencies.EUR;
import static com.yarmak.rates.MonitoredCurrencies.USD;

@Component
public class AvalRatesAgent implements RatesAgent {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String RATE_USD = "rate-numbers-usd";
    private static final String RATE_EUR = "rate-numbers-eur";

    private final URI sourceUrl = URI.create("https://www.aval.ua");
    private final JdkHttpClient client;

    public AvalRatesAgent(final JdkHttpClient client) {
        this.client = client;
    }

    @Override
    public Map<Currency, CurrencyPair> getRates(final List<Currency> currenciesToParse) {
        LOGGER.info(String.format("Asking rates from %s", sourceUrl));
        final String htmlBody = this.client.sendGet(sourceUrl);
        Document doc = Jsoup.parse(htmlBody);
        final Elements currenciesSection = doc.select("div.rate-numbers");
        final Map<Currency, CurrencyPair> rates = new HashMap<>();
        for (final Element e : currenciesSection) {
            try {
                if (currenciesToParse.contains(USD) && e.hasClass(RATE_USD)) {
                    rates.put(USD, parseCurrencyPair(getHtmlCurrencyPair(e)));
                } else if (currenciesToParse.contains(EUR) && e.hasClass(RATE_EUR)) {
                    rates.put(EUR, parseCurrencyPair(getHtmlCurrencyPair(e)));
                }
            } catch (HtmlParsingException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        //TODO: move this logger statement to aspect
        LOGGER.debug("Aval rates: {}", rates);
        return rates;
    }

    private Elements getHtmlCurrencyPair(final Element element) throws HtmlParsingException {
        final Elements htmlCurPair = element.select("span");
        if (htmlCurPair.size() != 3) {
            throw new HtmlParsingException(String.format("Unable to parse element with class \"%s\"", RATE_USD));
        }
        return htmlCurPair;
    }

    private CurrencyPair parseCurrencyPair(final Elements elements) {
        return new CurrencyPair(new BigDecimal(elements.get(0).text()), new BigDecimal(elements.get(1).text()));
    }


}
