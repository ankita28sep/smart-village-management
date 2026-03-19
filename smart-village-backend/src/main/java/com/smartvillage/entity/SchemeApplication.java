package com.smartvillage.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smartvillage.enums.ApplicationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "scheme_applications", uniqueConstraints = @UniqueConstraint(columnNames = { "applicant_id",
		"scheme_id" }))

public class SchemeApplication {
//CORE	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "applicant_id", nullable = false)
	@JsonIgnore
	private User applicant;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "scheme_id", nullable = false)
	private Scheme scheme;

//APPLICATION TRACKING	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ApplicationStatus status;

	@Column(nullable = false)
	private LocalDateTime appliedAt;
	@Column(nullable = false)
	private Integer year;
//PERSONAL INFO	
	@Column(nullable = false)
	private String fullName;
	@Column(nullable = false)
	private LocalDate dob;
	@Column(nullable = false, length = 500, columnDefinition = "TEXT")
	private String address;
	@Column(nullable = false, length = 10)
	@Pattern(regexp="\\d{10}")
	private String mobile;
	@Column(nullable = false, length = 12)
	@Pattern(regexp="\\d{12}")
	private String aadharNo;
//ELIGIBILITY
	private Integer financialYear;
	private Double annualIncome;
	private String religion;
	private String caste;
	private String department;
	private boolean disability;
//DOCUMENTS	  
	private String aadharDocPath;
	private String incomeCertPath;
	private String bankPassbookPath;
	private String photoPath;

	@PrePersist
	public void onCreate() {
		this.appliedAt = LocalDateTime.now();
		if (this.status == null) {
			this.status = ApplicationStatus.PENDING;
		}
		this.year = LocalDate.now().getYear();
	}

	public SchemeApplication() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getApplicant() {
		return applicant;
	}

	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}

	public Scheme getScheme() {
		return scheme;
	}

	public void setScheme(Scheme scheme) {
		this.scheme = scheme;
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
	

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	@Override
	public String toString() {
		return "SchemeApplication [id=" + id + ", applicant=" + applicant.getName() + ", scheme=" + scheme.getId() + ", status=" + status
				+ "]";
	}

}