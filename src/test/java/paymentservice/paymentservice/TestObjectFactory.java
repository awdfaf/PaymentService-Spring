package paymentservice.paymentservice;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import paymentservice.paymentservice.exrate.CachedExRateProvider;
import paymentservice.paymentservice.exrate.WebApiExRateProvider;
import paymentservice.paymentservice.payment.ExRateProvider;
import paymentservice.paymentservice.payment.ExRateProviderStub;
import paymentservice.paymentservice.payment.PaymentService;

import static java.math.BigDecimal.valueOf;

@Configuration
public class TestObjectFactory {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }
    @Bean
    public ExRateProvider exRateProvider() {
        return new ExRateProviderStub(valueOf(1000));
    }

}
