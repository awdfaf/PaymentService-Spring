package paymentservice.paymentservice;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Sort {
    public List<String> sortByLenght(List<String> list) {
        list.sort((o1, o2) -> o1.length() - o2.length());
        return list;
    }
}
