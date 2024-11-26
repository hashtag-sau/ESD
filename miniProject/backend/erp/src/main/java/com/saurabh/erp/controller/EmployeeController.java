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

    //refresh token request
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String authHeader) {
        if(!jwtUtils.verifyRefreshToken(authHeader)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid or expired token. Please log in again.");
        }
        String employeeID = jwtUtils.extractUserID(jwtUtils.getTokenFromHeader(authHeader));
        return ResponseEntity.ok(employeeService.refreshToken(employeeID));
    }

    // Signup endpoint
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid EmployeeRegisterRequest request) {
        return ResponseEntity.ok(employeeService.registerUser(request));
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid EmployeeLoginRequest request) {
        return ResponseEntity.ok(employeeService.login(request));
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

    // Get Employee Details endpoint
    @GetMapping("/detail")
    public ResponseEntity<?> getEmployeeDetails( @RequestHeader("Authorization") String authHeader) {
        if(!jwtUtils.verifyToken(authHeader)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid or expired token. Please log in again.");
        }
        String employeeID = jwtUtils.extractUserID(jwtUtils.getTokenFromHeader(authHeader));
        int id = Integer.parseInt(employeeID);

       return ResponseEntity.ok(employeeService.getEmployeeDetails(id));
    }



}
