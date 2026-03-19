package com.smartvillage.dto.scheme;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SchemeRequestDto {
	@NotBlank(message = "Name is required")
	private String name;
	@NotBlank(message = "Description is required")
	private String description;
	@NotNull(message = "Start date is required")
	private LocalDate startDate;
	@NotNull(message = "End date is required")
	private LocalDate endDate;
	@NotNull(message = "PostedBy is required")
	private Long postedBy;

	// Eligibility fields
	@NotNull(message = "finantial Year is required")
	private Integer financialYear;
	@NotBlank(message = "religion is required")
	private String religion;
	@NotBlank(message = "casteCategory is required")
	private String casteCategory;
	@NotNull(message = "annualIncome is required")
	private Double annualIncome;
	private Boolean disability;
	@NotBlank(message = "department is required")
	private String department;

	public Integer getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(Integer financialYear) {
		this.financialYear = financialYear;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getCasteCategory() {
		return casteCategory;
	}

	public void setCasteCategory(String casteCategory) {
		this.casteCategory = casteCategory;
	}

	public Double getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(double annualIncome) {
		this.annualIncome = annualIncome;
	}

	public Boolean isDisability() {
		return disability;
	}

	public void setDisability(boolean disability) {
		this.disability = disability;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setPostedBy(Long postedBy) {
		this.postedBy = postedBy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public long getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(long postedBy) {
		this.postedBy = postedBy;
	}

}
