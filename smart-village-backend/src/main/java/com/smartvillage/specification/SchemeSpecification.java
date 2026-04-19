package com.smartvillage.specification;

import org.springframework.data.jpa.domain.Specification;

import com.smartvillage.entity.Scheme;

public class SchemeSpecification {

    public static Specification<Scheme> filterByEligibility(
            Integer financialYear,
            String religion,
            String casteCategory,
            Double annualIncome,
            Boolean disability,
            String department) {

        return (root, query, cb) -> {

            var e = root.get("eligibility");

            var predicate = cb.conjunction();

            if (financialYear != null) {
                predicate = cb.and(predicate,
                        cb.equal(e.get("financialYear"), financialYear));
            }

            if (religion != null && !religion.isBlank()) {
                predicate = cb.and(predicate,
                        cb.equal(cb.lower(e.get("religion")), religion.toLowerCase()));
            }

            if (casteCategory != null && !casteCategory.isBlank()) {
                predicate = cb.and(predicate,
                        cb.equal(cb.lower(e.get("casteCategory")), casteCategory.toLowerCase()));
            }

            if (annualIncome != null) {
                predicate = cb.and(predicate,
                        cb.greaterThanOrEqualTo(e.get("annualIncome"), annualIncome));
            }

            if (disability != null) {
                predicate = cb.and(predicate,
                        cb.equal(e.get("disability"), disability));
            }

            if (department != null && !department.isBlank()) {
                predicate = cb.and(predicate,
                        cb.equal(cb.lower(e.get("department")), department.toLowerCase()));
            }

            return predicate;
        };
    }
}