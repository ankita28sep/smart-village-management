package com.smartvillage.service.impl;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    private final AnnouncementRepository announcementRepository;

    public AnnouncementServiceImpl(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    // POST ANNOUNCEMENT
    @Override
    public Announcement postAnnouncement(Announcement announcement) {

        if (announcementRepository.existsByTitleIgnoreCase(announcement.getTitle())) {
            throw new DuplicateResourceException(
                    "Announcement with title '" + announcement.getTitle() + "' already exists");
        }

        announcement.setPostedAt(LocalDateTime.now());
        announcement.setStatus(AnnouncementStatus.ACTIVE);

        return announcementRepository.save(announcement);
    }

    // UPDATE ANNOUNCEMENT
    @Override
    public Announcement updateAnnouncement(long id, Announcement announcement) {

        Announcement existing = getAnnouncementById(id);

        // Mapper already updated fields → just save
        return announcementRepository.save(announcement);
    }

    // DELETE
    @Override
    public void deleteAnnouncement(long id) {
        Announcement existing = getAnnouncementById(id);
        announcementRepository.delete(existing);
    }

    // GET BY ID
    @Override
    public Announcement getAnnouncementById(long id) {
        return announcementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Announcement", "id", id));
    }

    // PAGINATED METHODS

    @Override
    public Page<Announcement> getAllAnnouncements(Pageable pageable) {
        return announcementRepository.findAll(pageable);
    }

    @Override
    public Page<Announcement> getAnnouncementsPostedBy(long postedById, Pageable pageable) {
        return announcementRepository.findByPostedBy_Id(postedById, pageable);
    }

    @Override
    public Page<Announcement> searchAnnouncement(String title, Pageable pageable) {
        return announcementRepository.findByTitleContainingIgnoreCase(title, pageable);
    }

    @Override
    public Page<Announcement> getRecentAnnouncements(LocalDateTime since, Pageable pageable) {
        return announcementRepository.findByPostedAtAfter(since, pageable);
    }

    @Override
    public Page<Announcement> getAnnouncementsByType(AnnouncementType type, Pageable pageable) {
        return announcementRepository.findByType(type, pageable);
    }

    @Override
    public Page<Announcement> getAnnouncementsByStatus(AnnouncementStatus status, Pageable pageable) {
        return announcementRepository.findByStatus(status, pageable);
    }

    @Override
    public Page<Announcement> findByActiveTrue(Pageable pageable) {
        return announcementRepository.findByStatus(AnnouncementStatus.ACTIVE, pageable);
    }
}