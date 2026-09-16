package application;

import domain.exception.InvalidCpfException;
import domain.model.Manager;
import domain.model.Salesperson;
import domain.model.Secretary;
import domain.repository.EmployeeRepository;
import domain.service.EmployeeService;
import ui.ConsoleMenu;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        EmployeeService employeeService = new EmployeeService(employeeRepository);

        seedInitialEmployees(employeeService);

        try (Scanner scanner = new Scanner(System.in)) {
            ConsoleMenu consoleMenu = new ConsoleMenu(employeeService, scanner);
            consoleMenu.run();
        }
    }

    private static void seedInitialEmployees(EmployeeService employeeService) {
        try {
            Secretary secretary = new Secretary("John", new BigDecimal("3000.00"), "17534557070",
                    LocalDate.of(2022, 1, 10), true);
            Manager manager = new Manager("Mary", new BigDecimal("4000.00"), "17532257010",
                    LocalDate.of(2021, 3, 15), "password123");
            Salesperson salesperson = new Salesperson("Charles", new BigDecimal("4000.00"), "17533550790",
                    LocalDate.of(2023, 6, 20));

            employeeService.registerEmployee(secretary);
            employeeService.registerEmployee(manager);
            employeeService.registerEmployee(salesperson);
        } catch (InvalidCpfException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }
    }
}
