package com.saurabh.myrestaurant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.lang.annotation.Native;

public record LoginRequest(
        @NotNull(message = "Email is required")
        @Email(message = "Invalid Email Format")
        @JsonProperty("email") //jackson annotation for json mapping this will tell to map email field in json to here
        String email,

        @NotBlank(message = "Password is required")//checks for null empty and only whitespaces
        @Size(min = 4, max = 12, message = "Password must be between 6 and 12 characters")
        @JsonProperty("password")
        String password
) {
    //No need for an explicit constructor, getters, or setters these are automatically provided
    //since we are using record
    //however we can add custom constructor if we want additional login
}
