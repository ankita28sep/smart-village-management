package com.smartvillage.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartvillage.entity.Announcement;
import com.smartvillage.enums.AnnouncementStatus;
import com.smartvillage.enums.AnnouncementType;
import com.smartvillage.exceptions.DuplicateResourceException;
import com.smartvillage.exceptions.ResourceNotFoundException;
import com.smartvillage.repository.AnnouncementRepository;
import com.smartvillage.service.AnnouncementService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AnnouncementServiceImpl implements AnnouncementService {
	@Autowired
	private AnnouncementRepository announcementRepository;
	

	// POST ANNOUNCEMENT
	@Override
	public Announcement postAnnouncement(Announcement announcement) {
		if (announcementRepository.findFirstByTitleContainingIgnoreCase(announcement.getTitle()).isPresent()) {
			throw new DuplicateResourceException(
					"announcement with title " + announcement.getTitle() + " already exists");
		}
		announcement.setPostedBy(announcement.getPostedBy());
		announcement.setPostedAt(LocalDateTime.now());
		announcement.setType(announcement.getType());
		announcement.setStatus(AnnouncementStatus.ACTIVE);
		return announcementRepository.save(announcement);
	}

	// UPDATE ANNOUNCEMENT
	@Override
	public Announcement updateAnnouncement(long id, Announcement announcement) {
		Announcement existingAnnouncement = announcementRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("announcement", "id", id));
		return announcementRepository.save(existingAnnouncement);
	}

	// DELETE ANNOUNCEMENT
	@Override
	public void deleteAnnouncement(long id) {
		Announcement existingAnnouncement = announcementRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("announcement", "id", id));
		announcementRepository.delete(existingAnnouncement);
	}

	// GET ANNOUNCEMENT BY ID
	@Override
	public Announcement getAnnouncementById(long id) {
		return announcementRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("announcement", "id", id));
	}

	// GET ALL ANNOUNCEMENTS
	@Override
	public List<Announcement> getAllAnnouncements() {
		return announcementRepository.findAll();
	}

	// GET ANNOUNCEMENTS POSTED BY USER
	@Override
	public List<Announcement> getAnnouncementsPostedBy(long postedById) {
		return announcementRepository.findByPostedBy_Id(postedById);
	}

	// SEARCH ANNOUNCEMENT BY TITLE
	@Override
	public List<Announcement> searchAnnouncement(String title) {
		return announcementRepository.findByTitleContainingIgnoreCase(title);
	}

	// GET RECENT ANNOUNCEMENTS
	@Override
	public List<Announcement> getRecentAnnouncements(LocalDateTime since) {
		return announcementRepository.findByPostedAtAfter(since);
	}

	// GET ANNOUNCEMENTS BY TYPE
	@Override
	public List<Announcement> getAnnouncementsByType(AnnouncementType type) {

		return announcementRepository.findByType(type);
	}

//GET ANNOUNCEMENTS BY STATUS
	@Override
	public List<Announcement> getAnnouncementsByStatus(AnnouncementStatus status) {
		return announcementRepository.findByStatus(status);

	}

	@Override
	public List<Announcement> findByActiveTrue() {

		return announcementRepository.findByStatus(AnnouncementStatus.ACTIVE);
	}

}
