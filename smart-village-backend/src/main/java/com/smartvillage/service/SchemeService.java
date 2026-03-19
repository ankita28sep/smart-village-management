package com.smartvillage.service;

import java.util.List;

import com.smartvillage.entity.Scheme;

public interface SchemeService {
	Scheme createScheme(Scheme scheme);

	Scheme updateScheme(long id, Scheme scheme);

	void deleteScheme(long id);

	Scheme getSchemeById(long id);

	List<Scheme> getAllSchemes();

	List<Scheme> getActiveSchemes();

	List<Scheme> getSchemesByPostedById(long userId);

	List<Scheme> searchSchemes(String name);

	Scheme deactivateScheme(long id);
	List<Scheme> findByEligibility(
	        Integer financialYear,
	        String religion,
	        String casteCategory,
	        Double annualIncome,
	        Boolean disability,
	        String department
	);
	
	

}
