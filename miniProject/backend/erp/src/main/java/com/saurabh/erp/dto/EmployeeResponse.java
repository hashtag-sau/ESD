package com.saurabh.erp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EmployeeResponse(

        @JsonProperty("first_name")
        String firstName,

        @JsonProperty("last_name")
        String lastName,

        @JsonProperty("email")
        String email,

        @JsonProperty("title")
        String title,

        @JsonProperty("department")
        long department,

        @JsonProperty("photograph_path")
        String photographPath

) {
}
