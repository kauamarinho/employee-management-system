package domain;

import domain.model.Manager;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ManagerTest {

    @Test
    void calculatesSalaryAsBaseSalaryPlusManagementBonus() {
        Manager manager = new Manager("Mary", new BigDecimal("4000.00"), "17532257010",
                LocalDate.of(2021, 3, 15), "password123");

        assertEquals(new BigDecimal("12000.00"), manager.calculateSalary());
    }

    @Test
    void authenticatesOnlyWithTheCorrectPassword() {
        Manager manager = new Manager("Mary", new BigDecimal("4000.00"), "17532257010",
                LocalDate.of(2021, 3, 15), "password123");

        assertTrue(manager.authenticate("password123"));
        assertFalse(manager.authenticate("wrong-password"));
    }
}
