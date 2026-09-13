# 👔 Employee Management System

![Java](https://img.shields.io/badge/Java-OOP-orange?logo=openjdk)

A Java-based system designed to simulate employee management within a company. The project allows registering, querying, authenticating, and administering different types of employees, applying fundamental Object-Oriented Programming concepts.

## 📌 About the Project

The system was created to represent a real-world scenario of employee administration within a company. Each employee has information such as CPF, salary, and hire date, while specific roles have their own particular characteristics.

During development, business rules were implemented for data validation, user authentication, and control of registered employees.

## ✨ Features

- Employee registration
- Employee removal
- Employee search by CPF
- Listing of registered employees
- Display of the company's payroll
- Manager authentication
- Employee search by hiring period
- Error handling through custom exceptions

## 👥 Employee Model

The system works with different types of employees:

### Manager

Has access to the system through password-based authentication.

### Secretary

Employee who receives a salary bonus according to the rules defined in the application.

### Salesperson

Employee responsible for sales, who may have specific rules related to their compensation.

## 🏗️ Application Structure

The `Empresa` (Company) class is responsible for managing all registered employees.

The main operations include:

- Adding employees
- Removing employees
- Searching for employees
- Calculating payroll
- Listing registered information

## ✅ Implemented Validations

The system performs validations to ensure data consistency:

- Duplicate CPF check
- Validation of the provided CPF
- Date validation
- Check for non-existent employees

## 🧠 Applied Programming Concepts

- Object-Oriented Programming (OOP)
- Encapsulation
- Inheritance
- Polymorphism
- Interfaces
- Method overriding
- Exception handling
- Collections (ArrayList)
- Layered responsibility organization
