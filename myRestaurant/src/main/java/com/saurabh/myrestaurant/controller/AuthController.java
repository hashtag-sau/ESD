package com.saurabh.myrestaurant.controller;

import com.saurabh.myrestaurant.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final CustomerService customerService;

    @PostMapping("/login")
    public ResponseEntity<String> login(){

    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(){

    }
}
