package com.saurabh.erp.mapper;

import com.saurabh.erp.dto.EmployeeSalaryResponse;
import com.saurabh.erp.entity.EmployeeSalary;
import org.springframework.stereotype.Service;

@Service
public class EmployeeSalaryMapper {

    // EmployeeSalary entity to EmployeeSalaryResponse DTO
    public EmployeeSalaryResponse toEmployeeSalaryResponse(EmployeeSalary employeeSalary) {
        return new EmployeeSalaryResponse(
                employeeSalary.getEmployeeID(),
                employeeSalary.getPaymentDate(),
                employeeSalary.getAmount(),
                employeeSalary.getDescription()
        );
    }
}
