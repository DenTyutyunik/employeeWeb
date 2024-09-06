package org.tyutyunik.employeeWeb.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {
    String standardAnswer();

    ResponseEntity<List<Employee>> getData();

    String addEmployee(String firstName, String lastName) throws Exception;

    String removeEmployee(String firstName, String lastName) throws Exception;

    String findEmployee(String firstName, String lastName) throws Exception;
}
