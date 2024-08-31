package paymentservice.paymentservice.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import paymentservice.paymentservice.exrate.WebApiExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
class PaymentServiceTest {

    @Test
    void prepare() throws IOException {
        PaymentService paymentService = new PaymentService((new WebApiExRateProvider()));

        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.TEN);

        // 환율정보 가져온다
        Assertions.assertThat(payment.getExRate()).isNotNull();
        // 원화환산금액 계산
        Assertions.assertThat(payment.getConvertedAmount())
                .isEqualTo(payment.getExRate().multiply(payment.getForeignCurrencyAmount()));
        // 원화환산금액의 유효시간 계산
        Assertions.assertThat(payment.getValidUntil()).isNotNull();
    }
}