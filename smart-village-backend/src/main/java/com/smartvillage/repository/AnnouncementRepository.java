package com.smartvillage.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.smartvillage.entity.Announcement;
import com.smartvillage.enums.AnnouncementStatus;
import com.smartvillage.enums.AnnouncementType;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
	boolean existsByTitleIgnoreCase(String title);

	Page<Announcement> findByPostedBy_Id(long postedById, Pageable pageable);

	Page<Announcement> findByTitleContainingIgnoreCase(String title, Pageable pageable);

	Page<Announcement> findByPostedAtAfter(LocalDateTime since, Pageable pageable);

	Page<Announcement> findByType(AnnouncementType type, Pageable pageable);

	Page<Announcement> findByStatus(AnnouncementStatus status, Pageable pageable);

}
