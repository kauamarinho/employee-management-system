package domain.model;

import domain.authentication.Authenticatable;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Manager extends Employee implements Authenticatable {
    private static final BigDecimal MANAGEMENT_BONUS = new BigDecimal("8000.00");

    private final String password;

    public Manager(String name, BigDecimal baseSalary, String cpf, LocalDate hireDate, String password) {
        super(name, baseSalary, cpf, hireDate);
        this.password = password;
    }

    @Override
    public BigDecimal calculateSalary() {
        return getBaseSalary().add(MANAGEMENT_BONUS);
    }

    @Override
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}
