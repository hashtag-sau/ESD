package com.saurabh.erp.controller;

import com.saurabh.erp.dto.EmployeeSalaryResponse;
import com.saurabh.erp.entity.EmployeeSalary;
import com.saurabh.erp.helper.JWThelper;
import com.saurabh.erp.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeSalaryController {
    private final SalaryService salaryService;
    private final JWThelper jwtUtils;

    @GetMapping("/salary/all")
    public ResponseEntity<?> getAllSalary(@RequestHeader("Authorization") String authHeader) {
        if(!jwtUtils.verifyToken(authHeader)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid or expired token. Please log in again.");
        }
        String employeeID = jwtUtils.extractUserID(jwtUtils.getTokenFromHeader(authHeader));

        List<EmployeeSalaryResponse> salaries = salaryService.getAllSalary(employeeID);
        return ResponseEntity.ok(salaries);
    }

    // salaries?startDate= endDate=
    @GetMapping("/salaries")
    public ResponseEntity<?> getSalaryByDate(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,@RequestHeader("Authorization") String authHeader) throws ParseException {

        if(!jwtUtils.verifyToken(authHeader)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid or expired token. Please log in again.");
        }
        String employeeID = jwtUtils.extractUserID(jwtUtils.getTokenFromHeader(authHeader));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // string to date object
        Date start = dateFormat.parse(startDate);
        Date end = dateFormat.parse(endDate);

        List<EmployeeSalaryResponse> salaries = salaryService.getSalariesByDateRange(employeeID, start, end);

        return ResponseEntity.ok(salaries);
    }

    @GetMapping("/salary")
    public ResponseEntity<?> getSalaryByMonth(@RequestParam("month") int month, @RequestParam("year") int year, @RequestHeader("Authorization") String authHeader ) {
        if(!jwtUtils.verifyToken(authHeader)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid or expired token. Please log in again.");
        }
        String employeeID = jwtUtils.extractUserID(jwtUtils.getTokenFromHeader(authHeader));

        if ((month < 1 || month > 12) || (year < 1900 || year > 2099)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid month. Month must be between 1 and 12.");
        }

        List<EmployeeSalaryResponse> salaries = salaryService.getSalariesByMonthYear(employeeID, month, year);

        return ResponseEntity.ok(salaries);

    }
}
