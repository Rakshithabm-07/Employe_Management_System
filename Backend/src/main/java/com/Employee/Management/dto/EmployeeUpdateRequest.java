package com.Employee.Management.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;

public class EmployeeUpdateRequest {

    private String fullName;
    @Max(999)
    private Integer age;
    private String currentCity;
    private String currentAddressLine1;
    private String currentAddressLine2;
    @Pattern(regexp = "\\d{6}")
    private String currentPincode;
    private String permanentCity;
    private String permanentAddressLine1;
    private String permanentAddressLine2;
    @Pattern(regexp = "\\d{6}")
    private String permanentPincode;
    @Pattern(regexp = "\\d{10}")
    private String mobile;
    @Email
    private String personalEmail;
    private String emergencyContactName;
    @Pattern(regexp = "\\d{10}")
    private String emergencyContactMobile;

    // professional updatable
    private String officePhone;
    private String officeCity;
    private String officeAddressLine1;
    private String officeAddressLine2;
    @Pattern(regexp = "\\d{6}")
    private String officePincode;
    private String reportingManager;
    private String hrName;

    // finance
    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}")
    private String pan;
    @Pattern(regexp = "\\d{12}")
    private String aadhar;
    private String bankName;
    private String bankBranch;
    @Pattern(regexp = "[A-Z]{4}0[A-Z0-9]{6}")
    private String bankIfsc;
    private String ctcBreakup;

    private List<ProjectDto> projects;
    private List<EmploymentHistoryDto> employmentHistory;

    // getters & setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getCurrentAddressLine1() {
        return currentAddressLine1;
    }

    public void setCurrentAddressLine1(String currentAddressLine1) {
        this.currentAddressLine1 = currentAddressLine1;
    }

    public String getCurrentAddressLine2() {
        return currentAddressLine2;
    }

    public void setCurrentAddressLine2(String currentAddressLine2) {
        this.currentAddressLine2 = currentAddressLine2;
    }

    public String getCurrentPincode() {
        return currentPincode;
    }

    public void setCurrentPincode(String currentPincode) {
        this.currentPincode = currentPincode;
    }

    public String getPermanentCity() {
        return permanentCity;
    }

    public void setPermanentCity(String permanentCity) {
        this.permanentCity = permanentCity;
    }

    public String getPermanentAddressLine1() {
        return permanentAddressLine1;
    }

    public void setPermanentAddressLine1(String permanentAddressLine1) {
        this.permanentAddressLine1 = permanentAddressLine1;
    }

    public String getPermanentAddressLine2() {
        return permanentAddressLine2;
    }

    public void setPermanentAddressLine2(String permanentAddressLine2) {
        this.permanentAddressLine2 = permanentAddressLine2;
    }

    public String getPermanentPincode() {
        return permanentPincode;
    }

    public void setPermanentPincode(String permanentPincode) {
        this.permanentPincode = permanentPincode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactMobile() {
        return emergencyContactMobile;
    }

    public void setEmergencyContactMobile(String emergencyContactMobile) {
        this.emergencyContactMobile = emergencyContactMobile;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getOfficeCity() {
        return officeCity;
    }

    public void setOfficeCity(String officeCity) {
        this.officeCity = officeCity;
    }

    public String getOfficeAddressLine1() {
        return officeAddressLine1;
    }

    public void setOfficeAddressLine1(String officeAddressLine1) {
        this.officeAddressLine1 = officeAddressLine1;
    }

    public String getOfficeAddressLine2() {
        return officeAddressLine2;
    }

    public void setOfficeAddressLine2(String officeAddressLine2) {
        this.officeAddressLine2 = officeAddressLine2;
    }

    public String getOfficePincode() {
        return officePincode;
    }

    public void setOfficePincode(String officePincode) {
        this.officePincode = officePincode;
    }

    public String getReportingManager() {
        return reportingManager;
    }

    public void setReportingManager(String reportingManager) {
        this.reportingManager = reportingManager;
    }

    public String getHrName() {
        return hrName;
    }

    public void setHrName(String hrName) {
        this.hrName = hrName;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getBankIfsc() {
        return bankIfsc;
    }

    public void setBankIfsc(String bankIfsc) {
        this.bankIfsc = bankIfsc;
    }

    public String getCtcBreakup() {
        return ctcBreakup;
    }

    public void setCtcBreakup(String ctcBreakup) {
        this.ctcBreakup = ctcBreakup;
    }

    public java.util.List<ProjectDto> getProjects() {
        return projects;
    }

    public void setProjects(java.util.List<ProjectDto> projects) {
        this.projects = projects;
    }

    public java.util.List<EmploymentHistoryDto> getEmploymentHistory() {
        return employmentHistory;
    }

    public void setEmploymentHistory(java.util.List<EmploymentHistoryDto> employmentHistory) {
        this.employmentHistory = employmentHistory;
    }
}
