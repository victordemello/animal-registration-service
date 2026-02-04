package com.victordemello.animalregistrationservice.dtos;

import com.victordemello.animalregistrationservice.entities.Size;

import java.util.UUID;

public record SizeResponse(
        UUID id,
        String label
) {
    public static SizeResponse fromEntity(Size size) {
        return new SizeResponse(
                size.getUuid(),
                size.getLabel()
        );
    }
}
