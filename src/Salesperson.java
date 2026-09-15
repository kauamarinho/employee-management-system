import java.time.LocalDate;

public class Salesperson extends Employee {
    public Salesperson(String name, double baseSalary, String cpf, LocalDate hireDate) {
        super(name, baseSalary, cpf, hireDate);
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + 2000.0;
    }
}
