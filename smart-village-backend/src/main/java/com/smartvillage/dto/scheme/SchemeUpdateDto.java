package com.smartvillage.dto.scheme;

import java.time.LocalDate;

public class SchemeUpdateDto {

    private String name;

    private String description;

    // Eligibility fields (all optional for update)
    private Integer financialYear;

    private String religion;

    private String casteCategory;

    private Double annualIncome;

    private Boolean disability;

    private String department;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean active;

    // Getters and Setters

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

    public void setAnnualIncome(Double annualIncome) {  
        this.annualIncome = annualIncome;
    }

    public Boolean getDisability() {  
        return disability;
    }

    public void setDisability(Boolean disability) {   
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