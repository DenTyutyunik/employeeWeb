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
    public Employee addEmployee(String firstName, String lastName) throws Exception {
        if (employees.size() >= EMPLOYEES_MAX) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employee = new Employee(firstName, lastName);
        if (employeeExists(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        System.out.println("Employee added\n");
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) throws Exception {
        // v2
        /*Employee employee = new Employee(firstName, lastName);
        if (employeeExists(employee)) {
            employees.remove(employee);
            return employees;
        } else {
            throw new EmployeeNotFoundException();
        }*/
        // v1
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i < employees.size(); i++) {
            if (firstName.equals(employees.get(i).getFirstName()) && lastName.equals(employees.get(i).getLastName())) {
                System.out.println("Employee removed\n");
                employees.remove(i);
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) throws Exception {
        // v2
        /*Employee employee = new Employee(firstName, lastName);
        if (employeeExists(employee)) {
            System.out.println("Employee found\n");
            return employee;
        } else {
            throw new EmployeeNotFoundException();
        }*/
        // v1
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i < employees.size(); i++) {
            if (firstName.equals(employees.get(i).getFirstName()) && lastName.equals(employees.get(i).getLastName())) {
                System.out.println("Employee found\n");
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }

    private boolean employeeExists(Employee employee) throws Exception {
        // v3
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).isEquals(employee)) {
                System.out.println("Employee exists");
                return true;
            }
        }
        System.out.println("Employee doesn't exist");
        return false;
        // v2
        /*if (employees.contains(employee)) {
            System.out.println("Employee exists");
            return true;
        } else {
            System.out.println("Employee doesn't exist");
            return false;
        }*/
        // v1
        /*int index = employees.indexOf(employee);
        if (index != -1) {
            System.out.println("Employee exists");
            return true;
        } else {
            System.out.println("Employee doesn't exist");
            return false;
        }*/
    }
}
