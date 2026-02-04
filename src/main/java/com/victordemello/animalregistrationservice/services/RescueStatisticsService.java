package com.victordemello.animalregistrationservice.services;

import com.victordemello.animalregistrationservice.dtos.EmployeeRescueCountResponse;
import com.victordemello.animalregistrationservice.dtos.RescueStatisticsResponse;
import com.victordemello.animalregistrationservice.exceptions.BusinessException;
import com.victordemello.animalregistrationservice.repositories.AnimalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RescueStatisticsService {

    private static final long MAX_DATE_RANGE_DAYS = 365;

    private final AnimalRepository animalRepository;

    public RescueStatisticsService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Transactional(readOnly = true)
    public RescueStatisticsResponse getRescueStatistics(LocalDate startDate, LocalDate endDate) {
        validateDateRange(startDate, endDate);

        List<EmployeeRescueCountResponse> rescuesByEmployee =
                animalRepository.countRescuesByEmployeeBetweenDates(startDate, endDate);

        Long totalRescues = animalRepository.countTotalRescuesBetweenDates(startDate, endDate);

        return new RescueStatisticsResponse(
                startDate,
                endDate,
                totalRescues,
                rescuesByEmployee
        );
    }

    private void validateDateRange(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new BusinessException("Start date must be before or equal to end date");
        }

        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        if (daysBetween > MAX_DATE_RANGE_DAYS) {
            throw new BusinessException(
                    String.format("Date range cannot exceed %d days (1 year). Current range: %d days",
                            MAX_DATE_RANGE_DAYS, daysBetween)
            );
        }
    }
}
