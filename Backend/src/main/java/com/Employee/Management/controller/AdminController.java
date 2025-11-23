package com.Employee.Management.controller;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Employee.Management.dto.EmployeeCreateRequest;
import com.Employee.Management.entity.Employee;
import com.Employee.Management.entity.PayslipRecord;
import com.Employee.Management.repository.PayslipRepository;
import com.Employee.Management.service.EmployeeService;
import com.Employee.Management.service.FileStorageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final EmployeeService employeeService;
    private final FileStorageService fileStorageService;
    private final PayslipRepository payslipRepository;

    public AdminController(EmployeeService employeeService,
            FileStorageService fileStorageService,
            PayslipRepository payslipRepository) {
        this.employeeService = employeeService;
        this.fileStorageService = fileStorageService;
        this.payslipRepository = payslipRepository;
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeCreateRequest req) {
        Employee e = employeeService.createEmployee(req);
        return ResponseEntity.ok(e);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> listAll() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @PostMapping(
            value = "/employees/{id}/payslips",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PayslipRecord> uploadPayslip(
            @PathVariable Long id,
            @RequestParam("monthYear") String monthYear,
            @RequestPart("file") MultipartFile file) throws IOException {

        // safety validation
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("File is missing");
        }
        if (!file.getOriginalFilename().toLowerCase().endsWith(".pdf")) {
            throw new RuntimeException("Only PDF payslips are allowed");
        }

        // store file safely using new service
        byte[] bytes = file.getBytes();
        String storedPath = fileStorageService.storeFile(id, monthYear, bytes);

        // save metadata to DB
        PayslipRecord rec = new PayslipRecord();
        rec.setEmployeeId(id);
        rec.setMonthYear(monthYear);
        rec.setFilename(storedPath);
        rec.setUploadedAt(Instant.now());

        payslipRepository.save(rec);

        return ResponseEntity.ok(rec);
    }

    @GetMapping("/employees/{id}/payslips")
    public ResponseEntity<List<PayslipRecord>> listEmployeePayslips(@PathVariable Long id) {
        return ResponseEntity.ok(
                payslipRepository.findByEmployeeIdOrderByUploadedAtDesc(id));
    }
}
