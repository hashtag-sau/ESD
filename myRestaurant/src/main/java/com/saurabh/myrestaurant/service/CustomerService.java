package com.saurabh.myrestaurant.service;

import com.saurabh.myrestaurant.dto.CustomerDetailsResponse;
import com.saurabh.myrestaurant.dto.CustomerRegisterRequest;
import com.saurabh.myrestaurant.dto.LoginRequest;
import com.saurabh.myrestaurant.entity.Customer;
import com.saurabh.myrestaurant.exception.CustomerNotFoundException;
import com.saurabh.myrestaurant.helper.EncryptionService;
import com.saurabh.myrestaurant.helper.JWThelper;
import com.saurabh.myrestaurant.mapper.CustomerMapper;
import com.saurabh.myrestaurant.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.id.enhanced.CustomOptimizerDescriptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final EncryptionService encryptionService;
    private final JWThelper jwtHelper;
    private final CustomerMapper customerMapper;

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email).orElseThrow(() -> new CustomerNotFoundException(format("Customer Not Found for give EmailID : %s", email)));
    }

    public CustomerDetailsResponse retrieveCustomer(String email) {
        return customerMapper.toCustomerDetailsResponse(getCustomerByEmail(email));
    }


    public String login(LoginRequest loginRequest) {
        String email = loginRequest.email();
        String password = loginRequest.password();
        Customer customer = getCustomerByEmail(email);

        if(!encryptionService.validate(password, customer.getPassword())) {
            return "Invalid Password";
        }

        return jwtHelper.generateToken(email);
    }

    public String registerUser(CustomerRegisterRequest customerRegisterRequest) {
        // Check if the email already exists
        if (customerRepository.findByEmail(customerRegisterRequest.email()).isPresent()) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        Customer customer = customerMapper.toCustomer(customerRegisterRequest);
        customer.setPassword(encryptionService.encryptPassword(customer.getPassword()));
        customerRepository.save(customer);
        return jwtHelper.generateToken(customer.getEmail());

    }
}
