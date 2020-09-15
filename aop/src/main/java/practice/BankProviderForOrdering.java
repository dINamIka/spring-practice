package practice;

import org.springframework.stereotype.Component;

@Component
public class BankProviderForOrdering {

    public String printStatement() {
        System.out.println("Printing the statement...");
        return "Statement...";
    }
}
