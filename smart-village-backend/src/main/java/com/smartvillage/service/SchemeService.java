package com.smartvillage.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.smartvillage.entity.Scheme;

public interface SchemeService {

    // Create scheme
    Scheme createScheme(Scheme scheme);

    // Update scheme
    Scheme updateScheme(long id, Scheme scheme);

    // Delete scheme
    void deleteScheme(long id);

    // Get scheme by ID
    Scheme getSchemeById(long id);

    // Get all schemes (PAGINATED)
    Page<Scheme> getAllSchemes(Pageable pageable);

    // Get active schemes (PAGINATED)
    Page<Scheme> getActiveSchemes(Pageable pageable);

    // Get schemes by posted user (PAGINATED)
    Page<Scheme> getSchemesByPostedById(long userId, Pageable pageable);

    // Search schemes (PAGINATED)
    Page<Scheme> searchSchemes(String name, Pageable pageable);

    // Deactivate scheme
    Scheme deactivateScheme(long id);

    // Filter by eligibility (PAGINATED)
    Page<Scheme> findByEligibility(
            Integer financialYear,
            String religion,
            String casteCategory,
            Double annualIncome,
            Boolean disability,
            String department,
            Pageable pageable
    );
}