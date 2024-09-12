package org.tyutyunik.employeeWeb.model;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Employee other = (Employee) obj;
        return firstName.equals(other.firstName) && lastName.equals(other.lastName);
    }

    @Override
    public String toString() {
        return "Employee name = [" + this.getFullName() + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
