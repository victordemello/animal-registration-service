-- V3__insert_sizes.sql
-- Inserts initial size classifications seed data

INSERT INTO sizes (uuid, label) VALUES
    (uuid_generate_v4(), 'Small'),
    (uuid_generate_v4(), 'Medium'),
    (uuid_generate_v4(), 'Large'),
    (uuid_generate_v4(), 'Giant');
