package com.smartvillage.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Eligibility {
	private Integer financialYear;
	private String religion;
	private String casteCategory;
	private Double annualIncome;
	private Boolean disability;
	private String department;
	public Eligibility() {
		super();
	}
	public Eligibility(int finantialYear, String religion, String casteCategory, double annualIncome,
			boolean disability, String department) {
		super();
		this.financialYear = finantialYear;
		this.religion = religion;
		this.casteCategory = casteCategory;
		this.annualIncome = annualIncome;
		this.disability = disability;
		this.department = department;
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
	@Override
	public String toString() {
		return "Eligibility [financialYear=" + financialYear + ", religion=" + religion + ", casteCategory="
				+ casteCategory + ", annualIncome=" + annualIncome + ", disability=" + disability + ", department="
				+ department + "]";
	}
	

}
