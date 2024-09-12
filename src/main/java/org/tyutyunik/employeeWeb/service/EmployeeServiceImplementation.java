package org.tyutyunik.employeeWeb.service;

import org.springframework.stereotype.Service;
import org.tyutyunik.employeeWeb.controller.EmployeeController;
import org.tyutyunik.employeeWeb.model.Employee;
import org.tyutyunik.employeeWeb.exceptions.*;
import org.tyutyunik.employeeWeb.controller.EmployeeController.*;

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
    public List<Employee> getData() {
        System.out.println("getData");
        return employees;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() >= EMPLOYEES_MAX) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        boolean removed = employees.remove(employee);
        if (removed) {
            return employee;
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            return employee;
        } else {
            throw new EmployeeNotFoundException();
        }
    }
}
