package paymentservice.paymentservice;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import paymentservice.paymentservice.api.ApiTemplate;
import paymentservice.paymentservice.api.ErApiExRateExtractor;
import paymentservice.paymentservice.api.SimpleApiExecutor;
import paymentservice.paymentservice.exrate.CachedExRateProvider;
import paymentservice.paymentservice.exrate.RestTemplateExRateProvider;
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
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new RestTemplateExRateProvider(restTemplate());
    }

    @Bean
    public Clock clock() { return  Clock.systemDefaultZone(); }

}
