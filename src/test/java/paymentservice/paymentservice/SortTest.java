package paymentservice.paymentservice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class SortTest {
    Sort sort;

    @BeforeEach
    void setUp() {
        sort = new Sort();
        System.out.println(this);
    }

    @Test
    void sort() {
        // 실행(when)
        List<String> list = sort.sortByLenght(Arrays.asList("ab", "a"));
        // 검증(then)
        Assertions.assertThat(list).containsExactly("a", "ab");
    }
    @Test
    void sort3Items() {
        // 실행(when)
        List<String> list = sort.sortByLenght(Arrays.asList("a", "abc", "ab"));
        // 검증(then)
        Assertions.assertThat(list).containsExactly("a", "ab", "abc");
    }
    @Test
    void sortAlreadySorted() {
        // 실행(when)
        List<String> list = sort.sortByLenght(Arrays.asList("a", "ab", "abc"));
        // 검증(then)
        Assertions.assertThat(list).containsExactly("a", "ab", "abc");
    }
}
