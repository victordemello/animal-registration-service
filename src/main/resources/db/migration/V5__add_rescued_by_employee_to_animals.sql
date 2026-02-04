-- V5__add_rescued_by_employee_to_animals.sql
-- Adds foreign key relationship between animals and employees for tracking who rescued each animal

-- Add the new column for employee reference
ALTER TABLE animals ADD COLUMN rescued_by_employee_id UUID;

-- Add foreign key constraint
ALTER TABLE animals ADD CONSTRAINT fk_animals_rescued_by_employee
    FOREIGN KEY (rescued_by_employee_id) REFERENCES employees(id) ON DELETE SET NULL;

-- Add index for better query performance
CREATE INDEX idx_animals_rescued_by_employee_id ON animals(rescued_by_employee_id);

-- Update comment
COMMENT ON COLUMN animals.rescued_by_employee_id IS 'Reference to the employee who rescued/received the animal';

-- Note: The old received_by column is kept for backward compatibility and historical data
-- It can be dropped in a future migration after data migration is complete
