package com.victordemello.animalregistrationservice.repositories;

import com.victordemello.animalregistrationservice.entities.AnimalType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnimalTypeRepository extends JpaRepository<AnimalType, UUID> {
}
