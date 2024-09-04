package org.tyutyunik.employeeWeb.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeServiceImplementation implements EmployeeService {

    final int employeeCountMax = 10;

    private List<Employee> employees = new ArrayList<>();
    private int employeesCounter;

    @Override
    public String standardAnswer() {
        return "Добро пожаловать в менеджер сотрудников";
    }

    @Override
    public String addEmployee(String firstName, String lastName) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        if (employees.size() < employeeCountMax) {
            for (int i = 0; i < employees.size(); i++) {
                if (firstName.equals(employees.get(i).getFirstName()) && lastName.equals(employees.get(i).getLastName())) {
                    throw new EmployeeAlreadyAddedException("Сотрудник не может быть добавлен, потому что уже существует");
                }
            }
            employees.addLast(new Employee(firstName, lastName));
            return "Сотрудник добавлен";
        } else {
            throw new EmployeeStorageIsFullException("Сотрудник не может быть добавлен, потому что достигнуто максимальное количество сотрудников");
        }
    }

    @Override
    public String removeEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        for (int i = 0; i < employees.size(); i++) {
            if (firstName.equals(employees.get(i).getFirstName()) && lastName.equals(employees.get(i).getLastName())) {
                employees.remove(i);
                return "Сотрудник удалён";
            }
        }
        throw new EmployeeNotFoundException("Сотрудник не удалён, так как не найден");
    }

    @Override
    public String findEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        for (int i = 0; i < employees.size(); i++) {
            if (firstName.equals(employees.get(i).getFirstName()) && lastName.equals(employees.get(i).getLastName())) {
                return "Сотрудник найден";
            }
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }

    public static class EmployeeNotFoundException extends Exception {
        public EmployeeNotFoundException(String message) {
            super(message);
        }
    }

    public static class EmployeeStorageIsFullException extends Exception {
        public EmployeeStorageIsFullException(String message) {
            super(message);
        }
    }

    public static class EmployeeAlreadyAddedException extends Exception {
        public EmployeeAlreadyAddedException(String message) {
            super(message);
        }
    }
}
