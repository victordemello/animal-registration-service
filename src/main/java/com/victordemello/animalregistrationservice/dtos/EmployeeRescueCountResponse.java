package com.victordemello.animalregistrationservice.dtos;

import java.util.UUID;

public record EmployeeRescueCountResponse(
        UUID employeeId,
        String employeeName,
        String employeeRole,
        Long rescueCount
) {
}
