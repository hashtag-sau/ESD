package com.saurabh.erp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
import java.util.Map;

public record EmployeeSalaryResponse(

        @JsonProperty("employee_id")
        long employeeId,

        @JsonProperty("payment_date")
        Date paymentDate,

        @JsonProperty("amount")
        double amount,

        @JsonProperty("takeHome")
        String takeHome,

        @JsonProperty("grossPay")
        String grossPay,

        @JsonProperty("deductions")
        String deductions,

        @JsonProperty("details")
        List<Map<String, String>> details,

        @JsonProperty("deductionsDetails")
        List<Map<String, String>> deductionsDetails
) {}