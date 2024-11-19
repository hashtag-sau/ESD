package com.saurabh.myrestaurant.mapper;
import com.saurabh.myrestaurant.dto.CustomerDetailsResponse;
import com.saurabh.myrestaurant.dto.CustomerRegisterRequest;
import com.saurabh.myrestaurant.entity.Customer;
import org.hibernate.id.enhanced.CustomOptimizerDescriptor;

public class CustomerMapper {

    //CustomerRegister DTO to Customer entity
    public Customer toCustomer(CustomerRegisterRequest request) {
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password())
                .build();
    }

    //Customer entity to CustomerDetails Dto
    public CustomerDetailsResponse toCustomerDetailsResponse(Customer customer) {
        return new CustomerDetailsResponse(customer.getFirstName(), customer.getLastName(),customer.getEmail());

    }


}
