package com.victordemello.animalregistrationservice.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(name = "estimated_age_months", nullable = false)
    private Integer estimatedAgeInMonths;

    @Column(name = "temporary_name", nullable = false, length = 100)
    private String temporaryName;

    @Column(name = "arrival_condition", length = 500)
    private String arrivalConditionDescription;

    @Column(name = "received_by", length = 100)
    private String receivedBy;

    @ManyToOne
    @JoinColumn(name = "rescued_by_employee_id")
    private Employee rescuedByEmployee;

    @CreatedDate
    @Column(name = "arrival_date", nullable = false, updatable = false)
    private LocalDate arrivalDate;

    @Column(name = "adoption_date")
    private LocalDate adoptionDate;

    @Column(name = "date_of_death")
    private LocalDate dateOfDeath;

    @ManyToOne(optional = false)
    @JoinColumn(name = "animal_type_id")
    private AnimalType animalType;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;

    public UUID getId() {
        return id;
    }

    public Integer getEstimatedAgeInMonths() {
        return estimatedAgeInMonths;
    }

    public void setEstimatedAgeInMonths(Integer estimatedAgeInMonths) {
        this.estimatedAgeInMonths = estimatedAgeInMonths;
    }

    public String getTemporaryName() {
        return temporaryName;
    }

    public void setTemporaryName(String temporaryName) {
        this.temporaryName = temporaryName;
    }

    public String getArrivalConditionDescription() {
        return arrivalConditionDescription;
    }

    public void setArrivalConditionDescription(String arrivalConditionDescription) {
        this.arrivalConditionDescription = arrivalConditionDescription;
    }

    public String getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDate getAdoptionDate() {
        return adoptionDate;
    }

    public void setAdoptionDate(LocalDate adoptionDate) {
        this.adoptionDate = adoptionDate;
    }

    public LocalDate getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(LocalDate dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Employee getRescuedByEmployee() {
        return rescuedByEmployee;
    }

    public void setRescuedByEmployee(Employee rescuedByEmployee) {
        this.rescuedByEmployee = rescuedByEmployee;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(id, animal.id) && Objects.equals(estimatedAgeInMonths, animal.estimatedAgeInMonths) && Objects.equals(temporaryName, animal.temporaryName) && Objects.equals(arrivalConditionDescription, animal.arrivalConditionDescription) && Objects.equals(receivedBy, animal.receivedBy) && Objects.equals(arrivalDate, animal.arrivalDate) && Objects.equals(adoptionDate, animal.adoptionDate) && Objects.equals(dateOfDeath, animal.dateOfDeath) && Objects.equals(animalType, animal.animalType) && Objects.equals(size, animal.size) && Objects.equals(rescuedByEmployee, animal.rescuedByEmployee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, estimatedAgeInMonths, temporaryName, arrivalConditionDescription, receivedBy, arrivalDate, adoptionDate, dateOfDeath, animalType, size, rescuedByEmployee);
    }
}
