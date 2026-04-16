package com.smartvillage.mapper;

import com.smartvillage.dto.schemeApplication.SchemeApplicationRequestDto;
import com.smartvillage.dto.schemeApplication.SchemeApplicationResponseDto;
import com.smartvillage.dto.schemeApplication.SchemeApplicationUpdateDto;
import com.smartvillage.entity.Scheme;
import com.smartvillage.entity.SchemeApplication;
import com.smartvillage.entity.User;

public class SchemeApplicationMapper {

    // DTO → Entity
    public static SchemeApplication toEntity(SchemeApplicationRequestDto dto, User applicant, Scheme scheme) {
        SchemeApplication application = new SchemeApplication();

        application.setApplicant(applicant);
        application.setScheme(scheme);

        application.setFullName(dto.getFullName());
        application.setDob(dto.getDob());
        application.setAddress(dto.getAddress());
        application.setMobile(dto.getMobile());
        application.setAadharNo(dto.getAadharNo());
        application.setFinancialYear(dto.getFinancialYear());
        application.setAnnualIncome(dto.getAnnualIncome());
        application.setReligion(dto.getReligion());
        application.setCaste(dto.getCaste());
        application.setDepartment(dto.getDepartment());
        application.setDisability(dto.getDisability());

        application.setAadharDocPath(dto.getAadharDocPath());
        application.setIncomeCertPath(dto.getIncomeCertPath());
        application.setBankPassbookPath(dto.getBankPassbookPath());
        application.setPhotoPath(dto.getPhotoPath());

        return application;
    }

    // Entity → DTO
    public static SchemeApplicationResponseDto toDto(SchemeApplication application) {
        SchemeApplicationResponseDto dto = new SchemeApplicationResponseDto();

        dto.setId(application.getId());
        dto.setStatus(application.getStatus());
        dto.setAppliedAt(application.getAppliedAt());
        dto.setYear(application.getYear());

        // Applicant info (safe)
        if (application.getApplicant() != null) {
            dto.setApplicantId(application.getApplicant().getId());
            dto.setApplicantName(application.getApplicant().getName());
        }

        // Scheme info (safe)
        if (application.getScheme() != null) {
            dto.setSchemeId(application.getScheme().getId());
            dto.setSchemeName(application.getScheme().getName());
        }

        dto.setFullName(application.getFullName());
        dto.setDob(application.getDob());
        dto.setAddress(application.getAddress());
        dto.setMobile(application.getMobile());

        // Mask Aadhar (good practice)
        if (application.getAadharNo() != null && application.getAadharNo().length() >= 4) {
            String last4 = application.getAadharNo()
                    .substring(application.getAadharNo().length() - 4);
            dto.setAadharNo("xxxx-xxxx-" + last4);
        } else {
            dto.setAadharNo(null);
        }

        dto.setFinancialYear(application.getFinancialYear());
        dto.setAnnualIncome(application.getAnnualIncome());
        dto.setReligion(application.getReligion());
        dto.setCaste(application.getCaste());
        dto.setDepartment(application.getDepartment());
        dto.setDisability(application.isDisability());

        dto.setAadharDocPath(application.getAadharDocPath());
        dto.setIncomeCertPath(application.getIncomeCertPath());
        dto.setBankPassbookPath(application.getBankPassbookPath());
        dto.setPhotoPath(application.getPhotoPath());

        return dto;
    }

    // Update Entity (merge, no overwrite)
    public static void updateEntity(SchemeApplication application, SchemeApplicationUpdateDto dto) {

        if (dto.getFullName() != null)
            application.setFullName(dto.getFullName());

        if (dto.getDob() != null)
            application.setDob(dto.getDob());

        if (dto.getAddress() != null)
            application.setAddress(dto.getAddress());

        if (dto.getMobile() != null)
            application.setMobile(dto.getMobile());

        if (dto.getAnnualIncome() != null)
            application.setAnnualIncome(dto.getAnnualIncome());

        if (dto.getReligion() != null)
            application.setReligion(dto.getReligion());

        if (dto.getCaste() != null)
            application.setCaste(dto.getCaste());

        if (dto.getDisability() != null)
            application.setDisability(dto.getDisability());

        if (dto.getDepartment() != null)
            application.setDepartment(dto.getDepartment());

        if (dto.getAadharDocPath() != null)
            application.setAadharDocPath(dto.getAadharDocPath());

        if (dto.getIncomeCertPath() != null)
            application.setIncomeCertPath(dto.getIncomeCertPath());

        if (dto.getBankPassbookPath() != null)
            application.setBankPassbookPath(dto.getBankPassbookPath());

        if (dto.getPhotoPath() != null)
            application.setPhotoPath(dto.getPhotoPath());
    }
}