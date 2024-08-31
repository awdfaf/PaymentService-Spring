package paymentservice.paymentservice.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.math.BigDecimal.valueOf;

class PaymentServiceTest {
    Clock clock;

    @BeforeEach
    void setUp() {
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    void convertedAmount() throws IOException {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        testAmount(valueOf(1200), valueOf(12000), this.clock);
        testAmount(valueOf(1300), valueOf(13000), this.clock);
    }
    @Test
    void validUntil() throws IOException {
        PaymentService paymentService = new PaymentService((new ExRateProviderStub(valueOf(1000))), clock);

        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.TEN);

        // validUntil이 prepare 30분 뒤로 설정되었는가?
        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expextedValidUntil = now.plusMinutes(30);
        Assertions.assertThat(payment.getValidUntil()).isEqualTo(expextedValidUntil);
    }

    private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount, Clock clock) throws IOException {
        PaymentService paymentService = new PaymentService((new ExRateProviderStub(exRate)), clock);

        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.TEN);
        // 환율정보 가져온다
        Assertions.assertThat(payment.getExRate()).isEqualByComparingTo(exRate);
        // 원화환산금액 계산
        Assertions.assertThat(payment.getConvertedAmount())
                .isEqualByComparingTo(convertedAmount);

    }
}