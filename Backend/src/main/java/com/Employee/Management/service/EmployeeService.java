package com.Employee.Management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Employee.Management.dto.EmployeeCreateRequest;
import com.Employee.Management.dto.EmployeeUpdateRequest;
import com.Employee.Management.dto.EmploymentHistoryDto;
import com.Employee.Management.dto.ProjectDto;
import com.Employee.Management.entity.Employee;
import com.Employee.Management.entity.EmploymentHistory;
import com.Employee.Management.entity.ProjectAssignment;
import com.Employee.Management.repository.EmployeeRepository;
import com.Employee.Management.util.exceptions.ResourceNotFoundException;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BCryptPasswordEncoder encoder;

    public EmployeeService(EmployeeRepository employeeRepository, BCryptPasswordEncoder encoder) {
        this.employeeRepository = employeeRepository;
        this.encoder = encoder;
    }

    @Transactional
    public Employee createEmployee(EmployeeCreateRequest req) {
        if (employeeRepository.findByEmploymentCode(req.getEmploymentCode()).isPresent()) {
            throw new RuntimeException("Employment code already exists");
        }
        if (employeeRepository.findByCompanyEmail(req.getCompanyEmail()).isPresent()) {
            throw new RuntimeException("Company email already exists");
        }

        Employee e = new Employee();
        e.setEmploymentCode(req.getEmploymentCode());
        e.setPassword(encoder.encode(req.getPassword()));
        e.setFullName(req.getFullName());
        e.setDateOfBirth(req.getDateOfBirth());
        e.setGender(req.getGender());
        e.setAge(req.getAge());
        e.setCurrentCity(req.getCurrentCity());
        e.setCurrentAddressLine1(req.getCurrentAddressLine1());
        e.setCurrentAddressLine2(req.getCurrentAddressLine2());
        e.setCurrentPincode(req.getCurrentPincode());
        e.setPermanentCity(req.getPermanentCity());
        e.setPermanentAddressLine1(req.getPermanentAddressLine1());
        e.setPermanentAddressLine2(req.getPermanentAddressLine2());
        e.setPermanentPincode(req.getPermanentPincode());
        e.setMobile(req.getMobile());
        e.setPersonalEmail(req.getPersonalEmail());
        e.setEmergencyContactName(req.getEmergencyContactName());
        e.setEmergencyContactMobile(req.getEmergencyContactMobile());
        e.setCompanyEmail(req.getCompanyEmail());
        e.setOfficePhone(req.getOfficePhone());
        e.setOfficeCity(req.getOfficeCity());
        e.setOfficeAddressLine1(req.getOfficeAddressLine1());
        e.setOfficeAddressLine2(req.getOfficeAddressLine2());
        e.setOfficePincode(req.getOfficePincode());
        e.setReportingManager(req.getReportingManager());
        e.setHrName(req.getHrName());
        e.setDateOfJoining(req.getDateOfJoining());
        e.setPan(req.getPan());
        e.setAadhar(req.getAadhar());
        e.setBankName(req.getBankName());
        e.setBankBranch(req.getBankBranch());
        e.setBankIfsc(req.getBankIfsc());
        e.setCtcBreakup(req.getCtcBreakup());

        if (req.getProjects() != null) {
            List<ProjectAssignment> projects = new ArrayList<>();
            for (ProjectDto pd : req.getProjects()) {
                ProjectAssignment p = new ProjectAssignment();
                p.setProjectCode(pd.getProjectCode());
                p.setProjectName(pd.getProjectName());
                p.setClientName(pd.getClientName());
                p.setStartDate(pd.getStartDate());
                p.setEndDate(pd.getEndDate());
                p.setReportingManager(pd.getReportingManager());
                projects.add(p);
            }
            e.setProjects(projects);
        }

        if (req.getEmploymentHistory() != null) {
            List<EmploymentHistory> hist = new ArrayList<>();
            for (EmploymentHistoryDto hd : req.getEmploymentHistory()) {
                EmploymentHistory h = new EmploymentHistory();
                h.setCompanyName(hd.getCompanyName());
                h.setStartDate(hd.getStartDate());
                h.setEndDate(hd.getEndDate());
                hist.add(h);
            }
            e.setEmploymentHistory(hist);
        }

        e.setRole("EMPLOYEE");
        return employeeRepository.save(e);
    }

    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Transactional
    public Employee updateEmployee(Long id, EmployeeUpdateRequest req, boolean isAdmin) {
        Employee e = getEmployee(id);

        if (req.getFullName() != null) {
            e.setFullName(req.getFullName());
        }
        if (req.getAge() != null) {
            e.setAge(req.getAge());
        }
        if (req.getCurrentCity() != null) {
            e.setCurrentCity(req.getCurrentCity());
        }
        if (req.getCurrentAddressLine1() != null) {
            e.setCurrentAddressLine1(req.getCurrentAddressLine1());
        }
        if (req.getCurrentAddressLine2() != null) {
            e.setCurrentAddressLine2(req.getCurrentAddressLine2());
        }
        if (req.getCurrentPincode() != null) {
            e.setCurrentPincode(req.getCurrentPincode());
        }
        if (req.getPermanentCity() != null) {
            e.setPermanentCity(req.getPermanentCity());
        }
        if (req.getPermanentAddressLine1() != null) {
            e.setPermanentAddressLine1(req.getPermanentAddressLine1());
        }
        if (req.getPermanentAddressLine2() != null) {
            e.setPermanentAddressLine2(req.getPermanentAddressLine2());
        }
        if (req.getPermanentPincode() != null) {
            e.setPermanentPincode(req.getPermanentPincode());
        }
        if (req.getMobile() != null) {
            e.setMobile(req.getMobile());
        }
        if (req.getPersonalEmail() != null) {
            e.setPersonalEmail(req.getPersonalEmail());
        }
        if (req.getEmergencyContactName() != null) {
            e.setEmergencyContactName(req.getEmergencyContactName());
        }
        if (req.getEmergencyContactMobile() != null) {
            e.setEmergencyContactMobile(req.getEmergencyContactMobile());
        }

        if (req.getOfficePhone() != null) {
            e.setOfficePhone(req.getOfficePhone());
        }
        if (req.getOfficeCity() != null) {
            e.setOfficeCity(req.getOfficeCity());
        }
        if (req.getOfficeAddressLine1() != null) {
            e.setOfficeAddressLine1(req.getOfficeAddressLine1());
        }
        if (req.getOfficeAddressLine2() != null) {
            e.setOfficeAddressLine2(req.getOfficeAddressLine2());
        }
        if (req.getOfficePincode() != null) {
            e.setOfficePincode(req.getOfficePincode());
        }
        if (req.getReportingManager() != null) {
            e.setReportingManager(req.getReportingManager());
        }
        if (req.getHrName() != null) {
            e.setHrName(req.getHrName());
        }

        if (req.getPan() != null) {
            e.setPan(req.getPan());
        }
        if (req.getAadhar() != null) {
            e.setAadhar(req.getAadhar());
        }
        if (req.getBankName() != null) {
            e.setBankName(req.getBankName());
        }
        if (req.getBankBranch() != null) {
            e.setBankBranch(req.getBankBranch());
        }
        if (req.getBankIfsc() != null) {
            e.setBankIfsc(req.getBankIfsc());
        }
        if (req.getCtcBreakup() != null) {
            e.setCtcBreakup(req.getCtcBreakup());
        }

        if (req.getProjects() != null) {
            List<ProjectAssignment> projects = new ArrayList<>();
            for (ProjectDto pd : req.getProjects()) {
                ProjectAssignment p = new ProjectAssignment();
                p.setProjectCode(pd.getProjectCode());
                p.setProjectName(pd.getProjectName());
                p.setClientName(pd.getClientName());
                p.setStartDate(pd.getStartDate());
                p.setEndDate(pd.getEndDate());
                p.setReportingManager(pd.getReportingManager());
                projects.add(p);
            }
            e.setProjects(projects);
        }

        if (req.getEmploymentHistory() != null) {
            List<EmploymentHistory> hist = new ArrayList<>();
            for (EmploymentHistoryDto hd : req.getEmploymentHistory()) {
                EmploymentHistory h = new EmploymentHistory();
                h.setCompanyName(hd.getCompanyName());
                h.setStartDate(hd.getStartDate());
                h.setEndDate(hd.getEndDate());
                hist.add(h);
            }
            e.setEmploymentHistory(hist);
        }

        return employeeRepository.save(e);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
