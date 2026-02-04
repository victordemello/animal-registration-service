package com.victordemello.animalregistrationservice.dtos;

import com.victordemello.animalregistrationservice.entities.Employee;

import java.time.LocalDate;
import java.util.UUID;

public record EmployeeResponse(
        UUID id,
        String name,
        String email,
        String phone,
        String role,
        LocalDate hireDate,
        Boolean active
) {
    public static EmployeeResponse fromEntity(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getRole(),
                employee.getHireDate(),
                employee.getActive()
        );
    }
}
