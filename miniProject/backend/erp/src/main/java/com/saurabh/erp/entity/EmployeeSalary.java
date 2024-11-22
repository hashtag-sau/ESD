package com.saurabh.erp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name="Employee_Salary")
public class EmployeeSalary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // Foreign Key relationship with the Employee entity
    @ManyToOne(fetch = FetchType.LAZY)  // Many salaries to one employee
    @JoinColumn(name = "employee_id", referencedColumnName = "employeeId")  // Create foreign key with employeeId
    private Employee employee;  // Linking to the Employee entity

    @Column(name = "payment_date", nullable = false)
    private Date paymentDate;

    @Column(name="amount", nullable = false)
    private double amount;

    @Column(name = "description", nullable = false)
    private String description;


}
