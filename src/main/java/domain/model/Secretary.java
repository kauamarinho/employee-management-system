package domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Secretary extends Employee {
    private static final BigDecimal LANGUAGE_BONUS = new BigDecimal("500.00");

    private boolean languageBonus;

    public Secretary(String name, BigDecimal baseSalary, String cpf, LocalDate hireDate, boolean languageBonus) {
        super(name, baseSalary, cpf, hireDate);
        this.languageBonus = languageBonus;
    }

    public boolean isLanguageBonus() {
        return languageBonus;
    }

    public void setLanguageBonus(boolean languageBonus) {
        this.languageBonus = languageBonus;
    }

    @Override
    public BigDecimal calculateSalary() {
        return languageBonus ? getBaseSalary().add(LANGUAGE_BONUS) : getBaseSalary();
    }
}
