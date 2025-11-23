package com.Employee.Management.config;

import java.time.LocalDate;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.Employee.Management.entity.Employee;
import com.Employee.Management.repository.EmployeeRepository;

@Component
public class DataLoader implements ApplicationRunner {

    private final EmployeeRepository employeeRepository;
    private final BCryptPasswordEncoder encoder;

    public DataLoader(EmployeeRepository employeeRepository, BCryptPasswordEncoder encoder) {
        this.employeeRepository = employeeRepository;
        this.encoder = encoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        String adminEmail = "admin@company.com";
        if (employeeRepository.findByCompanyEmail(adminEmail).isEmpty()) {
            Employee admin = new Employee();
            admin.setEmploymentCode("000000");
            admin.setPassword(encoder.encode("Admin@123"));
            admin.setFullName("System Admin");
            admin.setDateOfBirth(LocalDate.of(1990, 1, 1));
            admin.setGender("Other");
            admin.setAge(35);
            admin.setMobile("9999999999");
            admin.setPersonalEmail("admin.personal@company.com");
            admin.setEmergencyContactName("Admin");
            admin.setEmergencyContactMobile("9999999999");
            admin.setCompanyEmail(adminEmail);
            admin.setOfficePhone("00000000");
            admin.setOfficeCity("HQ");
            admin.setOfficeAddressLine1("HQ");
            admin.setOfficePincode("000000");
            admin.setReportingManager("Admin");
            admin.setHrName("HR");
            admin.setDateOfJoining(LocalDate.now());
            admin.setPan("AAAAA0000A");
            admin.setAadhar("000000000000");
            admin.setBankName("AdminBank");
            admin.setBankBranch("HQ");
            admin.setBankIfsc("AAAA0BBBBBB");
            admin.setCtcBreakup("ADMIN");
            admin.setRole("ADMIN");
            employeeRepository.save(admin);
            System.out.println("Bootstrapped admin: admin@company.com -> Admin@123 (change immediately)");
        }
    }
}
