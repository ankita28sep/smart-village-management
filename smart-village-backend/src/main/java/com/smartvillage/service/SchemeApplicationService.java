package com.smartvillage.service;

import java.util.List;

import com.smartvillage.entity.Eligibility;
import com.smartvillage.entity.SchemeApplication;
import com.smartvillage.enums.ApplicationStatus;

public interface SchemeApplicationService {

	SchemeApplication applyToScheme(SchemeApplication application);

	SchemeApplication updateApplication(long applicationId, SchemeApplication application);

	SchemeApplication updateApplicationStatus(long applicationId, ApplicationStatus status);

	void deleteApplication(long applicationId);

	SchemeApplication getApplicationById(long applicationId);

	SchemeApplication findByApplicant_IdAndScheme_Id(long applicantId, long schemeId);

	List<SchemeApplication> getAllApplications();

	List<SchemeApplication> getApplicationsByApplicantId(long applicantId);

	List<SchemeApplication> getApplicationsBySchemeId(long schemeId);

	List<SchemeApplication> getApplicationsByStatus(ApplicationStatus status);

	List<SchemeApplication> searchApplicationsByApplicantName(String keyword);

	List<SchemeApplication> searchApplicationsBySchemeName(String keyword);

	SchemeApplication cancelApplication(long id);

	List<SchemeApplication> getApplicationsByYear(int year);

	void checkEligibility(SchemeApplication app, Eligibility e);

}
