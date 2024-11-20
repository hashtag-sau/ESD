package com.saurabh.myrestaurant.controller;

import com.saurabh.myrestaurant.dto.CustomerDetailsResponse;
import com.saurabh.myrestaurant.helper.JWThelper;
import com.saurabh.myrestaurant.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final JWThelper jwtUtils;

    @GetMapping("/profile")
    public ResponseEntity<Object> getCustomerDetails(@RequestHeader("Authorization") String authHeader) {
        if(!jwtUtils.verifyToken(authHeader)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid or expired token. Please log in again.");
        }
        String email = jwtUtils.extractUserName(jwtUtils.getTokenFromHeader(authHeader));
        return ResponseEntity.ok(customerService.retrieveCustomer(email));

    }
}
