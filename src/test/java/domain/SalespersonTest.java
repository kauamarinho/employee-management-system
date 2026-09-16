package domain;

import domain.model.Salesperson;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SalespersonTest {

    @Test
    void calculatesSalaryAsBaseSalaryPlusSalesBonus() {
        Salesperson salesperson = new Salesperson("Charles", new BigDecimal("4000.00"), "17533550790",
                LocalDate.of(2023, 6, 20));

        assertEquals(new BigDecimal("6000.00"), salesperson.calculateSalary());
    }
}
