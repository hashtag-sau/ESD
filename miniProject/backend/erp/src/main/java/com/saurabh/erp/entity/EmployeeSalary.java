package com.saurabh.erp.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    // Storing salary details as a JSON column
    @Column(name = "salary_details", columnDefinition = "JSON")
    private String salaryDetails;

    // Storing deduction details as a JSON column
    @Column(name = "deduction_details", columnDefinition = "JSON")
    private String deductionDetails;

    // Utility method for converting JSON to a list of maps
    public List<Map<String, Object>> getSalaryDetailsAsList() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(this.salaryDetails, List.class);
    }

    // Utility method for converting JSON to a list of maps
    public List<Map<String, Object>> getDeductionDetailsAsList() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(this.deductionDetails, List.class);

    }
}
