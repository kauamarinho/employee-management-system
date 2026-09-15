import java.time.LocalDate;

public class Manager extends Employee implements Authenticatable {
    private String password;

    public Manager(String name, double baseSalary, String cpf, LocalDate hireDate, String password) {
        super(name, baseSalary, cpf, hireDate);
        this.password = password;
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + 8000.0;
    }

    @Override
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}
