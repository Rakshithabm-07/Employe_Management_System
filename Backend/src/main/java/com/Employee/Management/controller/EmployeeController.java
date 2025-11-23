package com.Employee.Management.controller;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Employee.Management.entity.Employee;
import com.Employee.Management.entity.PayslipRecord;
import com.Employee.Management.repository.PayslipRepository;
import com.Employee.Management.service.EmployeeService;
import com.Employee.Management.service.FileStorageService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final PayslipRepository payslipRepository;
    private final FileStorageService fileStorageService;

    public EmployeeController(EmployeeService employeeService, PayslipRepository payslipRepository, FileStorageService fileStorageService) {
        this.employeeService = employeeService;
        this.payslipRepository = payslipRepository;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/me")
    public ResponseEntity<Employee> me(Authentication auth) {
        Long empId = Long.valueOf(auth.getPrincipal().toString());
        return ResponseEntity.ok(employeeService.getEmployee(empId));
    }

    @GetMapping("/me/payslips")
    public ResponseEntity<List<PayslipRecord>> myPayslips(Authentication auth) {
        Long empId = Long.valueOf(auth.getPrincipal().toString());
        return ResponseEntity.ok(payslipRepository.findTop6ByEmployeeIdOrderByUploadedAtDesc(empId));
    }

    @GetMapping("/me/payslips/{monthYear}")
    public ResponseEntity<Resource> downloadPayslip(@PathVariable String monthYear, Authentication auth) throws MalformedURLException {
        Long empId = Long.valueOf(auth.getPrincipal().toString());
        PayslipRecord rec = payslipRepository.findByEmployeeIdAndMonthYear(empId, monthYear)
                .orElseThrow(() -> new RuntimeException("Payslip not found"));
        Path path = fileStorageService.loadPath(rec);
        Resource resource = new UrlResource(path.toUri());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + path.getFileName().toString() + "\"")
                .body(resource);
    }
}
