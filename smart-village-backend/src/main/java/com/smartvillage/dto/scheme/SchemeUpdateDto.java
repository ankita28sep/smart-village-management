package com.smartvillage.dto.scheme;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class SchemeUpdateDto {
	@NotBlank(message = "Scheme name is required")
	private String name;
	@NotBlank(message = "Description is required")
	private String description;
	// Eligibility fields
	@NotNull(message = "Financial year is required")
	@Positive(message = "Financial year must be a positive integer")
	private Integer financialYear;
	@NotBlank(message = "religion is required")
	private String religion;
	@NotBlank(message = "casteCategory is required")
	private String casteCategory;
	@Positive(message = "Annual income must be a positive number")
	@NotNull(message = "annualIncome is required")
	private Double annualIncome;
	@NotNull(message = "disability is required")
	private Boolean disability;
	@NotBlank(message = "department is required")
	private String department;
	@NotNull(message = "Start date is required")
	private LocalDate startDate;
	@NotNull(message = "End date is required")
	private LocalDate endDate;
	private Boolean active;

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

	public int getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(int financialYear) {
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

	public double getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(double annualIncome) {
		this.annualIncome = annualIncome;
	}

	public boolean isDisability() {
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
