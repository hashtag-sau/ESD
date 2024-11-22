package com.saurabh.erp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record EmployeeSalaryResponse(

        @JsonProperty("employee_id")
        long employeeId,

        @JsonProperty("payment_date")
        Date paymentDate,

        @JsonProperty("amount")
        double amount,

        @JsonProperty("description")
        String description
) {}