package com.saurabh.myrestaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //lombok data contains getter setter tostring etc
@Builder //can
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="firstName", nullable = false)
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="email",nullable = false,unique = true)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="refreshToken", nullable = false)
    private String refreshToken; //for refresh tokens

}
