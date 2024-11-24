package com.saurabh.erp.service;

import com.saurabh.erp.dto.EmployeeLoginRequest;
import com.saurabh.erp.dto.EmployeeRegisterRequest;
import com.saurabh.erp.dto.EmployeeResponse;
import com.saurabh.erp.entity.Employee;
import com.saurabh.erp.exception.EmployeeNotFoundException;
import com.saurabh.erp.helper.EncryptionService;
import com.saurabh.erp.helper.JWThelper;
import com.saurabh.erp.mapper.EmployeeMapper;
import com.saurabh.erp.repo.EmployeeRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EncryptionService encryptionService;
    private final JWThelper jwThelper;
    private final EmployeeMapper employeeMapper;


    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email).orElseThrow(() -> new EmployeeNotFoundException(format("Employee Not Found for give EmailID : %s", email)));
    }

    public Map<String, String> login(EmployeeLoginRequest request) {
        String email = request.email();
        String password = request.password();

        Employee employee = getEmployeeByEmail(email);

        if(!encryptionService.validate(password, employee.getPassword())) {
            throw new IllegalArgumentException("Invalid Credentials");
        }

        String accessToken = jwThelper.generateAccessToken(String.valueOf(employee.getEmployeeID()));
        String refreshToken = jwThelper.generateRefreshToken(String.valueOf(employee.getEmployeeID()));

        // Prepare the response data
        Map<String, String> response = new HashMap<>();
        response.put("access_token", accessToken);
        response.put("refresh_token", refreshToken);

        return response;
    }

    public Employee getEmployeeById(long id) {
        return employeeRepository.findByEmployeeID(id).orElseThrow(() -> new EmployeeNotFoundException(format("Employee Not Found for id : %s", id)));
    }

    public EmployeeResponse getEmployeeDetails(long id){
        return employeeMapper.toEmployeeResponse(getEmployeeById(id));
    }

    public Map<String, String> registerUser(EmployeeRegisterRequest request) {
        // Check if the email already exists
        if (employeeRepository.findByEmail(request.email()).isPresent()) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        Employee employee = employeeMapper.toEmployee(request);

        employee.setPassword(encryptionService.encryptPassword(employee.getPassword()));
        Employee emp = employeeRepository.save(employee);

        String accessToken = jwThelper.generateAccessToken(String.valueOf(employee.getEmployeeID()));
        String refreshToken = jwThelper.generateRefreshToken(String.valueOf(employee.getEmployeeID()));

        emp.setRefreshToken(refreshToken);

        employeeRepository.save(emp);

        // Prepare the response data
        Map<String, String> response = new HashMap<>();
        response.put("access_token", accessToken);
        response.put("refresh_token", refreshToken);

        return response;
    }

    public String setPhotoPath(String filename, String employeeId) {
        int id = Integer.parseInt(employeeId);
        Employee employee = getEmployeeById(id);
        employee.setPhotographPath(filename);

        employeeRepository.save(employee);
        return employee.getPhotographPath();
    }
}
