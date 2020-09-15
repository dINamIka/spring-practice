package practice;

//@Component is removed intentionally
public class InvocationTarget {

    public void analyzeRates() {}

    public void compareRates() {}

    public void verifyRateIsValid() {
        System.out.println("Invocation of verifyRateIsValid()");
    }

    public void isValid() {}

    public void checkThePrevious() {
        System.out.println("Invocation of checkThePrevious()");
    }
}
