package ui;

import domain.exception.EmployeeNotFoundException;
import domain.exception.InvalidCpfException;
import domain.exception.InvalidDateException;
import domain.exception.NotAManagerException;
import domain.model.Employee;
import domain.model.Secretary;
import domain.service.EmployeeService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private final EmployeeService employeeService;
    private final Scanner scanner;

    public ConsoleMenu(EmployeeService employeeService, Scanner scanner) {
        this.employeeService = employeeService;
        this.scanner = scanner;
    }

    public void run() {
        int option;
        do {
            printMenu();
            option = readOption();

            try {
                switch (option) {
                    case 1 -> registerSecretary();
                    case 2 -> removeEmployee();
                    case 3 -> listEmployees();
                    case 4 -> searchEmployeeByCpf();
                    case 5 -> authenticateManager();
                    case 6 -> showMonthlyPayroll();
                    case 7 -> listEmployeesByPeriod();
                    case 0 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid option!");
                }
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        } while (option != 0);
    }

    private void printMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1 - Register Employee (Secretary)");
        System.out.println("2 - Remove Employee");
        System.out.println("3 - List Employees");
        System.out.println("4 - Search Employee by CPF");
        System.out.println("5 - Authenticate Manager");
        System.out.println("6 - Show Monthly Payroll");
        System.out.println("7 - List Employees by Period");
        System.out.println("0 - Exit");
        System.out.print("Choose: ");
    }

    private int readOption() {
        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    private void registerSecretary() {
        try {
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Salary: ");
            BigDecimal salary = new BigDecimal(scanner.nextLine().trim());
            System.out.print("Hire date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            System.out.print("Has language bonus (true/false): ");
            boolean languageBonus = Boolean.parseBoolean(scanner.nextLine().trim());

            Employee newEmployee = new Secretary(name, salary, cpf, date, languageBonus);
            employeeService.registerEmployee(newEmployee);
            System.out.println("Employee registered!");
        } catch (InvalidCpfException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid salary! Use a numeric value, e.g. 3000.00");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date! Use the YYYY-MM-DD format.");
        }
    }

    private void removeEmployee() {
        try {
            System.out.print("CPF of the employee to remove: ");
            String cpfToRemove = scanner.nextLine();
            employeeService.removeEmployee(cpfToRemove);
            System.out.println("Employee removed.");
        } catch (EmployeeNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listEmployees() {
        List<Employee> employees = employeeService.listEmployees();
        for (Employee e : employees) {
            System.out.println(e.getName() + " - CPF: " + e.getCpf() + " - Salary: " + e.calculateSalary());
        }
    }

    private void searchEmployeeByCpf() {
        try {
            System.out.print("CPF to search: ");
            String cpfToSearch = scanner.nextLine();
            Employee found = employeeService.findEmployeeByCpf(cpfToSearch);
            System.out.println("Found: " + found.getName());
        } catch (EmployeeNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void authenticateManager() {
        try {
            System.out.print("Manager's CPF: ");
            String managerCpf = scanner.nextLine();
            System.out.print("Enter the password: ");
            String password = scanner.nextLine();
            if (employeeService.authenticateManager(managerCpf, password)) {
                System.out.println("Correct password!");
            } else {
                System.out.println("Incorrect password!");
            }
        } catch (EmployeeNotFoundException | NotAManagerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void showMonthlyPayroll() {
        System.out.println("Total monthly payroll: " + employeeService.calculateMonthlyPayroll());
    }

    private void listEmployeesByPeriod() {
        try {
            System.out.print("Start date (YYYY-MM-DD): ");
            LocalDate start = LocalDate.parse(scanner.nextLine());
            System.out.print("End date (YYYY-MM-DD): ");
            LocalDate end = LocalDate.parse(scanner.nextLine());
            List<Employee> employees = employeeService.listEmployeesByPeriod(start, end);
            for (Employee e : employees) {
                System.out.println(e.getName() + " - Hired: " + e.getHireDate());
            }
        } catch (InvalidDateException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date! Use the YYYY-MM-DD format.");
        }
    }
}
