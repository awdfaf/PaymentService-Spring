package paymentservice.paymentservice;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import paymentservice.paymentservice.exrate.CachedExRateProvider;
import paymentservice.paymentservice.exrate.WebApiExRateProvider;
import paymentservice.paymentservice.payment.ExRateProvider;
import paymentservice.paymentservice.payment.ExRateProviderStub;
import paymentservice.paymentservice.payment.PaymentService;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static java.math.BigDecimal.valueOf;

@Configuration
public class TestPaymentConfig {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(), clock());
    }
    @Bean
    public ExRateProvider exRateProvider() {
        return new ExRateProviderStub(valueOf(1000));
    }

    @Bean
    public Clock clock() { return  Clock.fixed(Instant.now(), ZoneId.systemDefault()); }

}
