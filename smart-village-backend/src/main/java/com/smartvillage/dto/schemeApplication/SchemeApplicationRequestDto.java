package com.smartvillage.dto.schemeApplication;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class SchemeApplicationRequestDto {

    @NotNull(message = "applicantId is required")
    private Long applicantId;

    @NotNull(message = "schemeId is required")
    private Long schemeId;

    // PERSONAL INFO
    @NotBlank(message = "fullName is required")
    private String fullName;

    @NotNull(message = "dob is required")
    private LocalDate dob;

    @NotBlank(message = "address is required")
    private String address;

    @NotBlank(message = "mobile is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "mobile must be 10 digits")
    private String mobile;

    @NotBlank(message = "aadharNo is required")
    @Pattern(regexp = "^[0-9]{12}$", message = "aadhar must be 12 digits")
    private String aadharNo;

    // ELIGIBILITY
    @NotNull(message = "financialYear is required")
    private Integer financialYear;

    @NotNull(message = "annualIncome is required")
    @Positive(message = "annualIncome must be positive")
    private Double annualIncome;

    @NotBlank(message = "religion is required")
    private String religion;

    @NotBlank(message = "caste is required")
    private String caste;

    @NotBlank(message = "department is required")
    private String department;

    private Boolean disability;

    // DOCUMENTS
    @NotBlank(message = "aadharDocPath is required")
    private String aadharDocPath;

    @NotBlank(message = "incomeCertPath is required")
    private String incomeCertPath;

    @NotBlank(message = "bankPassbookPath is required")
    private String bankPassbookPath;

    @NotBlank(message = "photoPath is required")
    private String photoPath;

    // Getters & Setters

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    public Long getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(Long schemeId) {
        this.schemeId = schemeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(Integer financialYear) {
        this.financialYear = financialYear;
    }

    public Double getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(Double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Boolean getDisability() {
        return disability;
    }

    public void setDisability(Boolean disability) {
        this.disability = disability;
    }

    public String getAadharDocPath() {
        return aadharDocPath;
    }

    public void setAadharDocPath(String aadharDocPath) {
        this.aadharDocPath = aadharDocPath;
    }

    public String getIncomeCertPath() {
        return incomeCertPath;
    }

    public void setIncomeCertPath(String incomeCertPath) {
        this.incomeCertPath = incomeCertPath;
    }

    public String getBankPassbookPath() {
        return bankPassbookPath;
    }

    public void setBankPassbookPath(String bankPassbookPath) {
        this.bankPassbookPath = bankPassbookPath;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}