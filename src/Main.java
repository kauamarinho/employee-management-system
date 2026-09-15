import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Company company = new Company();
        Scanner sc = new Scanner(System.in);
        int option;

        try {
            Secretary s = new Secretary("João", 3000.0, "17534557089", LocalDate.of(2022, 1, 10), true);
            Manager m = new Manager("Maria", 4000.0, "17532257090", LocalDate.of(2021, 3, 15), "senha123");
            Salesperson v = new Salesperson("Carlos", 4000.0, "1753355079", LocalDate.of(2023, 6, 20));

            company.addEmployee(s);
            company.addEmployee(m);
            company.addEmployee(v);

        } catch (InvalidCpfException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }

        do {
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
            option = sc.nextInt();
            sc.nextLine();

            try {
                switch (option) {
                    case 1:
                        try {
                            System.out.print("Name: ");
                            String name = sc.nextLine();
                            System.out.print("CPF: ");
                            String cpf = sc.nextLine();
                            System.out.print("Salary: ");
                            double salary = sc.nextDouble();
                            sc.nextLine();
                            System.out.print("Hire date (YYYY-MM-DD): ");
                            LocalDate date = LocalDate.parse(sc.nextLine());
                            System.out.print("Has language bonus (true/false): ");
                            boolean languageBonus = sc.nextBoolean();

                            Employee newEmployee = new Secretary(name, salary, cpf, date, languageBonus);
                            company.addEmployee(newEmployee);
                            System.out.println("Employee registered!");
                        } catch (InvalidCpfException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 2:
                        try {
                            System.out.print("CPF of the employee to remove: ");
                            String cpfToRemove = sc.nextLine();
                            company.removeEmployee(cpfToRemove);
                            System.out.println("Employee removed.");
                        } catch (EmployeeNotFoundException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 3:
                        company.listEmployees();
                        break;

                    case 4:
                        try {
                            System.out.print("CPF to search: ");
                            String cpfToSearch = sc.nextLine();
                            Employee found = company.findEmployeeByCpf(cpfToSearch);
                            System.out.println("Found: " + found.getName());
                        } catch (EmployeeNotFoundException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 5:
                        try {
                            System.out.print("Manager's CPF: ");
                            String managerCpf = sc.nextLine();
                            Employee employee = company.findEmployeeByCpf(managerCpf);
                            if (employee instanceof Manager m) {
                                System.out.print("Enter the password: ");
                                String password = sc.nextLine();
                                if (m.authenticate(password)) {
                                    System.out.println("Correct password!");
                                } else {
                                    System.out.println("Incorrect password!");
                                }
                            } else {
                                System.out.println("This CPF does not belong to a manager.");
                            }
                        } catch (EmployeeNotFoundException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 6:
                        System.out.println("Total monthly payroll: " + company.calculateMonthlyPayroll());
                        break;

                    case 7:
                        try {
                            System.out.print("Start date (YYYY-MM-DD): ");
                            LocalDate start = LocalDate.parse(sc.nextLine());
                            System.out.print("End date (YYYY-MM-DD): ");
                            LocalDate end = LocalDate.parse(sc.nextLine());
                            company.listEmployeesByPeriod(start, end);
                        } catch (InvalidDateException e) {
                            System.out.println("Error: " + e.getMessage());
                        } catch (Exception e) {
                            System.out.println("Invalid date! Use the YYYY-MM-DD format.");
                        }
                        break;

                    case 0:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid option!");
                }
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        } while (option != 0);

        sc.close();
    }
}
