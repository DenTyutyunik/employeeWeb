package org.tyutyunik.employeeWeb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Some parameters are invalid")
public class BadRequestException extends Exception {
    public BadRequestException() {
        super("Some parameters are invalid");
        System.out.println("[EXCEPTION] Bad request: some parameters are invalid");
    }
}
