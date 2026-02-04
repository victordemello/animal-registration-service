-- V4__create_employees_table.sql
-- Creates the employees table for shelter staff

CREATE TABLE employees (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(150) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    role VARCHAR(50) NOT NULL,
    hire_date DATE NOT NULL DEFAULT CURRENT_DATE,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT uk_employees_email UNIQUE (email)
);

COMMENT ON TABLE employees IS 'Stores shelter employees information';
COMMENT ON COLUMN employees.id IS 'Unique identifier for the employee';
COMMENT ON COLUMN employees.name IS 'Full name of the employee';
COMMENT ON COLUMN employees.email IS 'Email address (unique)';
COMMENT ON COLUMN employees.phone IS 'Contact phone number';
COMMENT ON COLUMN employees.role IS 'Job role/position at the shelter';
COMMENT ON COLUMN employees.hire_date IS 'Date when the employee was hired';
COMMENT ON COLUMN employees.active IS 'Whether the employee is currently active';

CREATE INDEX idx_employees_active ON employees(active) WHERE active = TRUE;
CREATE INDEX idx_employees_hire_date ON employees(hire_date);
