package org.tyutyunik.employeeWeb.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    private static final int EMPLOYEES_MAX = 10;

    private List<Employee> employees = new ArrayList<>();

    @Override
    public String standardAnswer() {
        return "Добро пожаловать в менеджер сотрудников";
    }

    @Override
    public ResponseEntity<List<Employee>> getData() {
        return ResponseEntity.ok(employees);
    }

    @Override
    public String addEmployee(String firstName, String lastName) throws Exception {
        if (employees.size() < EMPLOYEES_MAX) {
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
    public String removeEmployee(String firstName, String lastName) throws Exception {
        for (int i = 0; i < employees.size(); i++) {
            if (firstName.equals(employees.get(i).getFirstName()) && lastName.equals(employees.get(i).getLastName())) {
                employees.remove(i);
                return "Сотрудник удалён";
            }
        }
        throw new EmployeeNotFoundException("Сотрудник не удалён, так как не найден");
    }

    @Override
    public String findEmployee(String firstName, String lastName) throws Exception {
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
