package com.saurabh.myrestaurant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomerDetailsResponse(
        @JsonProperty("first_name")
        String firstName,
        @JsonProperty("last_name")
        String lastName,
        @JsonProperty("email")
        String email
){

}