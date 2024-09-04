package org.tyutyunik.employeeWeb.service;

public interface EmployeeService {
    String standardAnswer();

    String addEmployee(String firstName, String lastName) throws Exception;

    String removeEmployee(String firstName, String lastName) throws Exception;

    String findEmployee(String firstName, String lastName) throws Exception;
}
