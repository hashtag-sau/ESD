package com.saurabh.myrestaurant.dto;

import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductRequest(

        @NotBlank(message = "Product name should be present")
        @JsonProperty("name")
        String name,

        @NotNull(message = "Product price is required")
        @Digits(integer = 10, fraction = 2, message = "Price must be a valid number with up to 2 decimal places") //10digits max
        @JsonProperty("price")
        Double price
) {
}
