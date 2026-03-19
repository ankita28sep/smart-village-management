package com.smartvillage.dto.scheme;

import java.time.LocalDate;

public class SchemeResponseDto {
	private long id;
	private String name;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private Boolean active;
	private String postedBy;
	// Eligibility fields
	private Integer financialYear;
	private String religion;
	private String casteCategory;
	private double annualIncome;
	private boolean disability;
	private String department;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
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

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
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

}
