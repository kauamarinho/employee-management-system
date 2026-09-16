package domain;

import domain.model.Secretary;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SecretaryTest {

    @Test
    void addsLanguageBonusWhenEligible() {
        Secretary secretary = new Secretary("John", new BigDecimal("3000.00"), "17534557070",
                LocalDate.of(2022, 1, 10), true);

        assertEquals(new BigDecimal("3500.00"), secretary.calculateSalary());
    }

    @Test
    void doesNotAddLanguageBonusWhenNotEligible() {
        Secretary secretary = new Secretary("John", new BigDecimal("3000.00"), "17534557070",
                LocalDate.of(2022, 1, 10), false);

        assertEquals(new BigDecimal("3000.00"), secretary.calculateSalary());
    }
}
