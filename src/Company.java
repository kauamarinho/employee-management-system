import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Company {
    private List<Employee> employees;

    public Company() {
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee e) throws InvalidCpfException {
        for (Employee existing : employees) {
            if (existing.getCpf().equals(e.getCpf())) {
                throw new InvalidCpfException("CPF already registered: " + e.getCpf());
            }
        }
        employees.add(e);
    }

    public void removeEmployee(String cpf) {
        employees.removeIf(e -> e.getCpf().equals(cpf));
    }

    public Employee findEmployeeByCpf(String cpf) throws EmployeeNotFoundException {
        for (Employee e : employees) {
            if (e.getCpf().equals(cpf)) {
                return e;
            }
        }
        throw new EmployeeNotFoundException("Employee with CPF " + cpf + " not found.");
    }

    public void listEmployees() {
        for (Employee e : employees) {
            System.out.println(e.getName() + " - CPF: " + e.getCpf() + " - Salary: " + e.calculateSalary());
        }
    }

    public double calculateMonthlyPayroll() {
        double total = 0.0;
        for (Employee e : employees) {
            total += e.calculateSalary();
        }
        return total;
    }

    public void listEmployeesByPeriod(LocalDate start, LocalDate end) throws InvalidDateException {
        if (start.isAfter(end)) {
            throw new InvalidDateException("Start date cannot be after end date.");
        }
        for (Employee e : employees) {
            if (!e.getHireDate().isBefore(start) && !e.getHireDate().isAfter(end)) {
                System.out.println(e.getName() + " - Hired: " + e.getHireDate());
            }
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
