package paymentservice.paymentservice.exrate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Map;

// @JsonIgnoreProperties(ignoreUnknown = true)를 사용하면 json 데이터에 포함되어 있지 않은 필드는 무시하고
// 포함되어 있는 필드만 사용할 수 있음
@JsonIgnoreProperties(ignoreUnknown = true)
public record ExRateData(String result, Map<String, BigDecimal> rates) {
}
