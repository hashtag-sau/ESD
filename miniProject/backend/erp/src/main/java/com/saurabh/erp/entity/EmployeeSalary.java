package com.saurabh.erp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Employee_Salary")
public class EmployeeSalary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // Foreign Key relationship with the Employee entity
    @Column(name = "employee_id")
    private long employeeID;  // Linking to the Employee entity

    @Column(name = "payment_date", nullable = false)
    private Date paymentDate;

    @Column(name="amount", nullable = false)
    private double amount;

    @Column(name = "description", nullable = false)
    private String description;


}
