package com.smartvillage.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.smartvillage.entity.Complaint;
import com.smartvillage.enums.ComplaintStatus;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    Page<Complaint> findByCitizen_Id(long citizenId, Pageable pageable);

    Page<Complaint> findByHandledBy_Id(long handlerId, Pageable pageable);

    Page<Complaint> findByStatus(ComplaintStatus status, Pageable pageable);

    Page<Complaint> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

    Page<Complaint> findByCreatedAtAfter(LocalDate since, Pageable pageable);

    // Check for existing complaints with same title (case-insensitive)
    boolean existsByTitleIgnoreCase(String title);

    // Check for existing complaints with same title excluding a specific id (used on update)
    boolean existsByTitleIgnoreCaseAndIdNot(String title, long id);
}