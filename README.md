# 👔 Employee Management System

![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![Maven](https://img.shields.io/badge/Build-Maven-blue?logo=apachemaven)
![JUnit 5](https://img.shields.io/badge/Tests-JUnit%205-25A162?logo=junit5)

A console-based Java application for managing employees within a company, built with a layered architecture. The project supports registering, searching, removing, listing, and authenticating employees, and calculating the company's payroll — all backed by in-memory storage.

## 📌 About the Project

The system represents a real-world scenario of employee administration within a company. Each employee has a name, CPF, base salary, and hire date, while specific roles (`Manager`, `Secretary`, `Salesperson`) have their own particular characteristics and salary rules.

The project was refactored from a single-package prototype into a layered architecture to improve separation of concerns, testability, and code quality, while keeping all data in memory (no database or external persistence).

## 🛠️ Technologies

- Java 17
- Maven (build and dependency management)
- JUnit 5 (automated testing)
- `BigDecimal` for monetary values (salaries, bonuses, payroll)
- `LocalDate` for hire dates and period filtering
- `ArrayList` for in-memory storage (no database)

## 🏗️ Layered Architecture

The project is organized into four layers:

- **`application`** — application entry point. `Main` only wires the dependencies together and starts the console UI.
- **`ui`** — console input/output. `ConsoleMenu` handles reading user input and printing output; it contains no business logic.
- **`domain`** — the core of the system:
  - `domain.model` — entities (`Employee`, `Manager`, `Secretary`, `Salesperson`) with only their own attributes and behavior.
  - `domain.service` — `EmployeeService` concentrates business rules: registration, search, removal, CPF validation, manager authentication, and payroll calculation.
  - `domain.repository` — `EmployeeRepository` stores employees in memory using an `ArrayList`.
  - `domain.authentication` — the `Authenticatable` contract implemented by roles that require a password (e.g. `Manager`).
  - `domain.exception` — custom exceptions (`InvalidCpfException`, `EmployeeNotFoundException`, `InvalidDateException`, `NotAManagerException`).
- **`infrastructure`** — reserved for future external integrations (currently empty, since the system has no external dependencies).

This separation means the console (`ui`) never touches business rules directly, and the business rules (`domain`) never depend on how they are displayed.

## 📁 Directory Structure

```text
src/
├── main/
│   └── java/
│       ├── application/
│       │   └── Main.java
│       ├── ui/
│       │   └── ConsoleMenu.java
│       ├── domain/
│       │   ├── model/
│       │   │   ├── Employee.java
│       │   │   ├── Manager.java
│       │   │   ├── Secretary.java
│       │   │   └── Salesperson.java
│       │   ├── service/
│       │   │   └── EmployeeService.java
│       │   ├── repository/
│       │   │   └── EmployeeRepository.java
│       │   ├── authentication/
│       │   │   └── Authenticatable.java
│       │   └── exception/
│       │       ├── InvalidCpfException.java
│       │       ├── EmployeeNotFoundException.java
│       │       ├── InvalidDateException.java
│       │       └── NotAManagerException.java
│       └── infrastructure/
└── test/
    └── java/
        ├── domain/
        │   ├── ManagerTest.java
        │   ├── SecretaryTest.java
        │   └── SalespersonTest.java
        └── service/
            └── EmployeeServiceTest.java
```

## ✅ Requirements

- JDK 17 or higher
- Maven 3.8+

## ▶️ Running the Project

Compile and run the automated test suite:

```bash
mvn clean test
```

Package the application into an executable jar:

```bash
mvn clean package
```

Run the console application:

```bash
java -cp target/classes application.Main
```

or, after packaging:

```bash
java -jar target/employee-management-system.jar
```

## ✨ Features

- Employee registration
- Employee removal
- Employee search by CPF
- Listing of registered employees
- Display of the company's monthly payroll
- Manager authentication
- Employee search by hiring period
- Error handling through custom exceptions

## 👥 Employee Model

### Manager

Receives a fixed management bonus on top of the base salary and authenticates with a password (`Authenticatable`).

### Secretary

Receives an optional language bonus on top of the base salary.

### Salesperson

Receives a fixed sales bonus on top of the base salary.

## ✅ Implemented Validations

- Duplicate CPF check
- CPF format validation (11 digits, not all repeated, valid check digits)
- Hire-date period validation (start date cannot be after end date)
- Check for non-existent employees on search and removal
- Manager-only authentication (rejects authentication attempts for non-manager CPFs)

## 🧪 Automated Tests

The test suite (JUnit 5) covers:

- Rejection of duplicate CPF registration
- Rejection of invalid CPF format
- Exception when searching for a non-existent employee
- Exception when removing a non-existent employee
- Monthly payroll calculation
- Manager authentication (correct and incorrect password, and non-manager CPFs)
- Salary calculation for each employee type (`Manager`, `Secretary`, `Salesperson`)
- Employee listing filtered by hire-date period

Tests are independent of each other and only use in-memory storage (a fresh `EmployeeRepository`/`EmployeeService` per test).

## 🧠 Applied Programming Concepts

- Object-Oriented Programming (OOP)
- Encapsulation
- Inheritance
- Polymorphism
- Interfaces
- Method overriding
- Exception handling
- Collections (`ArrayList`)
- Layered architecture (application / ui / domain / infrastructure)

## 💻 Example Usage

```
--- MENU ---
1 - Register Employee (Secretary)
2 - Remove Employee
3 - List Employees
4 - Search Employee by CPF
5 - Authenticate Manager
6 - Show Monthly Payroll
7 - List Employees by Period
0 - Exit
Choose: 1
Name: Alice
CPF: 52998234100
Salary: 5000.00
Hire date (YYYY-MM-DD): 2024-05-05
Has language bonus (true/false): true
Employee registered!
```
