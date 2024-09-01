package paymentservice.paymentservice;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import paymentservice.paymentservice.api.ApiTemplate;
import paymentservice.paymentservice.api.ErApiExRateExtractor;
import paymentservice.paymentservice.api.SimpleApiExecutor;
import paymentservice.paymentservice.exrate.CachedExRateProvider;
import paymentservice.paymentservice.payment.ExRateProvider;
import paymentservice.paymentservice.exrate.WebApiExRateProvider;
import paymentservice.paymentservice.payment.PaymentService;

import java.time.Clock;

@Configuration
public class PaymentConfig {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider(), clock());
    }
    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public ApiTemplate apiTemplate() {
        return new ApiTemplate(new SimpleApiExecutor(), new ErApiExRateExtractor());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider(apiTemplate());
    }

    @Bean
    public Clock clock() { return  Clock.systemDefaultZone(); }

}
