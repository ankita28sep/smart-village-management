package com.smartvillage.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartvillage.entity.Scheme;

public interface SchemeRepository extends JpaRepository<Scheme, Long> {
	boolean existsByNameIgnoreCase(String name);

	List<Scheme> findByStartDateAfter(LocalDate date);

	List<Scheme> findByNameContainingIgnoreCase(String name);

	List<Scheme> findByPostedBy_Id(Long userId);

	List<Scheme> findByActiveTrue();

	Optional<Scheme> findByName(String name);

	

	

}
