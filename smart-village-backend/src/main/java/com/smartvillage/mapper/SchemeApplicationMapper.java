package com.smartvillage.mapper;

import com.smartvillage.dto.schemeApplication.SchemeApplicationRequestDto;
import com.smartvillage.dto.schemeApplication.SchemeApplicationResponseDto;
import com.smartvillage.dto.schemeApplication.SchemeApplicationUpdateDto;
import com.smartvillage.entity.Scheme;
import com.smartvillage.entity.SchemeApplication;
import com.smartvillage.entity.User;

public class SchemeApplicationMapper {
	// TO CONVERT THE REQUESTDTO TO ENTITY
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
		application.setDisability(dto.isDisability());
		application.setAadharDocPath(dto.getAadharDocPath());
		application.setIncomeCertPath(dto.getIncomeCertPath());
		application.setBankPassbookPath(dto.getBankPassbookPath());
		application.setPhotoPath(dto.getPhotoPath());
		return application;
	}

	// TO CONVERT THE ENTITY TO RESPONSEDTO
	public static SchemeApplicationResponseDto toDto(SchemeApplication application) {
		SchemeApplicationResponseDto dto = new SchemeApplicationResponseDto();
		dto.setId(application.getId());
		dto.setStatus(application.getStatus());

		if (application.getApplicant() != null) {
			dto.setApplicantName(application.getApplicant().getName());
			dto.setApplicantId(application.getApplicant().getId());
		}
		if (application.getScheme() != null) {
			dto.setSchemeName(application.getScheme().getName());
			dto.setSchemeId(application.getScheme().getId());
		}
		dto.setAppliedAt(application.getAppliedAt());
		dto.setYear(application.getYear());
		dto.setFullName(application.getFullName());
		dto.setDob(application.getDob());
		dto.setAddress(application.getAddress());
		dto.setMobile(application.getMobile());
		if (application.getAadharNo() != null && application.getAadharNo().length() >= 4) {
			dto.setAadharNo("xxxx-xxxx-" + application.getAadharNo().substring(application.getAadharNo().length() - 4));
		} else {
			dto.setAadharNo(null);
		}
		dto.setFinancialYear(application.getFinancialYear());
		dto.setAnnualIncome(application.getAnnualIncome());
		dto.setReligion(application.getReligion());
		dto.setCaste(application.getCaste());
		dto.setDisability(application.isDisability());
		dto.setDepartment(application.getDepartment());
		dto.setAadharDocPath(application.getAadharDocPath());
		dto.setIncomeCertPath(application.getIncomeCertPath());
		dto.setBankPassbookPath(application.getBankPassbookPath());
		dto.setPhotoPath(application.getPhotoPath());

		return dto;

	}

	// UPDATE ENTITY
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
		application.setDisability(dto.isDisability());
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
