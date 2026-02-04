package com.victordemello.animalregistrationservice.dtos;

import com.victordemello.animalregistrationservice.entities.AnimalType;

import java.util.UUID;

public record AnimalTypeResponse(
        UUID id,
        String label
) {
    public static AnimalTypeResponse fromEntity(AnimalType animalType) {
        return new AnimalTypeResponse(
                animalType.getUuid(),
                animalType.getLabel()
        );
    }
}
