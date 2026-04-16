package com.smartvillage.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.smartvillage.entity.SchemeApplication;
import com.smartvillage.enums.ApplicationStatus;

public interface SchemeApplicationRepository extends JpaRepository<SchemeApplication, Long> {

    // ================= DUPLICATE CHECKS =================

    boolean existsByApplicant_IdAndScheme_Id(long applicantId, long schemeId);

    boolean existsByApplicant_IdAndScheme_IdAndYear(long applicantId, long schemeId, int year);

    boolean existsByAadharNoAndScheme_Id(String aadharNo, long schemeId);

    // ================= PAGINATED FETCH =================

    Page<SchemeApplication> findByApplicant_Id(long applicantId, Pageable pageable);

    Page<SchemeApplication> findByScheme_Id(long schemeId, Pageable pageable);

    Page<SchemeApplication> findByStatus(ApplicationStatus status, Pageable pageable);

    Page<SchemeApplication> findByYear(int year, Pageable pageable);

    Page<SchemeApplication> findByApplicant_IdAndStatus(long applicantId, ApplicationStatus status, Pageable pageable);

    Page<SchemeApplication> findByScheme_IdAndStatus(long schemeId, ApplicationStatus status, Pageable pageable);

    Page<SchemeApplication> findByApplicant_NameContainingIgnoreCase(String applicantName, Pageable pageable);

    Page<SchemeApplication> findByScheme_NameContainingIgnoreCase(String schemeName, Pageable pageable);

    // ================= SINGLE FETCH =================

    Optional<SchemeApplication> findByApplicant_IdAndScheme_Id(long applicantId, long schemeId);
}