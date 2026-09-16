package domain.repository;

import domain.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();

    public void save(Employee employee) {
        employees.add(employee);
    }

    public boolean existsByCpf(String cpf) {
        return findByCpf(cpf).isPresent();
    }

    public Optional<Employee> findByCpf(String cpf) {
        return employees.stream()
                .filter(employee -> employee.getCpf().equals(cpf))
                .findFirst();
    }

    public boolean deleteByCpf(String cpf) {
        return employees.removeIf(employee -> employee.getCpf().equals(cpf));
    }

    public List<Employee> findAll() {
        return new ArrayList<>(employees);
    }
}
