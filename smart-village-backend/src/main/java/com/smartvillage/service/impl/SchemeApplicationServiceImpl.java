package com.smartvillage.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.smartvillage.entity.Eligibility;
import com.smartvillage.entity.SchemeApplication;
import com.smartvillage.enums.ApplicationStatus;
import com.smartvillage.exceptions.DuplicateResourceException;
import com.smartvillage.exceptions.InvalidDataException;
import com.smartvillage.exceptions.ResourceNotFoundException;
import com.smartvillage.repository.SchemeApplicationRepository;
import com.smartvillage.service.SchemeApplicationService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SchemeApplicationServiceImpl implements SchemeApplicationService {
	@Autowired
	private SchemeApplicationRepository schemeApplicationRepository;

	// APPLY TO SCHEME
	@Override
	public SchemeApplication applyToScheme(SchemeApplication application) {
		// CHECK DUPLICATE APPLICATION
		if (schemeApplicationRepository.existsByApplicant_IdAndScheme_Id(application.getApplicant().getId(),
				application.getScheme().getId())) {
			throw new DuplicateResourceException("application with applicant id " + application.getApplicant().getId()
					+ " and scheme id " + application.getScheme().getId() + " already exists");
		}
		// CHECK DUPLICATE AADHAR NO FOR SAME SCHEME
		if (application.getAadharNo() != null && schemeApplicationRepository
				.existsByAadharNoAndScheme_Id(application.getAadharNo(), application.getScheme().getId())) {
			throw new DuplicateResourceException(
					"An application with the same Aadhar number already exists for this scheme");
		}
		// CHECK USER STATUS
		if (application.getApplicant().isActive() == false) {
			throw new InvalidDataException("Blocked users can not apply");
		}
		// CHECK EXPIREY OF SCHEME
		if (application.getScheme().getEndDate() != null
				&& application.getScheme().getEndDate().isBefore(LocalDate.now())) {
			throw new InvalidDataException("Scheme has expired");
		}
		Eligibility e = application.getScheme().getEligibility();
		this.checkEligibility(application, e);

		// CHECK SCHEME STATUS
		if (!application.getScheme().isActive()) {
			throw new InvalidDataException("Scheme is inactive or expired");
		}
		// CHECK ANNUAL APPLICATION LIMIT
		int currentYear = LocalDate.now().getYear();
		if (schemeApplicationRepository.existsByApplicant_IdAndScheme_IdAndYear(application.getApplicant().getId(),
				application.getScheme().getId(), currentYear)) {
			throw new DuplicateResourceException("You have already applied for this scheme in the current year");
		}

		return schemeApplicationRepository.save(application);
	}

	// UPDATE APPLICATION
	@Override
	public SchemeApplication updateApplication(long applicationId, SchemeApplication application) {
		SchemeApplication existingApplication = this.getApplicationIfOwner(applicationId);
		// Only allow update if pending
		if (existingApplication.getStatus() != ApplicationStatus.PENDING) {
			throw new InvalidDataException("Only PENDING applications can be updated");
		}
		return schemeApplicationRepository.save(existingApplication);
	}

	// UPDATE APPLICATION STATUS
	@Override
	public SchemeApplication updateApplicationStatus(long id, ApplicationStatus status) {
		SchemeApplication application = schemeApplicationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("application", "id", id));
		ApplicationStatus currentStatus = application.getStatus();
		if (currentStatus == ApplicationStatus.PENDING
				&& (status == ApplicationStatus.APPROVED || status == ApplicationStatus.REJECTED)) {
			application.setStatus(status);
		} else {
			throw new InvalidDataException("invalid status transition from " + currentStatus + " to " + status);
		}
		return schemeApplicationRepository.save(application);
	}

	// DELETE APPLICATION
	@Override
	public void deleteApplication(long id) {

		SchemeApplication application = this.getApplicationIfOwner(id);
		// Only allow update if pending
		if (application.getStatus() != ApplicationStatus.PENDING) {
			throw new InvalidDataException("Only PENDING applications can be deleted");
		}
		
		schemeApplicationRepository.delete(application);
	}

	// GET APPLICATION BY ID
	@Override
	public SchemeApplication getApplicationById(long id) {
		return schemeApplicationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("application", "id", id));
	}

	// GET ALL APPLICATIONS
	@Override
	public List<SchemeApplication> getAllApplications() {
		return schemeApplicationRepository.findAll();
	}

	// GET APPLICATIONS BY STATUS
	@Override
	public List<SchemeApplication> getApplicationsByStatus(ApplicationStatus status) {
		return schemeApplicationRepository.findByStatus(status);
	}

	// GET APPLICATIONS BY APPLICANT ID
	@Override
	public List<SchemeApplication> getApplicationsByApplicantId(long applicant_id) {
		return schemeApplicationRepository.findByApplicant_Id(applicant_id);
	}

	// GET APPLICATIONS BY SCHEME ID
	@Override
	public List<SchemeApplication> getApplicationsBySchemeId(long scheme_id) {
		return schemeApplicationRepository.findByScheme_Id(scheme_id);
	}

	// GET APPLICATIONS BY CITITZEN ID AND SCHEME ID
	@Override
	public SchemeApplication findByApplicant_IdAndScheme_Id(long applicantId, long schemeId) {
		return schemeApplicationRepository.findByApplicant_IdAndScheme_Id(applicantId, schemeId)
				.orElseThrow(() -> new ResourceNotFoundException("SchemeApplication", "applicantId/schemeId",
						applicantId + "/" + schemeId));
	}

