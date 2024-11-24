package com.saurabh.erp.controller;

import com.saurabh.erp.dto.EmployeeLoginRequest;
import com.saurabh.erp.dto.EmployeeRegisterRequest;
import com.saurabh.erp.helper.JWThelper;
import com.saurabh.erp.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final JWThelper jwtUtils;

    // Signup endpoint
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid EmployeeRegisterRequest request) {
        return ResponseEntity.ok(employeeService.registerUser(request));
    }

    @PostMapping("/setPhotoPath/{filename}")
    public ResponseEntity<?> setPhotoPath(@PathVariable String filename, @RequestHeader("Authorization") String authHeader) {
        if(!jwtUtils.verifyToken(authHeader)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid or expired token. Please log in again.");
        }
        String employeeID = jwtUtils.extractUserID(jwtUtils.getTokenFromHeader(authHeader));

        return ResponseEntity.ok(employeeService.setPhotoPath(filename, employeeID));
    }


    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid EmployeeLoginRequest request) {
        return ResponseEntity.ok(employeeService.login(request));
    }

    // Get Employee Details endpoint
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getEmployeeDetails(@PathVariable long id, @RequestHeader("Authorization") String authHeader) {
        if(!jwtUtils.verifyToken(authHeader)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid or expired token. Please log in again.");
        }
        String employeeID = jwtUtils.extractUserID(jwtUtils.getTokenFromHeader(authHeader));

       return ResponseEntity.ok(employeeService.getEmployeeDetails(id));
    }



}
