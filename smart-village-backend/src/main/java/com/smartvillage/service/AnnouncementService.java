package com.smartvillage.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.smartvillage.entity.Announcement;
import com.smartvillage.enums.AnnouncementStatus;
import com.smartvillage.enums.AnnouncementType;

public interface AnnouncementService {

    Announcement postAnnouncement(Announcement announcement);

    Announcement updateAnnouncement(long id, Announcement announcement);

    void deleteAnnouncement(long id);

    Announcement getAnnouncementById(long id);

    Page<Announcement> getAllAnnouncements(Pageable pageable);

    Page<Announcement> findByActiveTrue(Pageable pageable);

    Page<Announcement> getAnnouncementsPostedBy(long postedById, Pageable pageable);

    Page<Announcement> searchAnnouncement(String name, Pageable pageable);

    Page<Announcement> getRecentAnnouncements(LocalDateTime since, Pageable pageable);

    Page<Announcement> getAnnouncementsByType(AnnouncementType type, Pageable pageable);

    Page<Announcement> getAnnouncementsByStatus(AnnouncementStatus status, Pageable pageable);
}