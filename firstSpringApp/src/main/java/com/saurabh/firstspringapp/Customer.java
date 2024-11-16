package com.saurabh.firstspringapp;

import jakarta.persistence.*;

@Entity
public class Customer {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
}
