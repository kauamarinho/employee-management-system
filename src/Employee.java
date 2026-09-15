import java.time.LocalDate;

public abstract class Employee {
    private String name;
    private double baseSalary;
    private String cpf;
    private LocalDate hireDate;

    public Employee(String name, double baseSalary, String cpf, LocalDate hireDate) {
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

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public final String getCpf() {
        return cpf;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public abstract double calculateSalary();
}