// GET APPLICATIONS BY APPLICANT NAME
	@Override
	public List<SchemeApplication> searchApplicationsByApplicantName(String keyword) {

		return schemeApplicationRepository.findByApplicant_NameContainingIgnoreCase(keyword);
	}

// GET APPLICATIONS BY SCHEME NAME
	@Override
	public List<SchemeApplication> searchApplicationsBySchemeName(String keyword) {
		return schemeApplicationRepository.findByScheme_NameContainingIgnoreCase(keyword);
	}

// CANCEL APPLICATION
	@Override
	public SchemeApplication cancelApplication(long id) {
		SchemeApplication application = schemeApplicationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("application", "id", id));
		if (!application.getStatus().equals(ApplicationStatus.PENDING)) {
			throw new InvalidDataException(("Only pending applications can be cancelled"));
		}
		application.setStatus(ApplicationStatus.CANCELLED);

		return schemeApplicationRepository.save(application);
	}

// GET APPLICATIONS BY YEAR
	@Override
	public List<SchemeApplication> getApplicationsByYear(int year) {
		return schemeApplicationRepository.findByYear(year);
	}

//ELIGIBILITY CHECK
	@Override
	public void checkEligibility(SchemeApplication application, Eligibility e) {
		// ELIGIBILITY CHECK

		if (e != null) {

			// Financial Year
			if (application.getFinancialYear() != e.getFinancialYear()) {
				throw new InvalidDataException("Not eligible: Financial year mismatch");
			}

			// Religion
			if (e.getReligion() != null && application.getReligion() != null
					&& !application.getReligion().equalsIgnoreCase(e.getReligion())) {

				throw new InvalidDataException("Not eligible: Religion mismatch");
			}

			// Caste
			if (e.getCasteCategory() != null && application.getCaste() != null
					&& !application.getCaste().equalsIgnoreCase(e.getCasteCategory())) {

				throw new InvalidDataException("Not eligible: Caste category mismatch");
			}

			// Income (IMPORTANT FIX)
			if (e.getAnnualIncome() > 0 && application.getAnnualIncome() > e.getAnnualIncome()) {

				throw new InvalidDataException("Not eligible: Income exceeds scheme limit");
			}

			// Department
			if (e.getDepartment() != null && application.getDepartment() != null
					&& !application.getDepartment().equalsIgnoreCase(e.getDepartment())) {

				throw new InvalidDataException("Not eligible: Department mismatch");
			}

			// Disability (IMPORTANT FIX)
			if (e.isDisability() && !application.isDisability()) {

				throw new InvalidDataException("Not eligible: Scheme only for disabled applicants");
			}
		}

	}

	private SchemeApplication getApplicationIfOwner(Long id) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String email = auth.getName();

		SchemeApplication app = schemeApplicationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("application", "id", id));
		
		boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

		if (!isAdmin && !app.getApplicant().getEmail().equals(email)) {
			throw new InvalidDataException("Access denied");
		}

		return app;
	}
}
