package com.victordemello.animalregistrationservice.repositories;

import com.victordemello.animalregistrationservice.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnimalRespository extends JpaRepository<Animal, UUID> {
}
