package org.tyutyunik.employeeWeb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tyutyunik.employeeWeb.service.Employee;
import org.tyutyunik.employeeWeb.service.EmployeeService;
import org.tyutyunik.employeeWeb.service.EmployeeServiceImplementation.*;

import java.util.List;
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

    @GetMapping("/data")
    public ResponseEntity<List<Employee>> getData() {
        return employeeService.getData();
    }

    @GetMapping("/add")
    public ResponseEntity<String> add(@RequestParam Optional<String> firstName,
                                      @RequestParam Optional<String> lastName) throws Exception {
        if (firstName.isPresent() && lastName.isPresent()) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(employeeService.addEmployee(firstName.get(), lastName.get()));
            } catch (EmployeeAlreadyAddedException | EmployeeStorageIsFullException e) {
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Нет одного из параметров");
        }
    }

    @GetMapping("/remove")
    public ResponseEntity<String> remove(@RequestParam Optional<String> firstName,
                                         @RequestParam Optional<String> lastName) throws Exception {
        if (firstName.isPresent() && lastName.isPresent()) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(employeeService.removeEmployee(firstName.get(), lastName.get()));
            } catch (EmployeeNotFoundException e) {
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Нет одного из параметров");
        }
    }

    @GetMapping("/find")
    public ResponseEntity<String> find(@RequestParam Optional<String> firstName,
                                       @RequestParam Optional<String> lastName) throws Exception {
        if (firstName.isPresent() && lastName.isPresent()) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(employeeService.findEmployee(firstName.get(), lastName.get()));
            } catch (EmployeeNotFoundException e) {
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Нет одного из параметров");
        }
    }
}
