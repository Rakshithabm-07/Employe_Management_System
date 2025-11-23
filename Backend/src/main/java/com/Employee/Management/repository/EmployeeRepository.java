package com.Employee.Management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Employee.Management.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmploymentCode(String code);

    Optional<Employee> findByCompanyEmail(String email);

    Optional<Employee> findByCompanyEmailOrEmploymentCode(String email, String code);
}
