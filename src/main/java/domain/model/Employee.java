package domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Employee {
    private String name;
    private BigDecimal baseSalary;
    private final String cpf;
    private LocalDate hireDate;

    protected Employee(String name, BigDecimal baseSalary, String cpf, LocalDate hireDate) {
        this.name = name;
        this.baseSalary = baseSalary;
        this.cpf = cpf;
        this.hireDate = hireDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public abstract BigDecimal calculateSalary();
}
