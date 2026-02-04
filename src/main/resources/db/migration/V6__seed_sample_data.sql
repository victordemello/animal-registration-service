-- V6__seed_sample_data.sql
-- Populates database with sample data for testing and development

-- Insert sample employees
INSERT INTO employees (id, name, email, phone, role, hire_date, active) VALUES
    ('a1b2c3d4-e5f6-4a5b-8c9d-0e1f2a3b4c5d', 'Maria Silva', 'maria.silva@shelter.com', '11999001122', 'Veterinarian', '2022-01-15', TRUE),
    ('b2c3d4e5-f6a7-5b6c-9d0e-1f2a3b4c5d6e', 'Joao Santos', 'joao.santos@shelter.com', '11999002233', 'Animal Rescuer', '2022-03-20', TRUE),
    ('c3d4e5f6-a7b8-6c7d-0e1f-2a3b4c5d6e7f', 'Ana Oliveira', 'ana.oliveira@shelter.com', '11999003344', 'Animal Rescuer', '2022-06-10', TRUE),
    ('d4e5f6a7-b8c9-7d8e-1f2a-3b4c5d6e7f8a', 'Pedro Costa', 'pedro.costa@shelter.com', '11999004455', 'Shelter Manager', '2021-11-01', TRUE),
    ('e5f6a7b8-c9d0-8e9f-2a3b-4c5d6e7f8a9b', 'Carla Mendes', 'carla.mendes@shelter.com', '11999005566', 'Animal Rescuer', '2023-02-14', TRUE);

-- Get animal type UUIDs (Cat and Dog from V2)
-- We'll use subqueries to get the UUIDs

-- Insert sample animals with varied arrival dates for statistics testing
-- Animals rescued by Maria Silva (Veterinarian)
INSERT INTO animals (id, estimated_age_months, temporary_name, arrival_condition, arrival_date, animal_type_id, size_id, rescued_by_employee_id) VALUES
    ('11111111-1111-1111-1111-111111111111', 24, 'Luna', 'Good health, found wandering', '2024-01-10',
        (SELECT uuid FROM animal_types WHERE label = 'Cat'),
        (SELECT uuid FROM sizes WHERE label = 'Small'),
        'a1b2c3d4-e5f6-4a5b-8c9d-0e1f2a3b4c5d'),
    ('11111111-1111-1111-1111-111111111112', 36, 'Max', 'Minor injuries, recovered well', '2024-02-15',
        (SELECT uuid FROM animal_types WHERE label = 'Dog'),
        (SELECT uuid FROM sizes WHERE label = 'Large'),
        'a1b2c3d4-e5f6-4a5b-8c9d-0e1f2a3b4c5d'),
    ('11111111-1111-1111-1111-111111111113', 12, 'Mimi', 'Malnourished, needs care', '2024-03-20',
        (SELECT uuid FROM animal_types WHERE label = 'Cat'),
        (SELECT uuid FROM sizes WHERE label = 'Small'),
        'a1b2c3d4-e5f6-4a5b-8c9d-0e1f2a3b4c5d');

-- Animals rescued by Joao Santos (Animal Rescuer)
INSERT INTO animals (id, estimated_age_months, temporary_name, arrival_condition, arrival_date, animal_type_id, size_id, rescued_by_employee_id) VALUES
    ('22222222-2222-2222-2222-222222222221', 6, 'Thor', 'Found in construction site', '2024-01-25',
        (SELECT uuid FROM animal_types WHERE label = 'Dog'),
        (SELECT uuid FROM sizes WHERE label = 'Medium'),
        'b2c3d4e5-f6a7-5b6c-9d0e-1f2a3b4c5d6e'),
    ('22222222-2222-2222-2222-222222222222', 18, 'Bella', 'Abandoned in park', '2024-02-28',
        (SELECT uuid FROM animal_types WHERE label = 'Dog'),
        (SELECT uuid FROM sizes WHERE label = 'Medium'),
        'b2c3d4e5-f6a7-5b6c-9d0e-1f2a3b4c5d6e'),
    ('22222222-2222-2222-2222-222222222223', 8, 'Simba', 'Street rescue, healthy', '2024-04-05',
        (SELECT uuid FROM animal_types WHERE label = 'Cat'),
        (SELECT uuid FROM sizes WHERE label = 'Small'),
        'b2c3d4e5-f6a7-5b6c-9d0e-1f2a3b4c5d6e'),
    ('22222222-2222-2222-2222-222222222224', 48, 'Rex', 'Senior dog, good condition', '2024-05-12',
        (SELECT uuid FROM animal_types WHERE label = 'Dog'),
        (SELECT uuid FROM sizes WHERE label = 'Large'),
        'b2c3d4e5-f6a7-5b6c-9d0e-1f2a3b4c5d6e'),
    ('22222222-2222-2222-2222-222222222225', 3, 'Pipoca', 'Kitten found alone', '2024-06-18',
        (SELECT uuid FROM animal_types WHERE label = 'Cat'),
        (SELECT uuid FROM sizes WHERE label = 'Small'),
        'b2c3d4e5-f6a7-5b6c-9d0e-1f2a3b4c5d6e');

