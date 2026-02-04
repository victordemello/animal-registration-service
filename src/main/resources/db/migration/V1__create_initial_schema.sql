-- V1__create_initial_schema.sql
-- Creates the initial database schema for Animal Registration Service

-- Enable UUID extension
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create animal_types table
CREATE TABLE animal_types (
    uuid UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    label VARCHAR(50) NOT NULL,
    CONSTRAINT uk_animal_types_label UNIQUE (label)
);

COMMENT ON TABLE animal_types IS 'Lookup table for animal types (e.g., cat, dog)';
COMMENT ON COLUMN animal_types.uuid IS 'Unique identifier for the animal type';
COMMENT ON COLUMN animal_types.label IS 'Display label for the animal type';

-- Create sizes table
CREATE TABLE sizes (
    uuid UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    label VARCHAR(50) NOT NULL,
    CONSTRAINT uk_sizes_label UNIQUE (label)
);

COMMENT ON TABLE sizes IS 'Lookup table for animal sizes';
COMMENT ON COLUMN sizes.uuid IS 'Unique identifier for the size';
COMMENT ON COLUMN sizes.label IS 'Display label for the size';

-- Create animals table
CREATE TABLE animals (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    estimated_age_months INTEGER NOT NULL,
    temporary_name VARCHAR(100) NOT NULL,
    arrival_condition VARCHAR(500),
    received_by VARCHAR(100),
    arrival_date DATE NOT NULL DEFAULT CURRENT_DATE,
    adoption_date DATE,
    date_of_death DATE,
    animal_type_id UUID NOT NULL,
    size_id UUID,
    CONSTRAINT fk_animals_animal_type FOREIGN KEY (animal_type_id)
        REFERENCES animal_types(uuid) ON DELETE RESTRICT,
    CONSTRAINT fk_animals_size FOREIGN KEY (size_id)
        REFERENCES sizes(uuid) ON DELETE SET NULL,
    CONSTRAINT chk_animals_age_positive CHECK (estimated_age_months >= 0),
    CONSTRAINT chk_animals_adoption_after_arrival CHECK (adoption_date IS NULL OR adoption_date >= arrival_date),
    CONSTRAINT chk_animals_death_after_arrival CHECK (date_of_death IS NULL OR date_of_death >= arrival_date)
);

COMMENT ON TABLE animals IS 'Main table storing registered animals';
COMMENT ON COLUMN animals.id IS 'Unique identifier for the animal';
COMMENT ON COLUMN animals.estimated_age_months IS 'Estimated age of the animal in months';
COMMENT ON COLUMN animals.temporary_name IS 'Temporary name given to the animal upon arrival';
COMMENT ON COLUMN animals.arrival_condition IS 'Description of the animal condition upon arrival';
COMMENT ON COLUMN animals.received_by IS 'Name of the person who received the animal';
COMMENT ON COLUMN animals.arrival_date IS 'Date when the animal arrived at the shelter';
COMMENT ON COLUMN animals.adoption_date IS 'Date when the animal was adopted (null if not adopted)';
COMMENT ON COLUMN animals.date_of_death IS 'Date of death (null if alive)';
COMMENT ON COLUMN animals.animal_type_id IS 'Reference to the type of animal';
COMMENT ON COLUMN animals.size_id IS 'Reference to the size classification';

-- Create indexes for better query performance
CREATE INDEX idx_animals_animal_type_id ON animals(animal_type_id);
CREATE INDEX idx_animals_size_id ON animals(size_id);
CREATE INDEX idx_animals_arrival_date ON animals(arrival_date);
CREATE INDEX idx_animals_adoption_date ON animals(adoption_date) WHERE adoption_date IS NOT NULL;
