package com.Employee.Management.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Employee.Management.config.JwtUtil;
import com.Employee.Management.dto.LoginRequest;
import com.Employee.Management.entity.Employee;
import com.Employee.Management.repository.EmployeeRepository;

@Service
public class AuthService {

    private final EmployeeRepository employeeRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder;

    public AuthService(EmployeeRepository employeeRepository, JwtUtil jwtUtil, BCryptPasswordEncoder encoder) {
        this.employeeRepository = employeeRepository;
        this.jwtUtil = jwtUtil;
        this.encoder = encoder;
    }

    public String authenticateAndGenerateToken(LoginRequest req) {
        String identifier = req.getIdentifier();
        Employee emp = employeeRepository.findByCompanyEmailOrEmploymentCode(identifier, identifier)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!encoder.matches(req.getPassword(), emp.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return jwtUtil.generateToken(emp);
    }
}
