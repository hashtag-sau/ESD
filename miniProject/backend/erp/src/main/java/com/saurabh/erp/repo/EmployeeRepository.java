package com.saurabh.erp.repo;

import com.saurabh.erp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // by extending JpaRepository<Employee, Long>, the employee repo inherits methods to manage Employee entities
    // with the Long type as their identifier.
    Optional<Employee> findByEmployeeID(long id);
    Optional<Employee> findByEmail(String email);
}