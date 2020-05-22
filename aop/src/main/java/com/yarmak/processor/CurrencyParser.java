package com.yarmak.processor;

import com.yarmak.HtmlParsingException;
import com.yarmak.client.BasicClient;
import com.yarmak.entries.CurrencyPair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CurrencyParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final String RATE_USD = "rate-numbers-usd";
    private static final String RATE_EUR = "rate-numbers-eur";

    private final BasicClient client;

    public CurrencyParser(final BasicClient client) {
        this.client = client;
    }

    public Map<Currency, CurrencyPair> parseCurrency(final String url, final List<Currency> currenciesToParse) {
        LOGGER.debug(String.format("Getting rates from %s", url));
        final String htmlBody = this.client.sendGet(url);
        Document doc = Jsoup.parse(htmlBody);
        final Elements currenciesSection = doc.select("div.rate-numbers");
        final Map<Currency, CurrencyPair> rates = new HashMap<>();
        for (final Element e : currenciesSection) {
            try {
                if (e.hasClass(RATE_USD)) {
                    rates.put(Currency.getInstance("USD"), parseCurrencyPair(getHtmlCurrencyPair(e)));
                } else if (e.hasClass(RATE_EUR)) {
                    rates.put(Currency.getInstance("EUR"), parseCurrencyPair(getHtmlCurrencyPair(e)));
                }
            } catch (HtmlParsingException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return rates;
    }

    private Elements getHtmlCurrencyPair(final Element element) throws HtmlParsingException {
        final Elements htmlCurPair = element.select("span");
        if (htmlCurPair.size() != 2) {
            throw new HtmlParsingException(String.format("Unable to parse element with class \"%s\"", RATE_USD));
        }
        return htmlCurPair;
    }

    private CurrencyPair parseCurrencyPair(final Elements elements) {
        return new CurrencyPair(new BigDecimal(elements.get(0).text()), new BigDecimal(elements.get(1).text()));
    }


}
