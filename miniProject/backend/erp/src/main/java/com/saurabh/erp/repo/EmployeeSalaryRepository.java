package com.saurabh.erp.repo;

import com.saurabh.erp.entity.EmployeeSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EmployeeSalaryRepository extends JpaRepository<EmployeeSalary, Long> {
    Optional<List<EmployeeSalary>> findByEmployeeID(long employeeId);


    // Method to find salaries within a date range for a specific employee ID
    @Query("SELECT e FROM EmployeeSalary e WHERE e.paymentDate BETWEEN :startDate AND :endDate AND e.employeeID = :employeeId")
    Optional<List<EmployeeSalary>> findSalariesByDateRange(Date startDate, Date endDate, long employeeId);

    // Method to find salaries by specific month, year, and employee ID
    @Query("SELECT e FROM EmployeeSalary e WHERE FUNCTION('MONTH', e.paymentDate) = :month AND FUNCTION('YEAR', e.paymentDate) = :year AND e.employeeID = :employeeId")
    Optional<List<EmployeeSalary>> findSalariesByMonthAndYear(int month, int year, long employeeId);

}
