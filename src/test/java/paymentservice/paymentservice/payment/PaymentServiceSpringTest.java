package paymentservice.paymentservice.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import paymentservice.paymentservice.TestPaymentConfig;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

import static java.math.BigDecimal.valueOf;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestPaymentConfig.class)
class PaymentServiceSpringTest {
    @Autowired PaymentService paymentService;
    @Autowired Clock clock;
    @Autowired ExRateProviderStub exRateProviderStub;

    @Test
    void convertedAmount() {
        // exRate : 1000
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.TEN);

        Assertions.assertThat(payment.getExRate()).isEqualByComparingTo(valueOf(1000));
        Assertions.assertThat(payment.getConvertedAmount())
                .isEqualByComparingTo(valueOf(10000));

        // exRate : 500
        exRateProviderStub.setExRate(valueOf(500));
        Payment payment2 = paymentService.prepare(100L, "USD", BigDecimal.TEN);

        Assertions.assertThat(payment2.getExRate()).isEqualByComparingTo(valueOf(500));
        Assertions.assertThat(payment2.getConvertedAmount())
                .isEqualByComparingTo(valueOf(5000));


    }
    @Test
    void validUntil() {
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.TEN);

        // validUntil이 prepare 30분 뒤로 설정되었는가?
        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expextedValidUntil = now.plusMinutes(30);
        Assertions.assertThat(payment.getValidUntil()).isEqualTo(expextedValidUntil);
    }


}