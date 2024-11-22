package com.saurabh.erp.entity;

import jakarta.persistence.*;
import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name="Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long employeeID;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="photograph_path", unique = true)
    private String photographPath;

    @Column(name="department", nullable = false)
    private long department;

    @Column(name="password", nullable = false)
    private String password;
}
