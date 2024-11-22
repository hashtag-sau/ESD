package com.saurabh.erp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record EmployeeRegisterRequest(
        @NotNull(message = "Employee ID cannot be null")
        long employeeID,

        @NotBlank(message = "First name is required")
        @Size(min = 2, max = 30, message = "First name must be between 2 and 50 characters")
        @JsonProperty("first_name")
        String firstName,

        @Size(max = 50, message = "Last name cannot be longer than 50 characters")
        @JsonProperty("last_name")
        String lastName,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @JsonProperty("email")
        String email,

        @NotBlank(message = "Title is required")
        @JsonProperty("title")
        String title,

//        @NotBlank(message = "Photograph path is required")
//        @JsonProperty("")
//        String photographPath,

        @NotNull(message = "Department cannot be null")
        @JsonProperty()
        long department
) {
}
