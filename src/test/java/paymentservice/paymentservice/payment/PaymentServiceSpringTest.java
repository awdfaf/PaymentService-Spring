package paymentservice.paymentservice.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import paymentservice.paymentservice.ObjectFactory;
import paymentservice.paymentservice.TestObjectFactory;

import java.io.IOException;
import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestObjectFactory.class)
class PaymentServiceSpringTest {
    @Autowired PaymentService paymentService;
    @Autowired ExRateProviderStub exRateProviderStub;

    @Test
    void convertedAmount() throws IOException {
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

        // 원화환산금액의 유효시간 계산
//        Assertions.assertThat(payment.getValidUntil()).isNotNull();
    }


}