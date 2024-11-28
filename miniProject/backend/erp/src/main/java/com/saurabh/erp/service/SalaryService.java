package com.saurabh.erp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.saurabh.erp.dto.EmployeeSalaryResponse;
import com.saurabh.erp.entity.EmployeeSalary;
import com.saurabh.erp.mapper.EmployeeSalaryMapper;
import com.saurabh.erp.repo.EmployeeSalaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalaryService {
    private final EmployeeSalaryRepository employeeSalaryRepository;
    private final EmployeeSalaryMapper employeeSalaryMapper;

    private EmployeeSalaryResponse safelyMapToResponse(EmployeeSalary employeeSalary) {
        try {
            return employeeSalaryMapper.toEmployeeSalaryResponse(employeeSalary);
        } catch (JsonProcessingException e) {
            System.err.println("Error mapping EmployeeSalary to EmployeeSalaryResponse: " + e.getMessage());
            return null;
        }
    }


    public List<EmployeeSalaryResponse> getAllSalary(String employeeId) {
        int id = Integer.parseInt(employeeId);
        Optional<List<EmployeeSalary>> salaries = employeeSalaryRepository.findByEmployeeID(id);

        List<EmployeeSalaryResponse> list = new ArrayList<EmployeeSalaryResponse>();

        if(salaries.isPresent()) {
           for (EmployeeSalary employeeSalary : salaries.get()) {
               list.add(safelyMapToResponse(employeeSalary));
           }

           return list;
        }
        return null;

    }


    public List<EmployeeSalaryResponse>  getSalariesByDateRange(String employeeId, Date startDate, Date endDate) {
        int id = Integer.parseInt(employeeId);
        Optional<List<EmployeeSalary>> salaries = employeeSalaryRepository.findSalariesByDateRange(startDate, endDate, id);
        List<EmployeeSalaryResponse> list = new ArrayList<EmployeeSalaryResponse>();

        if(salaries.isPresent()) {
            for (EmployeeSalary employeeSalary : salaries.get()) {
                list.add(safelyMapToResponse(employeeSalary));
            }

            return list;
        }
        return null;
    }

    public List<EmployeeSalaryResponse> getSalariesByMonthYear(String employeeId, int month, int year) {
        int id = Integer.parseInt(employeeId);
        Optional<List<EmployeeSalary>> salaries = employeeSalaryRepository.findSalariesByMonthAndYear(month, year, id );

        List<EmployeeSalaryResponse> list = new ArrayList<EmployeeSalaryResponse>();

        if(salaries.isPresent()) {
            for (EmployeeSalary employeeSalary : salaries.get()) {
                list.add(safelyMapToResponse(employeeSalary));
            }

            return list;
        }
        return null;

    }



}
