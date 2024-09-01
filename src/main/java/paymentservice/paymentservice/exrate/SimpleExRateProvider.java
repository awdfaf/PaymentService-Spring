package paymentservice.paymentservice.exrate;

import paymentservice.paymentservice.payment.ExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;

public class SimpleExRateProvider implements ExRateProvider {
    @Override
    public BigDecimal getExRate(String currency) {
        if (currency.equals("USD")) return BigDecimal.valueOf(1200);
        throw new IllegalArgumentException("지원되지 않는 통화입니다.");
    }
}
