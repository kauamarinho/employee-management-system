package domain.service;

import domain.exception.EmployeeNotFoundException;
import domain.exception.InvalidCpfException;
import domain.exception.InvalidDateException;
import domain.exception.NotAManagerException;
import domain.model.Employee;
import domain.model.Manager;
import domain.repository.EmployeeRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void registerEmployee(Employee employee) throws InvalidCpfException {
        if (!isValidCpf(employee.getCpf())) {
            throw new InvalidCpfException("Invalid CPF: " + employee.getCpf());
        }
        if (employeeRepository.existsByCpf(employee.getCpf())) {
            throw new InvalidCpfException("CPF already registered: " + employee.getCpf());
        }
        employeeRepository.save(employee);
    }

    public void removeEmployee(String cpf) {
        if (!employeeRepository.deleteByCpf(cpf)) {
            throw new EmployeeNotFoundException("Employee with CPF " + cpf + " not found.");
        }
    }

    public Employee findEmployeeByCpf(String cpf) {
        return employeeRepository.findByCpf(cpf)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with CPF " + cpf + " not found."));
    }

    public List<Employee> listEmployees() {
        return employeeRepository.findAll();
    }

    public BigDecimal calculateMonthlyPayroll() {
        return employeeRepository.findAll().stream()
                .map(Employee::calculateSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Employee> listEmployeesByPeriod(LocalDate start, LocalDate end) {
        if (start.isAfter(end)) {
            throw new InvalidDateException("Start date cannot be after end date.");
        }
        return employeeRepository.findAll().stream()
                .filter(employee -> !employee.getHireDate().isBefore(start) && !employee.getHireDate().isAfter(end))
                .collect(Collectors.toList());
    }

    public boolean authenticateManager(String cpf, String password) {
        Employee employee = findEmployeeByCpf(cpf);
        if (!(employee instanceof Manager manager)) {
            throw new NotAManagerException("CPF " + cpf + " does not belong to a manager.");
        }
        return manager.authenticate(password);
    }

    private boolean isValidCpf(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            return false;
        }
        if (cpf.chars().distinct().count() == 1) {
            return false;
        }
        int[] digits = cpf.chars().map(c -> c - '0').toArray();
        int firstCheckDigit = calculateCheckDigit(digits, 9);
        int secondCheckDigit = calculateCheckDigit(digits, 10);
        return digits[9] == firstCheckDigit && digits[10] == secondCheckDigit;
    }

    private int calculateCheckDigit(int[] digits, int length) {
        int sum = 0;
        int weight = length + 1;
        for (int i = 0; i < length; i++) {
            sum += digits[i] * weight;
            weight--;
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }
}
