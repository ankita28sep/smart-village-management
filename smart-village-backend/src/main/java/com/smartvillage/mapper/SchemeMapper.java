package com.smartvillage.mapper;

import com.smartvillage.dto.scheme.SchemeRequestDto;
import com.smartvillage.dto.scheme.SchemeResponseDto;
import com.smartvillage.dto.scheme.SchemeUpdateDto;
import com.smartvillage.entity.Eligibility;
import com.smartvillage.entity.Scheme;
import com.smartvillage.entity.User;

public class SchemeMapper {

    // Convert Request DTO to Entity
    public static Scheme toEntity(SchemeRequestDto dto, User postedBy) {

        Scheme scheme = new Scheme();

        scheme.setName(dto.getName().trim());
        scheme.setDescription(dto.getDescription());
        scheme.setStartDate(dto.getStartDate());
        scheme.setEndDate(dto.getEndDate());

        // Eligibility mapping
        Eligibility eligibility = new Eligibility();
        eligibility.setFinancialYear(dto.getFinancialYear());
        eligibility.setCasteCategory(dto.getCasteCategory());
        eligibility.setReligion(dto.getReligion());
        eligibility.setAnnualIncome(dto.getAnnualIncome());
        eligibility.setDisability(dto.isDisability());
        eligibility.setDepartment(dto.getDepartment());

        scheme.setEligibility(eligibility);
        scheme.setActive(true);

        if (postedBy != null) {
            scheme.setPostedBy(postedBy);
        }

        return scheme;
    }

    // Convert Entity to Response DTO
    public static SchemeResponseDto toDto(Scheme scheme) {

        SchemeResponseDto dto = new SchemeResponseDto();

        dto.setId(scheme.getId());
        dto.setName(scheme.getName());
        dto.setDescription(scheme.getDescription());

        if (scheme.getEligibility() != null) {
            dto.setFinancialYear(scheme.getEligibility().getFinancialYear());
            dto.setReligion(scheme.getEligibility().getReligion());
            dto.setCasteCategory(scheme.getEligibility().getCasteCategory());
            dto.setAnnualIncome(scheme.getEligibility().getAnnualIncome());
            dto.setDisability(scheme.getEligibility().isDisability());
            dto.setDepartment(scheme.getEligibility().getDepartment());
        }

        dto.setStartDate(scheme.getStartDate());
        dto.setEndDate(scheme.getEndDate());
        dto.setActive(scheme.isActive());

        if (scheme.getPostedBy() != null) {
            dto.setPostedBy(scheme.getPostedBy().getName());
        }

        return dto;
    }

    // Update existing entity with Update DTO (partial update)
    public static void updateEntity(Scheme scheme, SchemeUpdateDto dto) {

        if (dto.getName() != null) {
            scheme.setName(dto.getName().trim());
        }

        if (dto.getDescription() != null) {
            scheme.setDescription(dto.getDescription());
        }

        // Handle eligibility safely
        Eligibility eligibility = scheme.getEligibility();
        if (eligibility == null) {
            eligibility = new Eligibility();
        }

        if (dto.getFinancialYear() != null) {
            eligibility.setFinancialYear(dto.getFinancialYear());
        }

        if (dto.getCasteCategory() != null) {
            eligibility.setCasteCategory(dto.getCasteCategory());
        }

        if (dto.getReligion() != null) {
            eligibility.setReligion(dto.getReligion());
        }

        if (dto.getAnnualIncome() != null) {
            eligibility.setAnnualIncome(dto.getAnnualIncome());
        }

        if (dto.getDisability() != null) {
            eligibility.setDisability(dto.getDisability());
        }

        if (dto.getDepartment() != null) {
            eligibility.setDepartment(dto.getDepartment());
        }

        scheme.setEligibility(eligibility);

        if (dto.getStartDate() != null) {
            scheme.setStartDate(dto.getStartDate());
        }

        if (dto.getEndDate() != null) {
            scheme.setEndDate(dto.getEndDate());
        }

        if (dto.getActive() != null) {
            scheme.setActive(dto.getActive());
        }
    }
}