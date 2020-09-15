package practice;

public interface RatesProvider {

    int getRateForUSD();

    int getRateForEUR();

    int getAverateRateForUSD();

    int getAverateRateForEUR();

    int[] getHistoricalDateForUSD();

    int[] getHistoricalDateForEUR();
}
