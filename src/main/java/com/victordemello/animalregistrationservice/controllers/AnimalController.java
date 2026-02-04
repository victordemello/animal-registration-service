package com.victordemello.animalregistrationservice.controllers;

import com.victordemello.animalregistrationservice.dtos.AdoptionRequest;
import com.victordemello.animalregistrationservice.dtos.AnimalRequest;
import com.victordemello.animalregistrationservice.dtos.AnimalResponse;
import com.victordemello.animalregistrationservice.services.AnimalService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping("/create")
    public ResponseEntity<AnimalResponse> create(@Valid @RequestBody AnimalRequest request) {
        AnimalResponse response = animalService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponse> findById(@PathVariable UUID id) {
        AnimalResponse response = animalService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<AnimalResponse>> findAll(
            @PageableDefault(size = 20, sort = "arrivalDate", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<AnimalResponse> response = animalService.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/available")
    public ResponseEntity<Page<AnimalResponse>> findAvailableForAdoption(
            @PageableDefault(size = 20) Pageable pageable
    ) {
        Page<AnimalResponse> response = animalService.findAvailableForAdoption(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/adopted")
    public ResponseEntity<Page<AnimalResponse>> findAdopted(
            @PageableDefault(size = 20) Pageable pageable
    ) {
        Page<AnimalResponse> response = animalService.findAdopted(pageable);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody AnimalRequest request
    ) {
        AnimalResponse response = animalService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        animalService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/adoption")
    public ResponseEntity<AnimalResponse> registerAdoption(
            @PathVariable UUID id,
            @Valid @RequestBody AdoptionRequest request
    ) {
        AnimalResponse response = animalService.registerAdoption(id, request);
        return ResponseEntity.ok(response);
    }
}
