package com.victordemello.animalregistrationservice.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record AnimalRequest(
        @NotNull(message = "Estimated age in months is required")
        @Min(value = 0, message = "Estimated age must be zero or positive")
        Integer estimatedAgeInMonths,

        @NotBlank(message = "Temporary name is required")
        @Size(max = 100, message = "Temporary name must not exceed 100 characters")
        String temporaryName,

        @Size(max = 500, message = "Arrival condition description must not exceed 500 characters")
        String arrivalConditionDescription,

        @NotNull(message = "Animal type ID is required")
        UUID animalTypeId,

        UUID sizeId,

        UUID rescuedByEmployeeId
) {
}
