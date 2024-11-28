package com.saurabh.erp.mapper;
import com.saurabh.erp.dto.EmployeeRegisterRequest;
import com.saurabh.erp.entity.Employee;
import com.saurabh.erp.dto.EmployeeResponse;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {

    // Employee entity to EmployeeResponse DTO
    public EmployeeResponse toEmployeeResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getTitle(),
                employee.getDepartment(),
                employee.getPhotographPath()
        );
    }

    //From DTO entity
    public Employee toEmployee(EmployeeRegisterRequest request) {
        return Employee.builder()
                .firstName(request.firstName())
                .lastName((request.lastName()))
                .email(request.email())
                .password(request.password())
                .title(request.title())
                .department(request.department())
                .build();
    }



}
