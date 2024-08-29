package paymentservice.paymentservice;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) throws IOException {
        //PaymentService paymentService = new WebApiExRatePaymentService();
        //확장
        PaymentService paymentService = new SimpleExRatePaymentService();
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));

        // toString()을 오버라이드해서 출력
        System.out.println(payment);
    }
}
