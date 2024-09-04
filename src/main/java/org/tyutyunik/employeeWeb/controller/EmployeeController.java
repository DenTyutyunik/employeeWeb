package org.tyutyunik.employeeWeb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tyutyunik.employeeWeb.service.EmployeeService;
import org.tyutyunik.employeeWeb.service.EmployeeServiceImplementation.*;

import java.util.Optional;

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

    @GetMapping("/add")
    public String add(@RequestParam Optional<String> firstName,
                      @RequestParam Optional<String> lastName) throws Exception {
        if (firstName.isPresent() && lastName.isPresent()) {
            try {
                return employeeService.addEmployee(firstName.get(), lastName.get());
            } catch (EmployeeAlreadyAddedException e) {
                System.out.println(e.getMessage()); // message for debug
                return e.getMessage(); // message for user
            } catch (EmployeeStorageIsFullException e) {
                System.out.println(e.getMessage()); // message for debug
                return e.getMessage(); // message for user
            }
        } else {
            System.out.println("Нет одного из параметров");
            return "Нет одного из параметров";
        }
    }

    @GetMapping("/remove")
    public String remove(@RequestParam Optional<String> firstName,
                         @RequestParam Optional<String> lastName) throws Exception {
        if (firstName.isPresent() && lastName.isPresent()) {
            try {
                return employeeService.removeEmployee(firstName.get(), lastName.get());
            } catch (EmployeeNotFoundException e) {
                System.out.println(e.getMessage()); // message for debug
                return e.getMessage(); // message for user
            }
        } else {
            return "Нет одного из параметров";
        }
    }

    @GetMapping("/find")
    public String find(@RequestParam Optional<String> firstName,
                       @RequestParam Optional<String> lastName) throws Exception {
        if (firstName.isPresent() && lastName.isPresent()) {
            try {
                return employeeService.findEmployee(firstName.get(), lastName.get());
            } catch (EmployeeNotFoundException e) {
                System.out.println(e.getMessage()); // message for debug
                return e.getMessage(); // message for user
            }
        } else {
            return "Нет одного из параметров";
        }
    }
}
