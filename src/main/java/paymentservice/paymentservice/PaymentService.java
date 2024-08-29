package paymentservice.paymentservice;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

abstract public class PaymentService {
    public Payment prepare(Long  orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        // 환율 가져오기 - 하드 코딩 -> 메소드 추출(리팩터링)
        BigDecimal exRate = getExRate(currency);
        // 금액 계산
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        // 유효 시간 계산
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

    // 환율 정보 가져오기 분리 - 추상클래스
    abstract BigDecimal getExRate(String currency) throws IOException;
}
