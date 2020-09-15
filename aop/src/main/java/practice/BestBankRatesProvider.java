package practice;

import org.springframework.stereotype.Component;

@Component
public class BestBankRatesProvider implements RatesProvider {

    @Override
    public int getRateForUSD() {
        return 0;
    }

    @Override
    public int getRateForEUR() {
        return 0;
    }

    @Override
    public int getAverateRateForUSD() {
        return 0;
    }

    @Override
    public int getAverateRateForEUR() {
        return 0;
    }

    @Override
    public int[] getHistoricalDateForUSD() {
        return new int[0];
    }

    @Override
    public int[] getHistoricalDateForEUR() {
        return new int[0];
    }
}
