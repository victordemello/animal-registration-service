package com.victordemello.animalregistrationservice.dtos;

import java.time.LocalDate;
import java.util.List;

public record RescueStatisticsResponse(
        LocalDate startDate,
        LocalDate endDate,
        Long totalRescues,
        List<EmployeeRescueCountResponse> rescuesByEmployee
) {
}
