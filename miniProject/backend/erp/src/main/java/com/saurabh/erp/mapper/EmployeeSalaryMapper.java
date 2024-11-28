package com.saurabh.erp.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saurabh.erp.dto.EmployeeSalaryResponse;
import com.saurabh.erp.entity.EmployeeSalary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeSalaryMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public EmployeeSalaryResponse toEmployeeSalaryResponse(EmployeeSalary employeeSalary) throws JsonProcessingException {
        // Parse JSON strings into objects
        List<Map<String, String>> salaryDetails = objectMapper.readValue(employeeSalary.getSalaryDetails(), List.class);
        List<Map<String, String>> deductionDetails = objectMapper.readValue(employeeSalary.getDeductionDetails(), List.class);

        // Calculate gross pay
        double grossPay = salaryDetails.stream()
                .mapToDouble(detail -> Double.parseDouble(detail.get("amount")))
                .sum();

        // Calculate total deductions
        double deductions = deductionDetails.stream()
                .mapToDouble(deduction -> Double.parseDouble(deduction.get("amount")))
                .sum();

        // Calculate take-home pay
        double takeHome = grossPay - deductions;

        // Construct the response
        return new EmployeeSalaryResponse(
                employeeSalary.getEmployeeID(),
                employeeSalary.getPaymentDate(),
                employeeSalary.getAmount(),
                String.format("%.2f", takeHome),
                String.format("%.2f", grossPay),
                String.format("%.2f", deductions),
                salaryDetails,
                deductionDetails
        );
    }
}
