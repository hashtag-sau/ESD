package com.saurabh.myrestaurant.controller;

import com.saurabh.myrestaurant.dto.CustomerRegisterRequest;
import com.saurabh.myrestaurant.dto.LoginRequest;
import com.saurabh.myrestaurant.entity.Customer;
import com.saurabh.myrestaurant.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final CustomerService customerService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(customerService.login(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid CustomerRegisterRequest customerRegisterRequest) {
        return ResponseEntity.ok(customerService.registerUser(customerRegisterRequest));
    }
}
