package org.tyutyunik.employeeWeb.controller;

import org.springframework.web.bind.annotation.*;
import org.tyutyunik.employeeWeb.model.Employee;
import org.tyutyunik.employeeWeb.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public String standardAnswer() {
        return employeeService.standardAnswer();
    }

    @GetMapping("/get/all")
    public List<Employee> getData() {
        return employeeService.getData();
    }

    @GetMapping("/add")
    public Employee add(@RequestParam String firstName,
                        @RequestParam String lastName) {
        return employeeService.addEmployee(firstName, lastName);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam String firstName,
                           @RequestParam String lastName) {
        return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam String firstName,
                         @RequestParam String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }
}
