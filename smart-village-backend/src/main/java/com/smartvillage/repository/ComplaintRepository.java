package com.smartvillage.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartvillage.entity.Complaint;
import com.smartvillage.enums.ComplaintStatus;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
	boolean existsByTitleIgnoreCase(String title);
	
    Optional<Complaint> findFirstByTitleContainingIgnoreCase(String title);
	
	List<Complaint>findByTitleContainingIgnoreCase(String keyword);

	List<Complaint> findByStatus(ComplaintStatus status);

	List<Complaint> findByCreatedAtAfter(LocalDate since);

	List<Complaint> findByCitizen_Id(long citizenId);

	List<Complaint> findByHandledBy_Id(long handledById);

}
