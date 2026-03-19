package com.smartvillage.dto.eligibility;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class EligibilityRequestDto {
	@NotNull(message = "Financial year is required")
	private Integer finantialYear;
	@NotBlank(message = "Religion is required")
	private String religion;
	@NotBlank(message = "Caste category is required")
	private String casteCategory;
	@Positive(message = "Annual income must be a positive number")
	@NotNull(message = "Annual income is required")
	private Double annualIncome;
	private Boolean disability;
	@NotBlank(message = "Department is required")
	private String department;
	public int getFinantialYear() {
		return finantialYear;
	}
	public void setFinantialYear(int finantialYear) {
		this.finantialYear = finantialYear;
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
	

}
