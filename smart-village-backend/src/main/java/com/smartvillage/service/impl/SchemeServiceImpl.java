package com.smartvillage.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.smartvillage.entity.Scheme;
import com.smartvillage.exceptions.DuplicateResourceException;
import com.smartvillage.exceptions.InvalidDataException;
import com.smartvillage.exceptions.ResourceNotFoundException;
import com.smartvillage.repository.SchemeRepository;
import com.smartvillage.repository.UserRepository;
import com.smartvillage.service.SchemeService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SchemeServiceImpl implements SchemeService {

    private final SchemeRepository schemeRepository;
    private final UserRepository userRepository;

    public SchemeServiceImpl(SchemeRepository schemeRepository, UserRepository userRepository) {
        this.schemeRepository = schemeRepository;
        this.userRepository = userRepository;
    }

    // CREATE SCHEME
    @Override
    public Scheme createScheme(Scheme scheme) {

        if (schemeRepository.existsByNameIgnoreCase(scheme.getName())) {
            throw new DuplicateResourceException(
                    "Scheme with name " + scheme.getName() + " already exists");
        }

        validatePostedBy(scheme);
        validateDates(scheme);

        return schemeRepository.save(scheme);
    }

    // UPDATE SCHEME
    @Override
    public Scheme updateScheme(long id, Scheme scheme) {

        Scheme existingScheme = getSchemeOrThrow(id);

        validatePostedBy(scheme);
        validateDates(scheme);

        if (!existingScheme.getName().equalsIgnoreCase(scheme.getName())) {

            Optional<Scheme> existingByName = schemeRepository.findByName(scheme.getName());

            if (existingByName.isPresent()) {
                throw new DuplicateResourceException(
                        "Scheme with name " + scheme.getName() + " already exists");
            }
        }

        existingScheme.setPostedBy(scheme.getPostedBy());

        return schemeRepository.save(existingScheme);
    }

    // DELETE SCHEME
    @Override
    public void deleteScheme(long id) {
        Scheme scheme = getSchemeOrThrow(id);
        schemeRepository.delete(scheme);
    }

    // GET SCHEME BY ID
    @Override
    public Scheme getSchemeById(long id) {
        Scheme scheme = getSchemeOrThrow(id);
        autoExpire(scheme);
        return scheme;
    }

    // GET ALL SCHEMES (PAGINATED)
    @Override
    public Page<Scheme> getAllSchemes(Pageable pageable) {
        Page<Scheme> schemes = schemeRepository.findAll(pageable);
        schemes.forEach(this::autoExpire);
        return schemes;
    }

    // GET ACTIVE SCHEMES (PAGINATED)
    @Override
    public Page<Scheme> getActiveSchemes(Pageable pageable) {
        Page<Scheme> schemes = schemeRepository.findByActiveTrue(pageable);
        schemes.forEach(this::autoExpire);
        return schemes;
    }

    // GET SCHEMES BY POSTEDBY ID (PAGINATED)
    @Override
    public Page<Scheme> getSchemesByPostedById(long id, Pageable pageable) {
        Page<Scheme> schemes = schemeRepository.findByPostedBy_Id(id, pageable);
        schemes.forEach(this::autoExpire);
        return schemes;
    }

    // SEARCH SCHEMES (PAGINATED)
    @Override
    public Page<Scheme> searchSchemes(String name, Pageable pageable) {
        Page<Scheme> schemes = schemeRepository.findByNameContainingIgnoreCase(name, pageable);
        schemes.forEach(this::autoExpire);
        return schemes;
    }

    // DEACTIVATE SCHEME
    @Override
    public Scheme deactivateScheme(long id) {
        Scheme scheme = getSchemeOrThrow(id);

        if (!scheme.isActive()) {
            throw new InvalidDataException("Scheme is already deactivated");
        }

        scheme.setActive(false);
        return schemeRepository.save(scheme);
    }

    // FIND BY ELIGIBILITY (PAGINATED)
    @Override
    public Page<Scheme> findByEligibility(Integer financialYear,
                                          String religion,
                                          String casteCategory,
                                          Double annualIncome,
                                          Boolean disability,
                                          String department,
                                          Pageable pageable) {

        Page<Scheme> schemes = schemeRepository.findAll(pageable);

        return schemes.map(s -> {
            if (s.getEligibility() == null) return null;

            boolean match =
                    (financialYear == null || s.getEligibility().getFinancialYear() == financialYear) &&
                    (religion == null || religion.isBlank() ||
                            s.getEligibility().getReligion().equalsIgnoreCase(religion.trim())) &&
                    (casteCategory == null || casteCategory.isBlank() ||
                            s.getEligibility().getCasteCategory().equalsIgnoreCase(casteCategory.trim())) &&
                    (annualIncome == null ||
                            s.getEligibility().getAnnualIncome() >= annualIncome) &&
                    (disability == null ||
                            s.getEligibility().isDisability() == disability) &&
                    (department == null || department.isBlank() ||
                            s.getEligibility().getDepartment().equalsIgnoreCase(department.trim()));

            if (match) {
                autoExpire(s);
                return s;
            }

            return null;
        });
    }

    // ================= HELPER METHODS =================

    private Scheme getSchemeOrThrow(long id) {
        return schemeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Scheme", "id", id));
    }

    private void validatePostedBy(Scheme scheme) {
        if (!userRepository.existsById(scheme.getPostedBy().getId())) {
            throw new ResourceNotFoundException(
                    "postedBy", "id", scheme.getPostedBy().getId());
        }
    }

    private void validateDates(Scheme scheme) {
        if (scheme.getStartDate().isAfter(scheme.getEndDate())) {
            throw new IllegalArgumentException("Start date must be before End date");
        }

        if (scheme.getEndDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("End date must be in the future");
        }
    }

    private void autoExpire(Scheme scheme) {
        if (scheme.isActive()
                && scheme.getEndDate() != null
                && scheme.getEndDate().isBefore(LocalDate.now())) {

            scheme.setActive(false);
            schemeRepository.save(scheme);
        }
    }
}