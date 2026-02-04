package com.victordemello.animalregistrationservice.dtos;

import com.victordemello.animalregistrationservice.entities.Animal;

import java.time.LocalDate;
import java.util.UUID;

public record AnimalResponse(
        UUID id,
        Integer estimatedAgeInMonths,
        String temporaryName,
        String arrivalConditionDescription,
        LocalDate arrivalDate,
        LocalDate adoptionDate,
        LocalDate dateOfDeath,
        AnimalTypeResponse animalType,
        SizeResponse size,
        EmployeeResponse rescuedByEmployee
) {
    public static AnimalResponse fromEntity(Animal animal) {
        return new AnimalResponse(
                animal.getId(),
                animal.getEstimatedAgeInMonths(),
                animal.getTemporaryName(),
                animal.getArrivalConditionDescription(),
                animal.getArrivalDate(),
                animal.getAdoptionDate(),
                animal.getDateOfDeath(),
                AnimalTypeResponse.fromEntity(animal.getAnimalType()),
                animal.getSize() != null ? SizeResponse.fromEntity(animal.getSize()) : null,
                animal.getRescuedByEmployee() != null ? EmployeeResponse.fromEntity(animal.getRescuedByEmployee()) : null
        );
    }
}
