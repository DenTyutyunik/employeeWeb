package org.tyutyunik.employeeWeb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Employee Storage Is Full Exception")
public class EmployeeStorageIsFullException extends RuntimeException {
    public EmployeeStorageIsFullException() {
        super("Employee Storage Is Full Exception");
        System.out.println("[EXCEPTION] Employee storage is full");
    }
}
