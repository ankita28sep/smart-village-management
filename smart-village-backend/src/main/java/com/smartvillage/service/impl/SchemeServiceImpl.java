package com.smartvillage.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private SchemeRepository schemeRepository;
	@Autowired
	private UserRepository userRepository;

	// CREATE SCHEME
	@Override
	public Scheme createScheme(Scheme scheme) {
		if (schemeRepository.existsByNameIgnoreCase(scheme.getName())) {
			throw new DuplicateResourceException("Scheme with name " + scheme.getName() + " already exists");
		}
		if (!userRepository.existsById(scheme.getPostedBy().getId())) {
			throw new ResourceNotFoundException("postedby", "id", scheme.getPostedBy().getId());
		}

		if (scheme.getStartDate().isAfter(scheme.getEndDate())) {
			throw new IllegalArgumentException("Start date must be before End date");
		}

		if (scheme.getEndDate().isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("End date must be in the future");
		}
		return schemeRepository.save(scheme);
	}

	// UPDATE SCHEME
	@Override
	public Scheme updateScheme(long id, Scheme scheme) {
		Scheme existingScheme = schemeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Scheme", "id", id));

		if (!userRepository.existsById(scheme.getPostedBy().getId())) {
			throw new ResourceNotFoundException("postedby", "id", scheme.getPostedBy().getId());
		}
		if (scheme.getStartDate().isAfter(scheme.getEndDate())) {
			throw new IllegalArgumentException("Start date must be before End date");
		}

		if (scheme.getEndDate().isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("End date must be in the future");
		}
		// Check duplicate only if name is changed
		if (!existingScheme.getName().equals(scheme.getName())) {

			Optional<Scheme> existingByName = schemeRepository.findByName(scheme.getName());

			if (existingByName.isPresent()) {
				throw new DuplicateResourceException("Scheme with name " + scheme.getName() + " already exists");
			}
		}
		existingScheme.setPostedBy(scheme.getPostedBy());
		return schemeRepository.save(existingScheme);
	}

	// DELETE SCHEME
	@Override
	public void deleteScheme(long id) {
		Scheme existingScheme = schemeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Scheme", "id", id));
		schemeRepository.delete(existingScheme);
	}

	// GET SCHEME BY ID
	@Override
	public Scheme getSchemeById(long id) {
		Scheme scheme = schemeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Scheme", "id", id));
		autoExpire(scheme);
		return scheme;
	}

	// GET ALL SCHEMES
	@Override
	public List<Scheme> getAllSchemes() {
		List<Scheme> all = schemeRepository.findAll();
		all.forEach(this::autoExpire);
		return all;
	}

	// GET ACTIVE SCHEMES
	@Override
	public List<Scheme> getActiveSchemes() {
		List<Scheme> byActiveTrue = schemeRepository.findByActiveTrue();
		byActiveTrue.forEach(this::autoExpire);
		return byActiveTrue;
	}

	// GET SCHEMES BY POSTEDBY ID
	@Override
	public List<Scheme> getSchemesByPostedById(long id) {
		List<Scheme> schemes = schemeRepository.findByPostedBy_Id(id);
		schemes.forEach(this::autoExpire);
		return schemes;
	}

	// SEARCH SCHEMES BY NAME
	@Override
	public List<Scheme> searchSchemes(String name) {
		List<Scheme> schemes = schemeRepository.findByNameContainingIgnoreCase(name);
		schemes.forEach(this::autoExpire);
		return schemes;
	}

	// DEACTIVATE SCHEME
	@Override
	public Scheme deactivateScheme(long id) {
		Scheme scheme = schemeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Scheme", "id", id));
		if (!scheme.isActive()) {
			throw new InvalidDataException("scheme is already deactivated");
		}
		scheme.setActive(false);
		return schemeRepository.save(scheme);
	}

	// AUTO EXPIRE SCHEME
	private void autoExpire(Scheme scheme) {
		if (scheme.isActive() && scheme.getEndDate() != null && scheme.getEndDate().isBefore(LocalDate.now())) {

			scheme.setActive(false);
			schemeRepository.save(scheme);
		}
	}

	// FIND SCHEME BY ELIGIBILITY
	@Override
	public List<Scheme> findByEligibility(Integer financialYear, String religion, String casteCategory,
			Double annualIncome, Boolean disability, String department) {

		List<Scheme> schemes = schemeRepository.findAll();

		schemes = schemes.stream().filter(s -> s.getEligibility() != null)
				.filter(s -> financialYear == null || s.getEligibility().getFinancialYear() == (financialYear))

				.filter(s -> religion == null || religion.isBlank()
						|| s.getEligibility().getReligion().equalsIgnoreCase(religion.trim()))

				.filter(s -> casteCategory == null || casteCategory.isBlank()
						|| s.getEligibility().getCasteCategory().equalsIgnoreCase(casteCategory.trim()))

				.filter(s -> annualIncome == null || s.getEligibility().getAnnualIncome() >= annualIncome)

				.filter(s -> disability == null || s.getEligibility().isDisability() == (disability))

				.filter(s -> department == null || department.isBlank()
						|| s.getEligibility().getDepartment().equalsIgnoreCase(department.trim()))
				.toList();

		schemes.forEach(this::autoExpire);
		return schemes;
	}

}
