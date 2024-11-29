CREATE TABLE Employee (
    employeeID BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    photograph_path VARCHAR(255) UNIQUE,
    department BIGINT NOT NULL,
    password VARCHAR(255) NOT NULL,
    refresh_token VARCHAR(255)
);

CREATE TABLE Employee_Salary (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    payment_date DATE NOT NULL,
    amount DOUBLE NOT NULL,
    salary_details JSON,
    deduction_details JSON,
    CONSTRAINT fk_employee FOREIGN KEY (employee_id) REFERENCES Employee(employeeID)
);



