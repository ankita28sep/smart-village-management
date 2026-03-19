package com.smartvillage.dto.schemeApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.smartvillage.enums.ApplicationStatus;

public class SchemeApplicationResponseDto {
	private long id;
	private Long applicantId;
	private Long schemeId;
	private String applicantName;
	private String schemeName;
	private ApplicationStatus status;
	private LocalDateTime appliedAt; 
	private Integer year;
	private String fullName;
	private LocalDate dob;
	private String address;
	private String mobile;
	private String maskedAadharNo;
	private Integer financialYear;
	private Double annualIncome;
	private String religion;
	private String caste;
	private String department;
	private boolean disability;
	private String aadharDocPath;
	private String incomeCertPath;
	private String bankPassbookPath;
	private String photoPath;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public ApplicationStatus getStatus() {
		return status;
	}
	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}
	public LocalDateTime getAppliedAt() {
		return appliedAt;
	}
	public void setAppliedAt(LocalDateTime appliedAt) {
		this.appliedAt = appliedAt;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
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
		return maskedAadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.maskedAadharNo = aadharNo;
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
	public boolean isDisability() {
		return disability;
	}
	public void setDisability(boolean disability) {
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
