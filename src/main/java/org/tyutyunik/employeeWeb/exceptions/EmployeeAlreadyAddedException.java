package org.tyutyunik.employeeWeb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Employee already added")
public class EmployeeAlreadyAddedException extends Exception {
    public EmployeeAlreadyAddedException() {
        super("Employee already added");
        System.out.println("[EXCEPTION] Employee already added");
    }
}
