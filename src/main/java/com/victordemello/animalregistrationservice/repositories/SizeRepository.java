package com.victordemello.animalregistrationservice.repositories;

import com.victordemello.animalregistrationservice.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SizeRepository extends JpaRepository<Size, UUID> {
}
