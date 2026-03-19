package com.smartvillage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartvillage.entity.SchemeApplication;
import com.smartvillage.enums.ApplicationStatus;

public interface SchemeApplicationRepository extends JpaRepository<SchemeApplication, Long> {
	List<SchemeApplication> findByApplicant_Id(long applicantId);

	boolean existsByApplicant_IdAndScheme_Id(long applicantId, long schemeId);

	boolean existsByApplicant_IdAndScheme_IdAndYear(long applicantId, long schemeId, int year);
	boolean existsByAadharNoAndScheme_Id(String aadharNo, long schemeId);

	List<SchemeApplication> findByScheme_Id(long schemeId);

	List<SchemeApplication> findByStatus(ApplicationStatus status);
	
	List<SchemeApplication> findByYear(int year);

	List<SchemeApplication> findByApplicant_IdAndStatus(long applicantId, ApplicationStatus status);

	Optional<SchemeApplication> findByApplicant_IdAndScheme_Id(long applicantId, long schemeId);

	List<SchemeApplication> findByApplicant_NameContainingIgnoreCase(String applicantName);

	List<SchemeApplication> findByScheme_NameContainingIgnoreCase(String schemeName);

	List<SchemeApplication> findByScheme_IdAndStatus(long schemeId, ApplicationStatus status);
}
