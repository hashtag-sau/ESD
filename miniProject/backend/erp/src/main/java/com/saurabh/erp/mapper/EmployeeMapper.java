package com.saurabh.erp.mapper;
import com.saurabh.erp.entity.Employee;
import com.saurabh.erp.dto.EmployeeResponse;

public class EmployeeMapper {

    // Employee entity to EmployeeResponse DTO
    public EmployeeResponse toEmployeeResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getTitle(),
                employee.getDepartment()
        );
    }

}
