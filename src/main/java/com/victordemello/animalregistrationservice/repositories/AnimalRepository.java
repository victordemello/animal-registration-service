package com.victordemello.animalregistrationservice.repositories;

import com.victordemello.animalregistrationservice.dtos.EmployeeRescueCountResponse;
import com.victordemello.animalregistrationservice.entities.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AnimalRepository extends JpaRepository<Animal, UUID> {

    @Query("SELECT a FROM Animal a WHERE a.adoptionDate IS NULL AND a.dateOfDeath IS NULL ORDER BY a.arrivalDate ASC")
    Page<Animal> findAvailableForAdoption(Pageable pageable);

    @Query("SELECT a FROM Animal a WHERE a.adoptionDate IS NOT NULL ORDER BY a.adoptionDate DESC")
    Page<Animal> findAdopted(Pageable pageable);

    @Query("""
            SELECT new com.victordemello.animalregistrationservice.dtos.EmployeeRescueCountResponse(
                e.id, e.name, e.role, COUNT(a.id)
            )
            FROM Animal a
            JOIN a.rescuedByEmployee e
            WHERE a.arrivalDate BETWEEN :startDate AND :endDate
            GROUP BY e.id, e.name, e.role
            ORDER BY COUNT(a.id) DESC
            """)
    List<EmployeeRescueCountResponse> countRescuesByEmployeeBetweenDates(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("SELECT COUNT(a) FROM Animal a WHERE a.arrivalDate BETWEEN :startDate AND :endDate AND a.rescuedByEmployee IS NOT NULL")
    Long countTotalRescuesBetweenDates(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
