package com.Employee.Management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Employee.Management.entity.PayslipRecord;

public interface PayslipRepository extends JpaRepository<PayslipRecord, Long> {

    List<PayslipRecord> findTop6ByEmployeeIdOrderByUploadedAtDesc(Long employeeId);

    List<PayslipRecord> findByEmployeeIdOrderByUploadedAtDesc(Long employeeId);

    Optional<PayslipRecord> findByEmployeeIdAndMonthYear(Long empId, String monthYear);

}
