package com.saurabh.myrestaurant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record CustomerRegisterRequest(
        @NotBlank(message = "First Name is required")
        @Pattern(regexp = "^[A-Za-z]+$", message = "First name must contain only alphabetic characters")
        @JsonProperty("first_name")
        String firstName,

        @Pattern(regexp = "^[A-Za-z]+$", message = "First name must contain only alphabetic characters")
        @JsonProperty("last_name")
        String lastName,


        @NotBlank(message = "Email is required")
        @Email
        @JsonProperty("email")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 4, max = 12, message = "Password must be between 6 and 12 characters")
        @JsonProperty("password")
        String password
){}
