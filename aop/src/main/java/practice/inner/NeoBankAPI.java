package practice.inner;

import org.springframework.stereotype.Service;

@Service
public class NeoBankAPI {

    public int retrieveTotalAmountForAccount() {
        System.out.println("Invocation of retrieveTotalAmountForAccount()");
        return 1000;
    }

    public String[] getStatement() {
        System.out.println("Invocation of getStatement()");
        throw new RuntimeException("Something went wrong!");
    }
}
