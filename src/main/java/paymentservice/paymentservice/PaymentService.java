package paymentservice.paymentservice;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentService {
    public Payment prepare(Long  orderId, String currency, BigDecimal foreignCurrencyAmount) {
        // 환율 가져오기 - 생략
        // 금액 계산 - 생략
        // 유효 시간 계산 - 생략

        // 임의의 값 넣어주기
        return new Payment(orderId, currency, foreignCurrencyAmount, BigDecimal.ZERO, BigDecimal.ZERO,
                LocalDateTime.now());
    }

    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));

        // toString()을 오버라이드해서 출력
        System.out.println(payment);
    }
}
