package practice;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

    @Bean
    public InvocationTarget invocationTarget() {
        return Mockito.mock(InvocationTarget.class);
    }
}
