-- V2__insert_animal_types.sql
-- Inserts initial animal types seed data

INSERT INTO animal_types (uuid, label) VALUES
    (uuid_generate_v4(), 'Cat'),
    (uuid_generate_v4(), 'Dog');
