package com.victordemello.animalregistrationservice.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record AdoptionRequest(
        @NotNull(message = "Adoption date is required")
        @PastOrPresent(message = "Adoption date cannot be in the future")
        LocalDate adoptionDate
) {
}