-- Animals rescued by Ana Oliveira (Animal Rescuer)
INSERT INTO animals (id, estimated_age_months, temporary_name, arrival_condition, arrival_date, animal_type_id, size_id, rescued_by_employee_id) VALUES
    ('33333333-3333-3333-3333-333333333331', 10, 'Nina', 'Found during storm', '2024-02-10',
        (SELECT uuid FROM animal_types WHERE label = 'Cat'),
        (SELECT uuid FROM sizes WHERE label = 'Small'),
        'c3d4e5f6-a7b8-6c7d-0e1f-2a3b4c5d6e7f'),
    ('33333333-3333-3333-3333-333333333332', 30, 'Bob', 'Surrendered by owner', '2024-03-15',
        (SELECT uuid FROM animal_types WHERE label = 'Dog'),
        (SELECT uuid FROM sizes WHERE label = 'Giant'),
        'c3d4e5f6-a7b8-6c7d-0e1f-2a3b4c5d6e7f'),
    ('33333333-3333-3333-3333-333333333333', 5, 'Mel', 'Highway rescue', '2024-04-22',
        (SELECT uuid FROM animal_types WHERE label = 'Dog'),
        (SELECT uuid FROM sizes WHERE label = 'Small'),
        'c3d4e5f6-a7b8-6c7d-0e1f-2a3b4c5d6e7f'),
    ('33333333-3333-3333-3333-333333333334', 14, 'Frajola', 'Trapped in building', '2024-07-08',
        (SELECT uuid FROM animal_types WHERE label = 'Cat'),
        (SELECT uuid FROM sizes WHERE label = 'Medium'),
        'c3d4e5f6-a7b8-6c7d-0e1f-2a3b4c5d6e7f');

-- Animals rescued by Pedro Costa (Shelter Manager - occasional rescues)
INSERT INTO animals (id, estimated_age_months, temporary_name, arrival_condition, arrival_date, animal_type_id, size_id, rescued_by_employee_id) VALUES
    ('44444444-4444-4444-4444-444444444441', 24, 'Duke', 'Emergency rescue', '2024-05-30',
        (SELECT uuid FROM animal_types WHERE label = 'Dog'),
        (SELECT uuid FROM sizes WHERE label = 'Large'),
        'd4e5f6a7-b8c9-7d8e-1f2a-3b4c5d6e7f8a');

-- Animals rescued by Carla Mendes (Animal Rescuer - newer employee)
INSERT INTO animals (id, estimated_age_months, temporary_name, arrival_condition, arrival_date, animal_type_id, size_id, rescued_by_employee_id) VALUES
    ('55555555-5555-5555-5555-555555555551', 7, 'Bolinha', 'Found in trash area', '2024-06-05',
        (SELECT uuid FROM animal_types WHERE label = 'Cat'),
        (SELECT uuid FROM sizes WHERE label = 'Small'),
        'e5f6a7b8-c9d0-8e9f-2a3b-4c5d6e7f8a9b'),
    ('55555555-5555-5555-5555-555555555552', 20, 'Zeus', 'Rescued from flood', '2024-07-15',
        (SELECT uuid FROM animal_types WHERE label = 'Dog'),
        (SELECT uuid FROM sizes WHERE label = 'Giant'),
        'e5f6a7b8-c9d0-8e9f-2a3b-4c5d6e7f8a9b'),
    ('55555555-5555-5555-5555-555555555553', 4, 'Mingau', 'Orphaned kitten', '2024-08-01',
        (SELECT uuid FROM animal_types WHERE label = 'Cat'),
        (SELECT uuid FROM sizes WHERE label = 'Small'),
        'e5f6a7b8-c9d0-8e9f-2a3b-4c5d6e7f8a9b');

-- Mark some animals as adopted
UPDATE animals SET adoption_date = '2024-03-25' WHERE id = '11111111-1111-1111-1111-111111111111';
UPDATE animals SET adoption_date = '2024-04-10' WHERE id = '22222222-2222-2222-2222-222222222221';
UPDATE animals SET adoption_date = '2024-05-20' WHERE id = '33333333-3333-3333-3333-333333333331';
UPDATE animals SET adoption_date = '2024-06-30' WHERE id = '22222222-2222-2222-2222-222222222222';
UPDATE animals SET adoption_date = '2024-08-15' WHERE id = '33333333-3333-3333-3333-333333333333';

-- Summary of sample data:
-- Maria Silva: 3 rescues (Jan-Mar 2024)
-- Joao Santos: 5 rescues (Jan-Jun 2024)
-- Ana Oliveira: 4 rescues (Feb-Jul 2024)
-- Pedro Costa: 1 rescue (May 2024)
-- Carla Mendes: 3 rescues (Jun-Aug 2024)
-- Total: 16 animals, 5 adopted
