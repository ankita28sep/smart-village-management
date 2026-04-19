package com.smartvillage.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.smartvillage.entity.SchemeApplication;
import com.smartvillage.enums.ApplicationStatus;

public interface SchemeApplicationService {

    // APPLY
    SchemeApplication applyToScheme(SchemeApplication application);

    // UPDATE
    SchemeApplication updateApplication(long applicationId, SchemeApplication application);

    // STATUS UPDATE
    SchemeApplication updateApplicationStatus(long applicationId, ApplicationStatus status);

    // DELETE
    void deleteApplication(long applicationId);

    // GET SINGLE
    SchemeApplication getApplicationById(long applicationId);

   

    // ================= PAGINATED METHODS =================

    Page<SchemeApplication> getAllApplications(Pageable pageable);

    Page<SchemeApplication> getApplicationsByApplicantId(long applicantId, Pageable pageable);

    Page<SchemeApplication> getApplicationsBySchemeId(long schemeId, Pageable pageable);

    Page<SchemeApplication> getApplicationsByStatus(ApplicationStatus status, Pageable pageable);

   

    // ================= ACTIONS =================

    SchemeApplication cancelApplication(long id);
}