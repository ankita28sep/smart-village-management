package com.smartvillage.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.smartvillage.entity.Scheme;

public interface SchemeRepository extends JpaRepository<Scheme, Long>,
JpaSpecificationExecutor<Scheme> {

    // Check duplicate
    boolean existsByNameIgnoreCase(String name);

    // Find by name
    Optional<Scheme> findByName(String name);

    // PAGINATED METHODS (used in service)

    Page<Scheme> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Scheme> findByPostedBy_Id(long id, Pageable pageable);

    Page<Scheme> findByActiveTrue(Pageable pageable);

    // Optional (keep only if you actually use it somewhere)
    Page<Scheme> findByStartDateAfter(LocalDate date, Pageable pageable);
}