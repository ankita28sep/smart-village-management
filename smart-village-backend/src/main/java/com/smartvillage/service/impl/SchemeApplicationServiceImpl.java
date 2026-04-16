package com.smartvillage.service.impl;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    private final SchemeApplicationRepository repository;

    public SchemeApplicationServiceImpl(SchemeApplicationRepository repository) {
        this.repository = repository;
    }

    // APPLY TO SCHEME
    @Override
    public SchemeApplication applyToScheme(SchemeApplication application) {

        validateDuplicateApplication(application);
        validateApplicant(application);
        validateScheme(application);
        validateEligibility(application);

        // Annual duplicate check
        int currentYear = LocalDate.now().getYear();
        if (repository.existsByApplicant_IdAndScheme_IdAndYear(
                application.getApplicant().getId(),
                application.getScheme().getId(),
                currentYear)) {

            throw new DuplicateResourceException(
                    "You have already applied for this scheme this year");
        }

        return repository.save(application);
    }

    // UPDATE APPLICATION
    @Override
    public SchemeApplication updateApplication(long id, SchemeApplication updated) {

        SchemeApplication existing = getApplicationOrThrow(id);

        if (existing.getStatus() != ApplicationStatus.PENDING) {
            throw new InvalidDataException("Only PENDING applications can be updated");
        }

        // IMPORTANT: updated object already modified by mapper
        return repository.save(existing);
    }

    // UPDATE STATUS
    @Override
    public SchemeApplication updateApplicationStatus(long id, ApplicationStatus status) {

        SchemeApplication app = getApplicationOrThrow(id);

        if (app.getStatus() != ApplicationStatus.PENDING) {
            throw new InvalidDataException("Only PENDING applications can be updated");
        }

        if (status != ApplicationStatus.APPROVED && status != ApplicationStatus.REJECTED) {
            throw new InvalidDataException("Invalid status transition");
        }

        app.setStatus(status);
        return repository.save(app);
    }

    // DELETE APPLICATION
    @Override
    public void deleteApplication(long id) {

        SchemeApplication app = getApplicationOrThrow(id);

        if (app.getStatus() != ApplicationStatus.PENDING) {
            throw new InvalidDataException("Only PENDING applications can be deleted");
        }

        repository.delete(app);
    }

    // GET BY ID
    @Override
    public SchemeApplication getApplicationById(long id) {
        return getApplicationOrThrow(id);
    }

    // GET ALL (PAGINATED)
    @Override
    public Page<SchemeApplication> getAllApplications(Pageable pageable) {
        return repository.findAll(pageable);
    }

    // BY STATUS (PAGINATED)
    @Override
    public Page<SchemeApplication> getApplicationsByStatus(ApplicationStatus status, Pageable pageable) {
        return repository.findByStatus(status, pageable);
    }

    // BY APPLICANT (PAGINATED)
    @Override
    public Page<SchemeApplication> getApplicationsByApplicantId(long applicantId, Pageable pageable) {
        return repository.findByApplicant_Id(applicantId, pageable);
    }

    // BY SCHEME (PAGINATED)
    @Override
    public Page<SchemeApplication> getApplicationsBySchemeId(long schemeId, Pageable pageable) {
        return repository.findByScheme_Id(schemeId, pageable);
    }

    // CANCEL APPLICATION
    @Override
    public SchemeApplication cancelApplication(long id) {

        SchemeApplication app = getApplicationOrThrow(id);

        if (app.getStatus() != ApplicationStatus.PENDING) {
            throw new InvalidDataException("Only pending applications can be cancelled");
        }

        app.setStatus(ApplicationStatus.CANCELLED);
        return repository.save(app);
    }

    // ================= HELPER METHODS =================

    private SchemeApplication getApplicationOrThrow(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("application", "id", id));
    }

    private void validateDuplicateApplication(SchemeApplication app) {

        if (repository.existsByApplicant_IdAndScheme_Id(
                app.getApplicant().getId(),
                app.getScheme().getId())) {

            throw new DuplicateResourceException("Application already exists for this scheme");
        }

        if (app.getAadharNo() != null &&
                repository.existsByAadharNoAndScheme_Id(
                        app.getAadharNo(),
                        app.getScheme().getId())) {

            throw new DuplicateResourceException(
                    "Application with same Aadhar already exists for this scheme");
        }
    }

    private void validateApplicant(SchemeApplication app) {
        if (!app.getApplicant().isActive()) {
            throw new InvalidDataException("Blocked users cannot apply");
        }
    }

    private void validateScheme(SchemeApplication app) {

        if (!app.getScheme().isActive()) {
            throw new InvalidDataException("Scheme is inactive");
        }

        if (app.getScheme().getEndDate() != null &&
                app.getScheme().getEndDate().isBefore(LocalDate.now())) {

            throw new InvalidDataException("Scheme has expired");
        }
    }

    private void validateEligibility(SchemeApplication app) {

        Eligibility e = app.getScheme().getEligibility();

        if (e == null) return;

        if (app.getFinancialYear() != e.getFinancialYear()) {
            throw new InvalidDataException("Financial year mismatch");
        }

        if (e.getReligion() != null &&
                !e.getReligion().equalsIgnoreCase(app.getReligion())) {
            throw new InvalidDataException("Religion mismatch");
        }

        if (e.getCasteCategory() != null &&
                !e.getCasteCategory().equalsIgnoreCase(app.getCaste())) {
            throw new InvalidDataException("Caste mismatch");
        }

        if (e.getAnnualIncome() > 0 &&
                app.getAnnualIncome() > e.getAnnualIncome()) {
            throw new InvalidDataException("Income exceeds limit");
        }

        if (e.getDepartment() != null &&
                !e.getDepartment().equalsIgnoreCase(app.getDepartment())) {
            throw new InvalidDataException("Department mismatch");
        }

        if (e.isDisability() && !app.isDisability()) {
            throw new InvalidDataException("Only for disabled applicants");
        }
    }

	@Override
	public SchemeApplication findByApplicant_IdAndScheme_Id(long applicantId, long schemeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SchemeApplication> getApplicationsByYear(int year, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SchemeApplication> searchApplicationsByApplicantName(String keyword, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SchemeApplication> searchApplicationsBySchemeName(String keyword, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}