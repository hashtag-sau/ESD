ALTER TABLE employee_salary
ADD CONSTRAINT fk_employee_salary FOREIGN KEY (employee_id)
REFERENCES employee (employeeid);
