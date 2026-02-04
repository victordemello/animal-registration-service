package com.victordemello.animalregistrationservice.repositories;

import com.victordemello.animalregistrationservice.entities.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface AnimalRepository extends JpaRepository<Animal, UUID> {

    @Query("SELECT a FROM Animal a WHERE a.adoptionDate IS NULL AND a.dateOfDeath IS NULL ORDER BY a.arrivalDate ASC")
    Page<Animal> findAvailableForAdoption(Pageable pageable);

    @Query("SELECT a FROM Animal a WHERE a.adoptionDate IS NOT NULL ORDER BY a.adoptionDate DESC")
    Page<Animal> findAdopted(Pageable pageable);
}
