package com.smartvillage.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartvillage.entity.Announcement;
import com.smartvillage.enums.AnnouncementStatus;
import com.smartvillage.enums.AnnouncementType;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
	List<Announcement> findByTitleContainingIgnoreCase(String title);

	List<Announcement> findByType(AnnouncementType type);

	List<Announcement> findByStatus(AnnouncementStatus status);

	Optional<Announcement> findFirstByTitleContainingIgnoreCase(String title);

	List<Announcement> findByPostedBy_Id(long userId);

	List<Announcement> findByPostedAtAfter(LocalDateTime start);

}
