package com.saurabh.myrestaurant.repo;

import com.saurabh.myrestaurant.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // by extending JpaRepository<Customer, Long>, the CustomerRepo inherits methods to manage Customer entities
    // with the Long type as their identifier.
    Optional<Customer> findByEmail(String email);
}
