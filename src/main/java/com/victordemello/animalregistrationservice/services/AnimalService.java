package com.victordemello.animalregistrationservice.services;

import com.victordemello.animalregistrationservice.dtos.AdoptionRequest;
import com.victordemello.animalregistrationservice.dtos.AnimalRequest;
import com.victordemello.animalregistrationservice.dtos.AnimalResponse;
import com.victordemello.animalregistrationservice.entities.Animal;
import com.victordemello.animalregistrationservice.entities.AnimalType;
import com.victordemello.animalregistrationservice.entities.Employee;
import com.victordemello.animalregistrationservice.entities.Size;
import com.victordemello.animalregistrationservice.exceptions.BusinessException;
import com.victordemello.animalregistrationservice.exceptions.ResourceNotFoundException;
import com.victordemello.animalregistrationservice.repositories.AnimalRepository;
import com.victordemello.animalregistrationservice.repositories.AnimalTypeRepository;
import com.victordemello.animalregistrationservice.repositories.EmployeeRepository;
import com.victordemello.animalregistrationservice.repositories.SizeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalTypeRepository animalTypeRepository;
    private final SizeRepository sizeRepository;
    private final EmployeeRepository employeeRepository;

    public AnimalService(
            AnimalRepository animalRepository,
            AnimalTypeRepository animalTypeRepository,
            SizeRepository sizeRepository,
            EmployeeRepository employeeRepository
    ) {
        this.animalRepository = animalRepository;
        this.animalTypeRepository = animalTypeRepository;
        this.sizeRepository = sizeRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public AnimalResponse create(AnimalRequest request) {
        AnimalType animalType = animalTypeRepository.findById(request.animalTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("AnimalType", "id", request.animalTypeId()));

        Size size = null;
        if (request.sizeId() != null) {
            size = sizeRepository.findById(request.sizeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Size", "id", request.sizeId()));
        }

        Employee employee = null;
        if (request.rescuedByEmployeeId() != null) {
            employee = employeeRepository.findById(request.rescuedByEmployeeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", request.rescuedByEmployeeId()));
        }

        Animal animal = new Animal();
        animal.setEstimatedAgeInMonths(request.estimatedAgeInMonths());
        animal.setTemporaryName(request.temporaryName());
        animal.setArrivalConditionDescription(request.arrivalConditionDescription());
        animal.setArrivalDate(LocalDate.now());
        animal.setAnimalType(animalType);
        animal.setSize(size);
        animal.setRescuedByEmployee(employee);

        Animal saved = animalRepository.save(animal);
        return AnimalResponse.fromEntity(saved);
    }

    @Transactional(readOnly = true)
    public AnimalResponse findById(UUID id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal", "id", id));
        return AnimalResponse.fromEntity(animal);
    }

    @Transactional(readOnly = true)
    public Page<AnimalResponse> findAll(Pageable pageable) {
        return animalRepository.findAll(pageable)
                .map(AnimalResponse::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<AnimalResponse> findAvailableForAdoption(Pageable pageable) {
        return animalRepository.findAvailableForAdoption(pageable)
                .map(AnimalResponse::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<AnimalResponse> findAdopted(Pageable pageable) {
        return animalRepository.findAdopted(pageable)
                .map(AnimalResponse::fromEntity);
    }

    @Transactional
    public AnimalResponse update(UUID id, AnimalRequest request) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal", "id", id));

        AnimalType animalType = animalTypeRepository.findById(request.animalTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("AnimalType", "id", request.animalTypeId()));

        Size size = null;
        if (request.sizeId() != null) {
            size = sizeRepository.findById(request.sizeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Size", "id", request.sizeId()));
        }

        Employee employee = null;
        if (request.rescuedByEmployeeId() != null) {
            employee = employeeRepository.findById(request.rescuedByEmployeeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", request.rescuedByEmployeeId()));
        }

        animal.setEstimatedAgeInMonths(request.estimatedAgeInMonths());
        animal.setTemporaryName(request.temporaryName());
        animal.setArrivalConditionDescription(request.arrivalConditionDescription());
        animal.setAnimalType(animalType);
        animal.setSize(size);
        animal.setRescuedByEmployee(employee);

        Animal saved = animalRepository.save(animal);
        return AnimalResponse.fromEntity(saved);
    }

    @Transactional
    public void delete(UUID id) {
        if (!animalRepository.existsById(id)) {
            throw new ResourceNotFoundException("Animal", "id", id);
        }
        animalRepository.deleteById(id);
    }

    @Transactional
    public AnimalResponse registerAdoption(UUID id, AdoptionRequest request) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal", "id", id));

        if (animal.getAdoptionDate() != null) {
            throw new BusinessException("Animal has already been adopted");
        }

        if (animal.getDateOfDeath() != null) {
            throw new BusinessException("Cannot adopt a deceased animal");
        }

        if (request.adoptionDate().isBefore(animal.getArrivalDate())) {
            throw new BusinessException("Adoption date cannot be before arrival date");
        }

        animal.setAdoptionDate(request.adoptionDate());

        Animal saved = animalRepository.save(animal);
        return AnimalResponse.fromEntity(saved);
    }
}
