package service;

import domain.exception.EmployeeNotFoundException;
import domain.exception.InvalidCpfException;
import domain.exception.InvalidDateException;
import domain.exception.NotAManagerException;
import domain.model.Employee;
import domain.model.Manager;
import domain.model.Salesperson;
import domain.model.Secretary;
import domain.repository.EmployeeRepository;
import domain.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeServiceTest {

    private static final String VALID_CPF_1 = "52998234100";
    private static final String VALID_CPF_2 = "36879614515";
    private static final String VALID_CPF_3 = "91234567873";
    private static final String INVALID_CPF = "12345678900";

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService(new EmployeeRepository());
    }

    @Test
    void rejectsDuplicateCpfRegistration() throws InvalidCpfException {
        employeeService.registerEmployee(new Salesperson("Charles", new BigDecimal("4000.00"),
                VALID_CPF_1, LocalDate.of(2023, 6, 20)));

        Employee duplicate = new Salesperson("Anna", new BigDecimal("3500.00"),
                VALID_CPF_1, LocalDate.of(2023, 7, 1));

        assertThrows(InvalidCpfException.class, () -> employeeService.registerEmployee(duplicate));
    }

    @Test
    void rejectsInvalidCpfFormat() {
        Employee employee = new Salesperson("Charles", new BigDecimal("4000.00"),
                INVALID_CPF, LocalDate.of(2023, 6, 20));

        assertThrows(InvalidCpfException.class, () -> employeeService.registerEmployee(employee));
    }

    @Test
    void throwsWhenSearchingForNonExistentEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployeeByCpf(VALID_CPF_1));
    }

    @Test
    void throwsWhenRemovingNonExistentEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.removeEmployee(VALID_CPF_1));
    }

    @Test
    void calculatesMonthlyPayrollAsTheSumOfEveryEmployeeSalary() throws InvalidCpfException {
        employeeService.registerEmployee(new Secretary("John", new BigDecimal("3000.00"),
                VALID_CPF_1, LocalDate.of(2022, 1, 10), true));
        employeeService.registerEmployee(new Manager("Mary", new BigDecimal("4000.00"),
                VALID_CPF_2, LocalDate.of(2021, 3, 15), "password123"));
        employeeService.registerEmployee(new Salesperson("Charles", new BigDecimal("4000.00"),
                VALID_CPF_3, LocalDate.of(2023, 6, 20)));

        BigDecimal expected = new BigDecimal("3500.00")
                .add(new BigDecimal("12000.00"))
                .add(new BigDecimal("6000.00"));

        assertEquals(expected, employeeService.calculateMonthlyPayroll());
    }

    @Test
    void authenticatesManagerWithCorrectPassword() throws InvalidCpfException {
        employeeService.registerEmployee(new Manager("Mary", new BigDecimal("4000.00"),
                VALID_CPF_1, LocalDate.of(2021, 3, 15), "password123"));

        assertTrue(employeeService.authenticateManager(VALID_CPF_1, "password123"));
        assertFalse(employeeService.authenticateManager(VALID_CPF_1, "wrong-password"));
    }

    @Test
    void throwsWhenAuthenticatingAnEmployeeThatIsNotAManager() throws InvalidCpfException {
        employeeService.registerEmployee(new Salesperson("Charles", new BigDecimal("4000.00"),
                VALID_CPF_1, LocalDate.of(2023, 6, 20)));

        assertThrows(NotAManagerException.class,
                () -> employeeService.authenticateManager(VALID_CPF_1, "any-password"));
    }

    @Test
    void listsOnlyEmployeesHiredWithinTheGivenPeriod() throws InvalidCpfException {
        employeeService.registerEmployee(new Secretary("John", new BigDecimal("3000.00"),
                VALID_CPF_1, LocalDate.of(2022, 1, 10), true));
        employeeService.registerEmployee(new Salesperson("Charles", new BigDecimal("4000.00"),
                VALID_CPF_2, LocalDate.of(2023, 6, 20)));

        List<Employee> employees = employeeService.listEmployeesByPeriod(
                LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31));

        assertEquals(1, employees.size());
        assertEquals(VALID_CPF_2, employees.get(0).getCpf());
    }

    @Test
    void rejectsAPeriodWhoseStartDateIsAfterItsEndDate() {
        assertThrows(InvalidDateException.class, () -> employeeService.listEmployeesByPeriod(
                LocalDate.of(2023, 12, 31), LocalDate.of(2023, 1, 1)));
    }
}
