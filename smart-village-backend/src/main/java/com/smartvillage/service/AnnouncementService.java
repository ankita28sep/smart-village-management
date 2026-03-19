package com.smartvillage.service;

import java.time.LocalDateTime;
import java.util.List;

import com.smartvillage.entity.Announcement;
import com.smartvillage.enums.AnnouncementStatus;
import com.smartvillage.enums.AnnouncementType;

public interface AnnouncementService {
	Announcement postAnnouncement(Announcement announcement);

	Announcement updateAnnouncement(long id, Announcement announcement);

	void deleteAnnouncement(long id);

	Announcement getAnnouncementById(long id);

	List<Announcement> getAllAnnouncements();
	List<Announcement>findByActiveTrue();

	List<Announcement> getAnnouncementsPostedBy(long postedById);

	List<Announcement> searchAnnouncement(String name);

	List<Announcement> getRecentAnnouncements(LocalDateTime since);

	List<Announcement> getAnnouncementsByType(AnnouncementType type);

	List<Announcement> getAnnouncementsByStatus(AnnouncementStatus status);

}
