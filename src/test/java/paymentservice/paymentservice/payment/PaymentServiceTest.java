package paymentservice.paymentservice.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

class PaymentServiceTest {

    @Test
    void convertedAmount() throws IOException {
        testAmount(BigDecimal.valueOf(1200), BigDecimal.valueOf(12000));
        testAmount(BigDecimal.valueOf(1300), BigDecimal.valueOf(13000));


        // 원화환산금액의 유효시간 계산
//        Assertions.assertThat(payment.getValidUntil()).isNotNull();
    }

    private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
        PaymentService paymentService = new PaymentService((new ExRateProviderStub(exRate)));

        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.TEN);
        // 환율정보 가져온다
        Assertions.assertThat(payment.getExRate()).isEqualByComparingTo(exRate);
        // 원화환산금액 계산
        Assertions.assertThat(payment.getConvertedAmount())
                .isEqualByComparingTo(convertedAmount);

    }
}