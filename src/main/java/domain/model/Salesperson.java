package domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Salesperson extends Employee {
    private static final BigDecimal SALES_BONUS = new BigDecimal("2000.00");

    public Salesperson(String name, BigDecimal baseSalary, String cpf, LocalDate hireDate) {
        super(name, baseSalary, cpf, hireDate);
    }

    @Override
    public BigDecimal calculateSalary() {
        return getBaseSalary().add(SALES_BONUS);
    }
}
